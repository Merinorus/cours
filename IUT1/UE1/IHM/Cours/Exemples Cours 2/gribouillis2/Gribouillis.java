package gribouillis2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Gribouillis extends JPanel implements MouseListener {

    static final int MAX = 500;
    int x[] = new int[MAX];
    int y[] = new int[MAX];
    int n = 0;
    
    public Gribouillis() {
        setBackground(Color.WHITE);
        addMouseListener(this);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 1; i < n; i++)
            g.drawLine(x[i - 1], y[i - 1], x[i], y[i]);
    }
    
    public void mouseReleased(MouseEvent e) {
        x[n  ] = e.getX();
        y[n++] = e.getY();
        repaint();
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}

}
