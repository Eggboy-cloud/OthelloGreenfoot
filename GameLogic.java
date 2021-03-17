
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
        System.out.println(row);
        System.out.println(col);
        if(gameBoard[row][col].player != piece.player && restriction(col) && restriction(row))
        {
            gameBoard[row][col] = piece;
            System.out.println();
            System.out.println("vänd");
            setPiece(row, col, direction, piece);
        }
        return true;
    }
    
    public void playPiece(int x,int y)
    {
       GamePiece piece = new GamePiece(player,x,y);
       if(playable(piece))
       {
           gameBoard[x][y] = piece;
           boolean hasOtherPieces = false;
           for(Pointer oneDir : validDirections)
           {
               hasOtherPieces = false;
               for(int row = piece.getX() + oneDir.horizontal, col = piece.getY() + oneDir.vertical; restriction(row) && restriction(col); row = row + oneDir.horizontal, col = col + oneDir.vertical)
               {

                    
                    if(gameBoard[row][col]==null)
                    {
                        System.out.println("null");
                        break;
                    }
                    else if(piece.player==gameBoard[row][col].player)
                    {    
                        if(hasOtherPieces)
                        {
                            System.out.println("Samma2");
                            row = piece.getX();
                            col = piece.getY();
                            if(setPiece(row,col,oneDir,piece))
                                break;
                        }   
                        break;
                    }
                    else
                    {    
                        System.out.println("Olik");
                        hasOtherPieces = true;
                        
                    }
               }
           }
           player = player ? false : true;
       }

    }

    public boolean playable(GamePiece piece)
    {
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
                            System.out.println(row+ " " + col);
                            System.out.println("yay");
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
        
}