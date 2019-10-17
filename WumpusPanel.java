
        import javax.imageio.ImageIO;
        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.KeyEvent;
        import java.awt.event.KeyListener;
        import java.awt.image.BufferedImage;
        import java.io.File;
public class WumpusPanel extends JPanel implements KeyListener
{
    private BufferedImage floor = null;
    private BufferedImage arrow = null;
    private BufferedImage fog = null;
    private BufferedImage gold = null;
    private BufferedImage ladder = null;
    private BufferedImage pit = null;
    private BufferedImage breeze = null;
    private BufferedImage wumpus = null;
    private BufferedImage black = null;
    private BufferedImage deadWumpus = null;
    private BufferedImage stench = null;
    private BufferedImage playerUp = null;
    private BufferedImage playerDown = null;
    private BufferedImage playerLeft = null;
    private BufferedImage playerRight = null;

    public static final int PLAYING=0;
    public static final int DEAD=1;
    public static final int WON=2;

    private int status;
    //private WumpusPlayer player;
    // private WumpusMap map;
    private WumpusMap wm;
    private WumpusPlayer wp;
    private WumpusSquare ws;

    private int w=1;
    private int ww=0;
    private int h=1;
    private int d=0;
    private int f=0;
    private int ff=0;
    private int a=0;


    public WumpusPanel()
    {
        super();
        setSize(500,615);
        wm = new WumpusMap();
        wp = new WumpusPlayer();
        ws = new WumpusSquare();

        status = PLAYING;

        addKeyListener(this);
        try
        {
            floor = ImageIO.read((new File("Images\\Floor.gif")));
            ladder = ImageIO.read((new File("Images\\ladder.gif")));
            arrow = ImageIO.read((new File("Images\\arrow.gif")));
            breeze = ImageIO.read((new File("Images\\breeze.gif")));
            gold = ImageIO.read((new File("Images\\gold.gif")));
            pit = ImageIO.read((new File("Images\\pit.gif")));
            wumpus = ImageIO.read((new File("Images\\wumpus.gif")));
            deadWumpus = ImageIO.read((new File("Images\\deadwumpus.gif")));
            stench = ImageIO.read((new File("Images\\stench.gif")));
            black = ImageIO.read((new File("Images\\black.gif")));
            playerUp = ImageIO.read((new File("Images\\playerUp.png")));
            playerDown = ImageIO.read((new File("Images\\playerDown.png")));
            playerLeft = ImageIO.read((new File("Images\\playerLeft.png")));
            playerRight = ImageIO.read((new File("Images\\playerRight.png")));
        }
        catch(Exception e)
        {
            System.out.println("Error Loading Images: " + e.getMessage());
        }
    }
    public void reset()
    {

        wm.createMap();
        wp.setRowPosition(wm.getLadderC());
        wp.setColPosition(wm.getLadderR());
        status=PLAYING;
        wp.setGold(false);
        wp.setArrow(true);
        h=1;
        d=0;

    }

    public int getW() {
        return w;
    }

    public int getWw() {
        return ww;
    }

