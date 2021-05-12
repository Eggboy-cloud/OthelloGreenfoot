    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    
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
public class ChatClient extends World {

    public static final int StandardSideLength = 8;
    public static final int ImageLength = 55;
    public static final int VerticalMargin = 20;
    public static final int HorizontalMargin = 20;
    boolean black = false;
    GameLogic gameRule = new GameLogic(black);
    JSONObject clientJson = new JSONObject();
    private ClientCell[][] cellList = new ClientCell[8][8]; //Contains all positions on the board, empty, black or white.
    
    BufferedReader in;
    PrintWriter out;
    //JFrame frame = new JFrame("Chatter");
    //JTextField textField = new JTextField(40);
    //JTextArea messageArea = new JTextArea(8, 40);

    /**
     * Constructs the client by laying out the GUI and registering a listener with the
     * textfield so that pressing Return in the listener sends the textfield contents
     * to the server. Note however that the textfield is initially NOT editable, and
     * only becomes editable AFTER the client receives the NAMEACCEPTED message from
     * the server.
     */
    public ChatClient() {
        super(StandardSideLength * ImageLength + HorizontalMargin * 2, StandardSideLength * ImageLength + VerticalMargin * 2, 1);
        constructBoard();
        main();
        // Layout GUI
        //textField.setEditable(false);
        //messageArea.setEditable(false);
        //frame.getContentPane().add(textField, "North");
        //frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        //frame.pack();

        // Add Listeners
        /*textField.addActionListener(new ActionListener() {
            /**
             * Responds to pressing the enter key by sending the contents of the text
             * field to the server, then clear it in preparation for the next message.
             
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
        */
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
    
    public void play(int x, int y)
    {
        gameRule.playPiece(x,y,black);
        for (int row = 0; row<=7;row++)
        {
            for(int col = 0; col<=7;col++)
            {
                if(gameRule.gameBoard[row][col]!=null)
                {
                    if(gameRule.gameBoard[row][col].player==false)
                        cellList[row][col].setBlack();
                    else
                        cellList[row][col].setWhite();
                }    
            }
        }
        String text_x = String.valueOf(x);
        String text_y = String.valueOf(y);
        clientJson.put("x",text_y);
        clientJson.put("y",text_x);
    }

    /**
     * Connects to the server then enters the processing loop.
     */
    private void run() throws IOException, JSONException {

        // Make connection and initialize streams
        String serverAddress = "127.0.0.1";
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
        while (true) {
            String inputCommand = in.readLine();
            JSONObject opponent = new JSONObject(inputCommand);
            JSONArray opData = opponent.getJSONArray("clientJson");
            final int n = opData.length();
            for (int i = 0; i < n; ++i) {
                final JSONObject piece = opData.getJSONObject(i);
                int x = piece.getInt("x");
                int y = piece.getInt("y");
                boolean player = piece.getBoolean("player");
                gameRule.playPiece(x,y,player);
            }
            
            
            out.println(clientJson);
        }
        
    }
    

    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(){
        try{
        ChatClient client = new ChatClient();
        //client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //client.frame.setVisible(true);
        client.run();
    }
        catch(Exception e){
        e.printStackTrace();
        }
    }
}