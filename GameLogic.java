
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
        gameBoard[(side/2)-1][(side/2)-1] = new GamePiece(false);
        gameBoard[(side/2)][(side/2)-1] = new GamePiece(true);
        gameBoard[(side/2)-1][(side/2)] = new GamePiece(true);
        gameBoard[(side/2)][(side/2)] = new GamePiece(false);
    }

    public boolean setPiece(int row, int col, Pointer direction,GamePiece piece)
    {
        row = row + direction.horizontal;
        col = col + direction.vertical;
        if(gameBoard[row][col].player != piece.player && restriction(col) && restriction(row))
        {
            System.out.println("Placerar pjäs på: " +row+","+col);
            gameBoard[row][col].turnPiece();
            setPiece(row, col, direction, piece);
        }
        return true;
    }

    public void playPiece(GamePiece piece)
    {
        gameBoard[piece.getX()][piece.getY()] = piece;
        boolean hasOtherPieces = false;
        System.out.println("Pjäs: "+piece);
        for(Pointer oneDir : validDirections)
        {
            hasOtherPieces = false;
            for(int row = piece.getX() + oneDir.horizontal, col = piece.getY() + oneDir.vertical; restriction(row) && restriction(col); row = row + oneDir.horizontal, col = col + oneDir.vertical)
            {

                if(gameBoard[row][col]==null)
                {
                    break;
                }
                else if(piece.player==gameBoard[row][col].player)
                {    
                    if(hasOtherPieces)
                    {
                        row = piece.getX();
                        col = piece.getY();
                        if(setPiece(row,col,oneDir,piece))
                            break;
                    }   
                    break;
                }
                else
                {
                    hasOtherPieces = true;
                }
            }
        }
        player = !player;
    }

    public boolean playable(GamePiece piece)
    {
        if(testEmptyCell(piece))
            return false;
        System.out.println("Cellen är tom");
        boolean hasOtherPieces = false;
        for(Pointer oneDir : validDirections)
        {
            hasOtherPieces = false;
            for(int row = piece.getX() + oneDir.horizontal, col = piece.getY() + oneDir.vertical; restriction(row) && restriction(col); row = row + oneDir.horizontal, col = col + oneDir.vertical)
            {

                if(gameBoard[row][col]==null)
                {
                    break;
                }
                else if(piece.player==gameBoard[row][col].player)
                {    
                    if(hasOtherPieces)
                    {
                        return true;
                    }
                    break;
                }
                else
                {    
                    hasOtherPieces = true;
                }
            }
        }
        return false;
    }

    public boolean winCondition()
    {
        boolean hasOtherPieces = false;
        for(int i=0; i<gameBoard.length; i++) {
            for(int j=0; j<gameBoard[i].length; j++) {
                if(gameBoard[i][j]!=null)
                    break;
                gameBoard[i][j] = new GamePiece(player,i,j);
                for(Pointer oneDir : validDirections)
                {
                    hasOtherPieces = false;
                    for(int row = gameBoard[i][j].getX() + oneDir.horizontal, col = gameBoard[i][j].getY() + oneDir.vertical; restriction(row) && restriction(col); row = row + oneDir.horizontal, col = col + oneDir.vertical)
                    {

                        if(gameBoard[row][col]==null)
                        {
                            break;
                        }
                        else if(gameBoard[i][j].player==gameBoard[row][col].player)
                        {    
                            if(hasOtherPieces)
                            {
                                gameBoard[i][j] = null;
                                return true;
                            }
                            break;
                        }
                        else
                        {    
                            hasOtherPieces = true;
                        }
                    }
                }
                gameBoard[i][j] = null;
            }
        }
        return false;
    }

    public boolean testEmptyCell(GamePiece piece)
    {
        return gameBoard[piece.getX()][piece.getY()]!=null;
    }
}