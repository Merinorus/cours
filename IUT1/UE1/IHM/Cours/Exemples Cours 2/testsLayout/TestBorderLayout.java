package testsLayout;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TestBorderLayout extends JFrame {

    TestBorderLayout() {
        super("TestBorderLayout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new BorderLayout());
        add(new JButton("Bouton 1"), BorderLayout.NORTH);
        add(new JButton("Bouton 2"), BorderLayout.EAST);
        add(new JButton("Bouton 3"), BorderLayout.SOUTH);
        add(new JButton("Bouton 4"), BorderLayout.WEST);
        add(new JButton("Bouton 5"), BorderLayout.CENTER);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new TestBorderLayout();
    }
}
