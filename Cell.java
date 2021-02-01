    import greenfoot.*;    
    public class Cell extends Graphics
    {
        
        public int x;
        public int y;
        
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
