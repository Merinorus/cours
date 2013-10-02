package gribouillis1;

import javax.swing.JFrame;

public class Cadre extends JFrame {
    
    Cadre() {
        super("Gribouillis 1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setContentPane(new Gribouillis());
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Cadre();
    }

}
