
public class GameLogic  
{
    public boolean player;
    int side = 8;    
    GamePiece[][]gameBoard = new GamePiece[side][side];
    
    Pointer[] validDirections = new Pointer[]{
        new Pointer(1,0),
        new Pointer(1,1),
        new Pointer(1,-1),
        new Pointer(0,1),
        new Pointer(0,-1),
        new Pointer(-1,0),
        new Pointer(-1,1),
        new Pointer(-1,-1)
    };   
    public GameLogic(boolean currentPlayer)
    {
        this.player = currentPlayer;
        setWorld();
    }
    
    public GameLogic()
    {
    }
    
    public boolean restriction(int coord)
    {
        return coord >= 0 && coord < side;
    }
    
    public void setWorld()
    {
        gameBoard[(side/2)-1][(side/2)-1] = new GamePiece(true);
        gameBoard[(side/2)][(side/2)-1] = new GamePiece(false);
        gameBoard[(side/2)-1][(side/2)] = new GamePiece(false);
        gameBoard[(side/2)][(side/2)] = new GamePiece(true);
    }
    
    public boolean setPiece(Pointer direction,GamePiece piece)
    {
        int row = piece.getX() + direction.horizontal;
        int col = piece.getY() + direction.vertical;
        if(gameBoard[piece.getX()+row][piece.getY()+col] != piece)
        {
            row = row + direction.horizontal;
            col = col + direction.vertical;
            gameBoard[piece.getX()+row][piece.getY()+col] = piece;
        }
        else
            return true;
        return true;
    }
    
    public void playPiece(int x,int y)
    {
       GamePiece piece = new GamePiece(player,x,y);
       if(playable(piece))
       {
           for(Pointer oneDir : validDirections)
           {
               for(int row = piece.getX() + oneDir.horizontal, col = piece.getY() + oneDir.vertical; restriction(row) && restriction(col); row = row + oneDir.horizontal, col = col + oneDir.vertical)
               {
                    boolean hasOtherPieces = false;
                    if(gameBoard[row][col]==null)
                        break;
                    else if(piece.player==gameBoard[row][col].player)
                    {    
                        if(hasOtherPieces)
                        {
                            if(setPiece(oneDir,piece))
                                break;
                        }   
                        break;
                    }
                    else
                    {    
                        hasOtherPieces = true;
                        continue;
                    }
               }
           }
       }
    }
    
    public boolean playable(GamePiece piece)
    {
        for(Pointer oneDir : validDirections)
        {
            for(int row = piece.getX() + oneDir.horizontal, col = piece.getY() + oneDir.vertical; restriction(row) && restriction(col); row = row + oneDir.horizontal, col = col + oneDir.vertical)
            {
                boolean hasOtherPieces = false;
                if(gameBoard[row][col]==null)
                    break;
                else if(piece.player==gameBoard[row][col].player)
                {    
                    if(hasOtherPieces)
                        return true;
                    break;
                }
                else
                {    
                    hasOtherPieces = true;
                    continue;
                }
            }
        }
        return false;
    }
    
}