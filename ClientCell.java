
import greenfoot.*;    
public class ClientCell extends Actor
{
    public int x; // X cordinate
    public int y; // y cordinate

    public ClientCell(int x,int y)
    {
        x = this.x;
        y = this.y;
    }

    public void act () 
    {
        if(Greenfoot.mouseClicked(this)){
            ChatClient world = (ChatClient)getWorld();
            world.play(((getX()-47)/55),((getY()-47)/55)); }
    }

    

    public void setBlack()
    {
        GreenfootImage black = new GreenfootImage("black.png");
        black.scale(50,50);
        setImage(black);
    }

    public void setWhite()
    {
        GreenfootImage white = new GreenfootImage("white.png");
        white.scale(50,50);
        setImage(white);
    }
}
