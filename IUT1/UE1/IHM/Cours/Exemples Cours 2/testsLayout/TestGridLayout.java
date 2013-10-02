package testsLayout;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TestGridLayout extends JFrame {

    TestGridLayout() {
        super("TestGridLayout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new GridLayout(0, 2));
        for (int i = 1; i <= 5; i++)
            add(new JButton("Bouton " + i));
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new TestGridLayout();
    }
}
