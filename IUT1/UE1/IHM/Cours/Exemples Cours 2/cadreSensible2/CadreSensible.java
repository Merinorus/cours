package cadreSensible2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class CadreSensible extends JFrame implements MouseListener {
    
    CadreSensible() {
        super("Cadre sensible 2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        
        addMouseListener(this);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new CadreSensible();
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("clic sur le point (" + e.getX() + "," + e.getY() + ")");
    }
    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
