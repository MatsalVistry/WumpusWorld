import java.awt.*;
import javax.swing.*;
public class WumpusFrame extends JFrame
{
    public WumpusFrame(String frameName)
    {
        super(frameName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        WumpusPanel p = new WumpusPanel ();
        Insets frameInsets = getInsets();
        int frameWidth = p.getWidth()
                + (frameInsets.left + frameInsets.right);
        int frameHeight = p.getHeight()
                + (frameInsets.top + frameInsets.bottom);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setLayout(null);
        add(p);
        pack();
        setVisible(true);
    }
}