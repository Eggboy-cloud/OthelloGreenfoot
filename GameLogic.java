public class GameLogic  
{
    public boolean player;
    int side = 8;    
    GamePiece[][]gameBoard = new GamePiece[side][side];
    
    public void setWorld()
    {
        gameBoard[(side/2)-1][(side/2)-1] = new GamePiece(true);
        gameBoard[(side/2)][(side/2)-1] = new GamePiece(false);
        gameBoard[(side/2)-1][(side/2)] = new GamePiece(false);
        gameBoard[(side/2)][(side/2)] = new GamePiece(true);
    }
    
    public void setPiece(int x,int y)
    {
        gameBoard[x][y] = new GamePiece(player);
    }
    
    public void playPiece(int x,int y)
    {
        
    }
}
