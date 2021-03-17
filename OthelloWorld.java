import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // Java library

public class OthelloWorld extends World
{
    MouseInfo mouse = Greenfoot.getMouseInfo();
    
    //Values used when building the Greenfoot World and when placing out the empty cells
    public static final int StandardSideLength = 8;
    public static final int ImageLength = 55;
    public static final int VerticalMargin = 20;
    public static final int HorizontalMargin = 20;
    boolean black = false;
    GameLogic gameRule = new GameLogic(black);
    
    private Cell[][] cellList = new Cell[8][8]; //Contains all positions on the board, empty, black or white.
    public OthelloWorld()
    {  
        super(StandardSideLength * ImageLength + HorizontalMargin * 2, StandardSideLength * ImageLength + VerticalMargin * 2, 1);
        constructBoard();
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
                    cellList[x][y] = new Cell(x,y);
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
        gameRule.playPiece(x,y);
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
    }
    
    public void updateWorld(int x,int y)
    {
        
    }
}
