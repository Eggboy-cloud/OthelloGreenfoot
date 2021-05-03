import greenfoot.*;
/**
 * Write a description of class Grafik here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Graphics extends Actor 
{
    private GreenfootImage display_;
    private int size_;
    private Color wordColour_;
    private Color backColour_;
    
    private final int DEFAULT_SIZE_ = 36;
    
    private boolean animate_;
    private boolean empty_;
    private int flashCount_;
    
    private final GreenfootImage CLEAR_IMAGE_ = new GreenfootImage(1, 1);
    private final int FLASH_DELAY_ = 40;
    
        /**
     * Creates a visual object with a given GreenfootImage.
     * Can choose whether or not if you want it to flash.
     */
    public Graphics(GreenfootImage img, boolean animate)
    {
        display_ = img;
        animate_ = animate;
        setImage(display_);
        empty_ = false;
    }
    
    public void act()
    {
        if( animate_ )
        {
            if( flashCount_ % FLASH_DELAY_ == 0 )
            {
                if( !empty_ )
                {
                    setImage(CLEAR_IMAGE_);
                    empty_ = true;
                }
                else if( empty_ )
                {
                    setImage(display_);
                    empty_ = false;
                }
            }
            flashCount_ ++;
        }
    }
}
