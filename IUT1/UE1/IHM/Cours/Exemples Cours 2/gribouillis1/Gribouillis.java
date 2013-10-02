package gribouillis1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Gribouillis extends JPanel implements MouseListener {

    int x = -1, y;
    
    public Gribouillis() {
        setBackground(Color.WHITE);
        addMouseListener(this);
    }
    
    public void mouseReleased(MouseEvent e) {
        Graphics g = getGraphics();
        if (x >= 0)
            g.drawLine(x, y, e.getX(), e.getY());
        x = e.getX();
        y = e.getY();
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}

}
