public class WumpusSquare
{
    private boolean gold;
    private boolean ladder;
    private boolean pit;
    private boolean breeze;
    private boolean wumpus;
    private boolean deadWumpus;
    private boolean stench;
    private boolean visited;
    private boolean black;
    private boolean cheatVisited;

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public WumpusSquare() {
        gold=false;
        ladder=false;
        pit=false;
        breeze=false;
        wumpus=false;
        deadWumpus=false;
        stench=false;
        visited=false;
        black=true;
        cheatVisited=false;
    }

    public boolean isCheatVisited() {
        return cheatVisited;
    }

    public void setCheatVisited(boolean cheatVisited) {
        this.cheatVisited = cheatVisited;
    }

    public boolean isVisited() {

        return visited;

    }

    public boolean isPit() {
        return pit;
    }

    public boolean isBreeze() {
        return breeze;
    }

    public boolean isBlack() {
        return black;
    }

    public boolean isGold() {
        return gold;
    }

    public boolean isWumpus() { return wumpus;}

    public boolean isDeadWumpus() {
        return deadWumpus;
    }

    public boolean isStench() {
        return stench;
    }

    public boolean isLadder()
    {
        return ladder;
    }

    public void setGold(boolean gold)
    {
        this.gold = gold;
    }

    public void setLadder(boolean ladder)
    {
        this.ladder = ladder;
    }

    public void setPit(boolean pit) {
        this.pit = pit;
    }

    public void setBreeze(boolean breeze) {
        this.breeze = breeze;
    }

    public void setWumpus(boolean wumpus) {
        this.wumpus = wumpus;
    }

    public void setDeadWumpus(boolean deadWumpus) {
        this.deadWumpus = deadWumpus;
    }
    public void setBlack(boolean black){this.black = black;}

    public void setStench(boolean stench) {
        this.stench = stench;
    }
    @Override
    public String toString()
    {
        if(isWumpus()==true)
            return "W";
        if(isDeadWumpus()== true)
            return "D";
        if(isLadder() == true)
            return "L";
        if(isPit() == true)
            return "P";
        if(isGold() == true)
            return "G";
        else
            return "*";
    }
}