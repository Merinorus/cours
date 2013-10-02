package testsLayout;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestGridLayout2 extends JFrame {

    TestGridLayout2() {
        super("TestGridLayout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new GridLayout(0, 2));
        for (int i = 1; i <= 5; i++) {
            JPanel p = new JPanel();
            p.add(new JButton("Bouton " + i));
            add(p);
        }
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new TestGridLayout2();
    }
}
