import greenfoot.*;
public class Menu extends World  
{
    // instance variables - replace the example below with your own
    private Graphics solo;
    private Graphics local;

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
        
        local = new Graphics(new GreenfootImage("Online Local", 50, Color.BLACK, null), false );
        addObject(local, halfX, halfY + 120);
    }
    
        public void act()
    {
        if( Greenfoot.mouseClicked(solo) )
        {
            Greenfoot.setWorld(new OthelloWorld());
        }
        
        if( Greenfoot.mouseClicked(local) )
        {
            Greenfoot.setWorld(new OthelloClient());
        }
    }
}
