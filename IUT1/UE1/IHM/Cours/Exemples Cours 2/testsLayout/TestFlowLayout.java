package testsLayout;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TestFlowLayout extends JFrame {

    TestFlowLayout() {
        super("TestFlowLayout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new FlowLayout());
        for (int i = 1; i <= 5; i++)
            add(new JButton("Bouton " + i));
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new TestFlowLayout();
    }
}
