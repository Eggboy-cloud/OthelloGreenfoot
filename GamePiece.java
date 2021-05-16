public class GamePiece
{
    private int x;
    private int y;
    public boolean player;
    public static int pointsWhite = 2;
    public static int pointsBlack = 2;

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

    public GamePiece()
    {
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void turnPiece()
    {
        player = !player;
        if(player==false)
        {
            pointsBlack++;
            pointsWhite--;
        }
        else
        {
            pointsBlack--;
            pointsWhite++;
        }
    }

    public String toString(){
        return String.valueOf(x)+":"+String.valueOf(y)+":"+String.valueOf(player);
    }
}
