import greenfoot.*;
public class Menu extends World  
{
    // instance variables - replace the example below with your own
    private Graphics solo;
    

    /**
     * Constructor for objects of class Menu
     */
    public Menu()
    {
        super(600,400,1);
        
        int halfX = getWidth()/2;
        int halfY = getHeight()/2;
        
        addObject(new Graphics(new GreenfootImage("Othello", 80, Color.BLACK, null), false), halfX, halfY - 100 );
        
        solo = new Graphics(new GreenfootImage("Play Game", 50, Color.BLACK, null), false );
        addObject(solo, halfX, halfY + 50);
    }
    
        public void act()
    {
        if( Greenfoot.mouseClicked(solo) )
        {
            Greenfoot.setWorld(new OthelloWorld());
        }
    }
}
