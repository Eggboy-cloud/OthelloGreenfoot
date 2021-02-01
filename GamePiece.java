public class GamePiece extends GameLogic
{
    private int x;
    private int y;
    
    public GamePiece(boolean player)
    {
        this.player = player;
    }
    
    public GamePiece(boolean player,int x, int y)
    {
        this.player = player;
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
}
