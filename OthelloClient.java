import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple Swing-based client for the chat server. Graphically it is a frame with a text
 * field for entering messages and a textarea to see the whole dialog.
 *
 * The client follows the following Chat Protocol. When the server sends "SUBMITNAME" the
 * client replies with the desired screen name. The server will keep sending "SUBMITNAME"
 * requests as long as the client submits screen names that are already in use. When the
 * server sends a line beginning with "NAMEACCEPTED" the client is now allowed to start
 * sending the server arbitrary strings to be broadcast to all chatters connected to the
 * server. When the server sends a line beginning with "MESSAGE " then all characters
 * following this string should be displayed in its message area.
 */
public class OthelloClient extends World {

    public static final int StandardSideLength = 8;
    public static final int ImageLength = 55;
    public static final int VerticalMargin = 20;
    public static final int HorizontalMargin = 20;
    boolean black = false;
    GameLogic gameRule = new GameLogic(false);
    //JSONObject clientJson = new JSONObject();
    private ClientCell[][] cellList = new ClientCell[8][8]; //Contains all positions on the board, empty, black or white.

    BufferedReader in;
    PrintWriter out;

    /**
     * Constructs the client by laying out the GUI and registering a listener with the
     * textfield so that pressing Return in the listener sends the textfield contents
     * to the server. Note however that the textfield is initially NOT editable, and
     * only becomes editable AFTER the client receives the NAMEACCEPTED message from
     * the server.
     */
    public OthelloClient()  {
        super(StandardSideLength * ImageLength + HorizontalMargin * 2, StandardSideLength * ImageLength + VerticalMargin * 2, 1);
        try{
            constructBoard();
            run();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Prompt for and return the desired screen name.
     */
    private void getName() {
        /* return JOptionPane.showInputDialog(
        frame,
        "Choose a screen name:",
        "Screen name selection",
        JOptionPane.PLAIN_MESSAGE);
         */
    }

    public void constructBoard()
    {
        //Places out empty cells which will be played upon
        int xStartLocation = HorizontalMargin + ImageLength/2;
        int yStartLocation = VerticalMargin + ImageLength/2;

        for (int x = 0; x<=7;x++)
        {
            for(int y = 0; y<=7;y++)
            {
                cellList[x][y] = new ClientCell(x,y);
                addObject(cellList[x][y], x * ImageLength + xStartLocation, y * ImageLength + yStartLocation);
            }
        }

        cellList[3][4].setWhite();
        cellList[3][3].setBlack();        
        cellList[4][4].setBlack();
        cellList[4][3].setWhite();
    }

    public void play (int x, int y)
    {
        //Checks if the player can play
        if(black==gameRule.player)
        {
            //Checks if game is over
            if(!gameRule.winCondition())
                System.out.println("Game Over");
            GamePiece piece = new GamePiece(black,x,y);
            //Checks if the player can make this play
            if(gameRule.playable(piece)){
                gameRule.playPiece(piece);
                //Updates the visual board
                for (int row = 0; row<=7;row++)
                {
                    for(int col = 0; col<=7;col++)
                    {
                        if(gameRule.gameBoard[row][col]!=null)
                        {
                            if(gameRule.gameBoard[row][col].player==false)
                            {
                                cellList[row][col].setBlack();
                            }
                            else
                            {
                                cellList[row][col].setWhite();
                            }
                        }    
                    }
                }
                repaint();
                /*String text_x = String.valueOf(x);
                String text_y = String.valueOf(y);
                clientJson.put("x",text_y);
                clientJson.put("y",text_x);
                clientJson.put("player", black);
                out.println(clientJson);*/
                out.println(piece);
                waitForPlay();
            }
        }

    }
    public void waitForPlay()
    {
        try{
            String inputCommand = in.readLine();
            System.out.println(inputCommand);
            String[] inp = inputCommand.split(":");
            OpPlay(Integer.parseInt(inp[0]),Integer.parseInt(inp[1]),Boolean.parseBoolean(inp[2]));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Connects to the server then enters the processing loop.
     */
    private void run() throws IOException{

        // Make connection and initialize streams
        String serverAddress = "192.168.1.144";

        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.

        String inputCommand = in.readLine();
        System.out.println("Input:"+inputCommand);
        String[] inp = inputCommand.split(":");
        int val = Integer.parseInt(inp[0]);

        if(inp.length==1&& val == 2)
        {
            black = !black;
            System.out.println("Spelare två");
            String newInput = in.readLine();
            System.out.println(newInput);
            String[] newIn = newInput.split(":");
            OpPlay(Integer.parseInt(newIn[0]),Integer.parseInt(newIn[1]),Boolean.parseBoolean(newIn[2]));
        }
        else if(inp.length==1&& val == 1)
        {
            System.out.println("Spelare ett");
        }
    }

    public void OpPlay(int x, int y, boolean player)
    {

        /*JSONArray opData = opponent.getJSONArray("clientJson");
        final int n = opData.length();
        for (int i = 0; i < n; ++i) {
        final JSONObject pieceData = opData.getJSONObject(i);
        int x = pieceData.getInt("x");
        int y = pieceData.getInt("y");
        boolean player = pieceData.getBoolean("player");*/
        if(player==gameRule.player)
        {
            System.out.println("Inne i satsen");
            //Checks if game is over
            if(!gameRule.winCondition())
                System.out.println("Game Over");
            GamePiece piece = new GamePiece(player,x,y);
            gameRule.playPiece(piece);
            for (int row = 0; row<=7;row++)
            {
                for(int col = 0; col<=7;col++)
                {
                    if(gameRule.gameBoard[row][col]!=null)
                    {
                        if(gameRule.gameBoard[row][col].player==false)
                        {
                            cellList[row][col].setBlack();
                        }
                        else
                        {
                            cellList[row][col].setWhite();
                        }
                    }    
                }
            }

        }
    }

    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(){
        try{
            OthelloClient client = new OthelloClient();
            client.run();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}