package cadreSensible1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuetteSouris implements MouseListener {

    public void mouseClicked(MouseEvent e) {
        System.out.println("clic sur le point (" + e.getX() + "," + e.getY() + ")");
    }

    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
}
