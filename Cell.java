    import greenfoot.*;    
    public class Cell extends Graphics
    {
      public int x; // X cordinate
      public int y; // y cordinate
      OthelloWorld world = (OthelloWorld)getWorld();  
    public Cell(int x,int y)
    {
        x = this.x;
        y = this.y;
    }
    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            System.out.println("X Coord: " + (getX()-47)/55 + " Y Coord: " +(getY()-47)/55);
            world.play( ((getX()-47)/55),((getY()-47)/55),true);
        }
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
