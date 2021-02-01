    import greenfoot.*;
    
    public class Cell extends Actor
    {
        
        public int x;
        public int y;
    //    GreenfootImage black = new GreenfootImage("black.gif");
    //    GreenfootImage image = black.getImages();
    //    image.scale(30,30);
        
    public Cell(int x,int y)
    {
        x = this.x;
        y = this.y;
    }
    
    public void act() 
    {
    
    }
    
    public void setBlack()
    {
        GreenfootImage black = new GreenfootImage("black.gif");
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