    public void paint(Graphics g)
    {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);
        g.setColor(Color.WHITE);
        g.fillRect(0, 500, 500, 115);
        for (int x = 0; x < 500; x += 50) {
            for (int y = 0; y < 500; y += 50) {
                //    if(wm.getSquare(x/50,y/50).isVisited())
                System.out.print(g.drawImage(floor, x, y, null));
            }
        }

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {


                if (wm.getSquare(x, y).isPit())
                    System.out.print(g.drawImage(pit, x * 50, y * 50, null));

                if (wm.getSquare(x, y).isBreeze())
                    System.out.print(g.drawImage(breeze, x * 50, y * 50, null));


                if (wm.getSquare(x, y).isWumpus() )
                    System.out.print(g.drawImage(wumpus, x * 50, y * 50, null));

                if (wm.getSquare(x, y).isDeadWumpus() )
                    System.out.print(g.drawImage(deadWumpus, x * 50, y * 50, null));

                if (wm.getSquare(x, y).isStench())
                    System.out.print(g.drawImage(stench, x * 50, y * 50, null));


                if (wm.getSquare(x, y).isLadder()) {
                    System.out.print(g.drawImage(ladder, x * 50, y * 50, null));
                    if (w == 1) {
                        wp.setColPosition(y);
                        wp.setRowPosition(x);
                        w--;
                    }
                }

                if (wm.getSquare(x, y).isGold() && h == 1)
                    System.out.print(g.drawImage(gold, x * 50, y * 50, null));


            }
        }
        if (wp.getDirection() == wp.NORTH) {
            System.out.print(g.drawImage(playerUp, wp.getRowPosition() * 50, wp.getColPosition() * 50, null));
            wm.getSquare(wp.getRowPosition(), wp.getColPosition()).setBlack(false);
            wm.getSquare(wp.getRowPosition(), wp.getColPosition()).setVisited(true);
        }
        if (wp.getDirection() == wp.WEST) {
            System.out.print(g.drawImage(playerLeft, wp.getRowPosition() * 50, wp.getColPosition() * 50, null));
            wm.getSquare(wp.getRowPosition(), wp.getColPosition()).setBlack(false);
            wm.getSquare(wp.getRowPosition(), wp.getColPosition()).setVisited(true);

        }
        if (wp.getDirection() == wp.EAST) {
            System.out.print(g.drawImage(playerRight, wp.getRowPosition() * 50, wp.getColPosition() * 50, null));
            wm.getSquare(wp.getRowPosition(), wp.getColPosition()).setBlack(false);
            wm.getSquare(wp.getRowPosition(), wp.getColPosition()).setVisited(true);
        }
        if (wp.getDirection() == wp.SOUTH) {
            System.out.print(g.drawImage(playerDown, wp.getRowPosition() * 50, wp.getColPosition() * 50, null));
            wm.getSquare(wp.getRowPosition(), wp.getColPosition()).setBlack(false);
            wm.getSquare(wp.getRowPosition(), wp.getColPosition()).setVisited(true);
        }
        for (int d = 0; d < 10; d++) {
            for (int dd = 0; dd < 10; dd++) {
                if (wm.getSquare(d, dd).isBlack() == true) {
                    System.out.print(g.drawImage(black, d * 50, dd * 50, null));
                }
            }
        }
        g.setColor(Color.RED);
        g.drawString("Messages:", 75, 510);
        g.drawString("Inventory:", 400, 510);

        g.setColor(Color.BLUE);
        //   Font f = new Font("Ariel",Font.PLAIN,500);

        if (wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isBreeze()) {
            g.drawString("You feel a breeze", 50, 525);
        }
        if (wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isStench() || wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isDeadWumpus()) {
            g.drawString("You smell a stench", 50, 550);
        }
        if (wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isGold() && wp.getGold() == false) {
            g.drawString("You see a glimmer", 50, 575);
        }
        if (wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isLadder()) {
            g.drawString("You bump into a ladder", 50, 595);
        }


        if (!wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isBreeze() && !wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isStench() && !wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isGold()  && !wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isLadder() && !wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isDeadWumpus()) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 500, 250, 115);
            g.setColor(Color.RED);
            g.drawString("Messages:", 75, 510);
        }
        if (h == 1) {
            if (wp.getGold() == true) {
                System.out.print(g.drawImage(floor, wp.getRowPosition() * 50, wp.getColPosition() * 50, null));
                if (wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isBreeze()) {
                    System.out.print(g.drawImage(breeze, wp.getRowPosition() * 50, wp.getColPosition() * 50, null));
                }
                if (wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isStench()) {
                    System.out.print(g.drawImage(stench, wp.getRowPosition() * 50, wp.getColPosition() * 50, null));
                }
                if (wp.getDirection() == wp.NORTH) {
                    System.out.print(g.drawImage(playerUp, wp.getRowPosition() * 50, wp.getColPosition() * 50, null));
                } else if (wp.getDirection() == wp.EAST) {
                    System.out.print(g.drawImage(playerRight, wp.getRowPosition() * 50, wp.getColPosition() * 50, null));
                } else if (wp.getDirection() == wp.SOUTH) {
                    System.out.print(g.drawImage(playerDown, wp.getRowPosition() * 50, wp.getColPosition() * 50, null));
                } else if (wp.getDirection() == wp.WEST) {
                    System.out.print(g.drawImage(playerLeft, wp.getRowPosition() * 50, wp.getColPosition() * 50, null));
                }
                h--;
            }
        }
        if (d == 1) {
            wp.setArrow(false);
            for(int x=0;x<10;x++)
            {
                if(wp.getColPosition()-x>=0) {
                    if (wm.getSquare(wp.getRowPosition(), wp.getColPosition() - x).isWumpus()) {
                        System.out.print(g.drawImage(floor, wp.getRowPosition() * 50, (wp.getColPosition() - x) * 50, null));
                        wm.getSquare(wp.getRowPosition(), wp.getColPosition() - x).setDeadWumpus(true);
                        wm.getSquare(wp.getRowPosition(), wp.getColPosition() - x).setWumpus(false);
                        if (wm.getSquare(wp.getRowPosition(), wp.getColPosition() - x).isBreeze()) {
                            System.out.print(g.drawImage(breeze, wp.getRowPosition() * 50, (wp.getColPosition() - x) * 50, null));
                        }
                        if (wm.getSquare(wp.getRowPosition(), wp.getColPosition() - 1).isStench()) {
                            System.out.print(g.drawImage(stench, wp.getRowPosition() * 50, (wp.getColPosition() - x) * 50, null));
                        }
                        System.out.print(g.drawImage(deadWumpus, wp.getRowPosition() * 50, (wp.getColPosition() - x) * 50, null));
                        f = wp.getRowPosition();
                        ff = (wp.getColPosition() - x);
                        d = 5;
                        g.drawString("You hear a scream", 50, 615);

                        System.out.print(g.drawImage(black, wp.getRowPosition() * 50, (wp.getColPosition() - x) * 50, null));

                        break;
                    }
                }

            }
            d = 6;
        }
        if (d == 2) {
            wp.setArrow(false);
            for(int x=0;x<10;x++) {
                if (wp.getColPosition() + x <=9) {
                    if (wm.getSquare(wp.getRowPosition(), wp.getColPosition() + x).isWumpus()) {
                        System.out.print(g.drawImage(floor, wp.getRowPosition() * 50, (wp.getColPosition() + x) * 50, null));
                        wm.getSquare(wp.getRowPosition(), wp.getColPosition() + x).setDeadWumpus(true);
                        wm.getSquare(wp.getRowPosition(), wp.getColPosition() + x).setWumpus(false);
                        if (wm.getSquare(wp.getRowPosition(), wp.getColPosition() + x).isBreeze()) {
                            System.out.print(g.drawImage(breeze, wp.getRowPosition() * 50, (wp.getColPosition() + x) * 50, null));
                        }
                        if (wm.getSquare(wp.getRowPosition(), wp.getColPosition() + x).isStench()) {
                            System.out.print(g.drawImage(stench, wp.getRowPosition() * 50, (wp.getColPosition() + x) * 50, null));
                        }
                        System.out.print(g.drawImage(deadWumpus, wp.getRowPosition() * 50, (wp.getColPosition() + x) * 50, null));
                        f = wp.getRowPosition();
                        ff = (wp.getColPosition() + x);
                        d = 5;
                        g.drawString("You hear a scream", 50, 615);
                        System.out.print(g.drawImage(black, wp.getRowPosition() * 50, (wp.getColPosition() + x) * 50, null));
                        break;

                    }
                }
            }
            d = 6;
        }
        if (d == 3) {
            wp.setArrow(false);
            for(int x=0;x<10;x++) {
                if (wp.getRowPosition() - x >=0) {

                    if (wm.getSquare(wp.getRowPosition() - x, wp.getColPosition()).isWumpus()) {
                        System.out.print(g.drawImage(floor, (wp.getRowPosition() - x) * 50, wp.getColPosition() * 50, null));
                        wm.getSquare((wp.getRowPosition() - x), wp.getColPosition()).setDeadWumpus(true);
                        wm.getSquare((wp.getRowPosition() - x), wp.getColPosition()).setWumpus(false);
                        if (wm.getSquare((wp.getRowPosition() - x), wp.getColPosition()).isBreeze()) {
                            System.out.print(g.drawImage(breeze, (wp.getRowPosition() - x) * 50, wp.getColPosition() * 50, null));
                        }
                        if (wm.getSquare(wp.getRowPosition() - x, wp.getColPosition()).isStench()) {
                            System.out.print(g.drawImage(stench, (wp.getRowPosition() - x) * 50, wp.getColPosition() * 50, null));
                        }
                        System.out.print(g.drawImage(deadWumpus, (wp.getRowPosition() - x) * 50, wp.getColPosition() * 50, null));
                        f = wp.getRowPosition() - x;
                        ff = wp.getColPosition();
                        d = 5;
                        g.drawString("You hear a scream", 50, 615);
                        System.out.print(g.drawImage(black, (wp.getRowPosition() - x) * 50, wp.getColPosition() * 50, null));

                        break;
                    }
                }
            }
            d = 6;
        }
        if (d == 4) {
            wp.setArrow(false);
            for(int x=0;x<10;x++) {
                if (wp.getRowPosition() + x<=9) {
                    if (wm.getSquare(wp.getRowPosition() + x, wp.getColPosition()).isWumpus()) {
                        System.out.print(g.drawImage(floor, (wp.getRowPosition() + x) * 50, wp.getColPosition() * 50, null));
                        wm.getSquare((wp.getRowPosition() + x), wp.getColPosition()).setDeadWumpus(true);
                        wm.getSquare((wp.getRowPosition() + x), wp.getColPosition()).setWumpus(false);
                        if (wm.getSquare((wp.getRowPosition() + x), wp.getColPosition()).isBreeze()) {
                            System.out.print(g.drawImage(breeze, (wp.getRowPosition() + x) * 50, wp.getColPosition() * 50, null));
                        }
                        if (wm.getSquare(wp.getRowPosition() + x, wp.getColPosition()).isStench()) {
                            System.out.print(g.drawImage(stench, (wp.getRowPosition() + x) * 50, wp.getColPosition() * 50, null));
                        }
                        System.out.print(g.drawImage(deadWumpus, (wp.getRowPosition() + x) * 50, wp.getColPosition() * 50, null));
                        f = wp.getRowPosition() + x;
                        ff = wp.getColPosition();
                        d = 5;
                        g.drawString("You hear a scream", 50, 615);
                        System.out.print(g.drawImage(black, (wp.getRowPosition() + x) * 50, wp.getColPosition() * 50, null));

                        break;
                    }
                }
            }
            d = 6;
        }
        if (d == 5) {
            System.out.print(g.drawImage(deadWumpus, f * 50, ff * 50, null));
            wm.getSquare(f, ff).setDeadWumpus(true);
            wm.getSquare(f, ff).setWumpus(false);
        }
        if (wp.getArrow() == true) {
            System.out.print(g.drawImage(arrow, 450, 550, null));
        }
        if (wp.getGold() == true) {
            System.out.print(g.drawImage(gold, 400, 550, null));
        }


        if (status == DEAD && wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isPit()) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 500, 250, 100);
            g.setColor(Color.BLUE);

            g.drawString("You fell down a pit to your death. Game Over.(N for new game)", 50, 550);
            g.setColor(Color.RED);
            g.drawString("Messages:", 75, 510);
        }
        if (status == DEAD && wm.getSquare(wp.getRowPosition(), wp.getColPosition()).isWumpus()) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 500, 250, 100);
            g.setColor(Color.BLUE);
            g.drawString("You are eaten by the Wumpus. Game Over.(N for new game)", 50, 550);
            g.setColor(Color.RED);
            g.drawString("Messages:", 75, 510);
        }
        if (status == WON) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 500, 250, 100);
            g.setColor(Color.BLUE);
            g.drawString("You Win.(N for new game)", 50, 525);
            g.setColor(Color.RED);
            g.drawString("Messages:", 75, 510);
        }

    }
    public void keyTyped(KeyEvent e)
    {

    }
    public void keyPressed(KeyEvent e)
    {
        //  g.setColor(Color.BLUE);
        //    Font f = new Font("Ariel",Font.PLAIN,15);
    /*    for(s=0;s<10;s++)
        {
            for(int ss=0;ss<10;ss++)
            {
                if(wm.getSquare(s,ss).isWumpus() &&wm.getSquare(s,ss).isP)
            }
        }*/
        if(status==PLAYING)
        {
            if (e.getKeyChar() == 'w')
            {
                wp.setDirection(wp.NORTH);
                if (wp.getColPosition() - 1 >= 0)
                    wp.setColPosition(wp.getColPosition() - 1);
                if(wm.getSquare(wp.getRowPosition(),wp.getColPosition()).isWumpus() && d!=5|| wm.getSquare(wp.getRowPosition(),wp.getColPosition()).isPit()) {

                    status = DEAD;
                    repaint();
                }
          /*      if(wm.getSquare(wp.getRowPosition(),wp.getColPosition()).isBreeze())
                {
                    g.drawString("Breeze",500,500);
                }*/
            }
            if (e.getKeyChar() == 's') {
                wp.setDirection(wp.SOUTH);
                if (wp.getColPosition() + 1 <= 9)
                    wp.setColPosition(wp.getColPosition() + 1);
                if(wm.getSquare(wp.getRowPosition(),wp.getColPosition()).isWumpus() && d!=5|| wm.getSquare(wp.getRowPosition(),wp.getColPosition()).isPit()) {
                    status = DEAD;
                    repaint();

                }

            }
            if (e.getKeyChar() == 'a') {
                wp.setDirection(wp.WEST);
                if (wp.getRowPosition() - 1 >= 0)
                    wp.setRowPosition(wp.getRowPosition() - 1);
                if(wm.getSquare(wp.getRowPosition(),wp.getColPosition()).isWumpus() && d!=5|| wm.getSquare(wp.getRowPosition(),wp.getColPosition()).isPit()) {
                    status = DEAD;
                    repaint();

                }
            }
            if (e.getKeyChar() == 'd') {
                wp.setDirection(wp.EAST);
                if (wp.getRowPosition() + 1 <= 9)
                    wp.setRowPosition(wp.getRowPosition() + 1);
                if(wm.getSquare(wp.getRowPosition(),wp.getColPosition()).isWumpus() && d!=5|| wm.getSquare(wp.getRowPosition(),wp.getColPosition()).isPit()) {
                    status = DEAD;
                    repaint();

                }
            }
            if(e.getKeyChar() == 'p' && wm.getSquare(wp.getRowPosition(),wp.getColPosition()).isGold())
            {
                wp.setGold(true);
            }

            if( wp.getArrow() == true && e.getKeyChar() == 'i')
            {
                d=1;
            }
            if( wp.getArrow() == true && e.getKeyChar() == 'k')
            {
                d=2;
            }
            if( wp.getArrow() == true && e.getKeyChar() == 'j')
            {
                d=3;
            }
            if( wp.getArrow() == true && e.getKeyChar() == 'l')
            {
                d=4;
            }
            if(e.getKeyChar() == '*')
            {
         /*      if(a%2==0)
                {
                    for(int x=0;x<10;x++)
                    {
                        for(int y=0;y<10;y++)
                        {
                            if(wm.getSquare(x,y).isBlack())
                            {
                                wm.getSquare(x,y).setBlack(false);
                            }
                        }
                    }
                }
                else
                {
                    for(int x=0;x<10;x++)
                    {
                        for(int y=0;y<10;y++)
                        {
                            if(!wm.getSquare(x,y).isVisited())
                            {
                                wm.getSquare(x,y).setBlack(true);
                            }
                        }
                    }
                }*/
                if(a%2==0)
                {
                    for(int x=0;x<10;x++)
                    {
                        for(int y=0;y<10;y++)
                        {
                            if(wm.getSquare(x,y).isBlack())
                            {
                                wm.getSquare(x,y).setBlack(false);
                            }
                        }
                    }
                }
                else
                {
                    for(int x=0;x<10;x++)
                    {
                        for(int y=0;y<10;y++)
                        {
                            if(!wm.getSquare(x,y).isVisited())
                            {
                                wm.getSquare(x,y).setBlack(true);
                            }
                        }
                    }
                }
                a++;
            }
            if(e.getKeyChar() == 'c' && wm.getSquare(wp.getRowPosition(),wp.getColPosition()).isLadder() && wp.getGold()==true)
            {
                status = WON;
                repaint();
            }
        }
        if (e.getKeyChar() == 'n' && status == DEAD || e.getKeyChar() == 'n' && status == WON) {
            reset();
        }
        repaint();
    }
    public void keyReleased(KeyEvent e)
    {

    }
    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }
}