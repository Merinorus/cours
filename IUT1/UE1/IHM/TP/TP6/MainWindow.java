package tp;

import java.awt.*;

import javax.swing.*;

public class MainWindow extends JFrame {
	public static void main(String[] args) {
		MainWindow mw = new MainWindow();
	}
	
	private ActionBar actionBar;
	private StatusBar statusBar;
	private MineSweeper mineSweeper;
	private JPanel pnlSouth;
	
	public MainWindow() {
		actionBar = new ActionBar();
		add(actionBar, BorderLayout.NORTH);
		
		pnlSouth = new JPanel();
		pnlSouth.setLayout(new FlowLayout());
		add(pnlSouth, BorderLayout.SOUTH);
		
		statusBar = new StatusBar();
		pnlSouth.add(statusBar);
		
		mineSweeper = new MineSweeper(actionBar, statusBar);
		add(mineSweeper, BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
