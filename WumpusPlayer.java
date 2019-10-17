public class WumpusPlayer
{
    public static final int NORTH=0;
    public static final int EAST=1;
    public static final int SOUTH=2;
    public static final int WEST=3;

    private int direction;
    private boolean arrow;
    private boolean gold;
    private int colPosition;
    private int rowPosition;

    public WumpusPlayer()
    {
        direction = NORTH;
        gold = false;
        arrow = true;

    }

    public int getDirection() {
        return direction;
    }
    public boolean getGold()
    {
        return gold;
    }
    public boolean getArrow()
    {
        return arrow;
    }

    public boolean isArrow() {
        return arrow;
    }

    public boolean isGold() {
        return gold;
    }

    public int getColPosition() {
        return colPosition;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setArrow(boolean arrow) {
        this.arrow = arrow;
    }

    public void setGold(boolean gold) {
        this.gold = gold;
    }

    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }
}