import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // Java library
/**
 * Contains the Code for the Gameboard
 * 
 * @Egil Guting 
 * @2021-18-01
 */
public class OthelloWorld extends World
{
    MouseInfo mouse = Greenfoot.getMouseInfo();
    
    //Values used when building the Greenfoot World and when placing out the empty cells
    public static final int STANDARD_SIDE_LENGTH_ = 8;
    public static final int IMAGE_LENGTH_ = 55;
    public static final int VERTICAL_MARGIN_ = 20;
    public static final int HORIZONTAL_MARGIN_ = 20;
    
    private Cell[][] cellList = new Cell[8][8]; //Contains all positions on the board, empty, black or white.
    public OthelloWorld()
    {  
        super(STANDARD_SIDE_LENGTH_ * IMAGE_LENGTH_ + HORIZONTAL_MARGIN_ * 2, STANDARD_SIDE_LENGTH_ * IMAGE_LENGTH_ + VERTICAL_MARGIN_ * 2, 1);
        constructBoard();
    }
    
    public void act()
    {
        
    }
    
    public void constructBoard()
    {
        //Places out empty cells which will be played upon
        int xStartLocation = HORIZONTAL_MARGIN_ + IMAGE_LENGTH_/2;
        int yStartLocation = VERTICAL_MARGIN_ + IMAGE_LENGTH_/2;
        
        for (int x = 0; x<=7;x++)
            {
                for(int y = 0; y<=7;y++)
                {
                    cellList[x][y] = new Cell(x,y);
                    addObject(cellList[x][y], x * IMAGE_LENGTH_ + xStartLocation, y * IMAGE_LENGTH_ + yStartLocation);
                }
            }
            
        cellList[3][4].setWhite();
        cellList[3][3].setBlack();        
        cellList[4][4].setBlack();
        cellList[4][3].setWhite();  
        
    }
}
