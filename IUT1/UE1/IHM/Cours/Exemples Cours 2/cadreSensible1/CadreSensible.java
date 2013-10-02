package cadreSensible1;

import javax.swing.JFrame;

public class CadreSensible extends JFrame {
    
    CadreSensible() {
        super("Cadre sensible 1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        
        GuetteSouris gs = new GuetteSouris();
        addMouseListener(gs);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new CadreSensible();
    }
}
