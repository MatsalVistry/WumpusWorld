public class WumpusMain
{
    public static void main(String[]args)
    {
        WumpusMap wm = new WumpusMap();
        WumpusPanel wp = new WumpusPanel();
        System.out.print(wm.toString());
        new WumpusFrame("WumpusWorld");

    }
}
