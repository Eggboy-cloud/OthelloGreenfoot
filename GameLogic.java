public class GameLogic  
{
    public boolean player;
    
    public void setWorld()
    {
        int x = 8;
        int y = 8;
        
        GamePiece[][]gameBoard = new GamePiece[x][y];
        gameBoard[(x/2)-1][(y/2)-1] = new GamePiece(true);
        gameBoard[(x/2)][(y/2)-1] = new GamePiece(false);
        gameBoard[(x/2)-1][(y/2)] = new GamePiece(false);
        gameBoard[(x/2)][(y/2)] = new GamePiece(true);
    }
    
    public void setPiece(int x,int y)
    {
        if
    }
}
