package tp;

import java.awt.*;
import javax.swing.*;

public class StatusBar extends JLabel {
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(3,1));
		frame.setTitle("StatusBar");
		
		StatusBar stPlay = new StatusBar();
		frame.add(stPlay);
		
		StatusBar stWon = new StatusBar(true);
		frame.add(stWon);
		
		StatusBar stLost = new StatusBar(false);
		frame.add(stLost);
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private static final String basePath = "images/";
	
	private static final String strPlay = "Jouez...";
	private static final String strWon = "Gagné !";
	private static final String strLost = "Perdu :(";
	
	private static final ImageIcon iconGame = new ImageIcon(basePath + "partie_en-cours.png");
	private static final ImageIcon iconWon = new ImageIcon(basePath + "partie_gagnee.png");
	private static final ImageIcon iconLost = new ImageIcon(basePath + "partie_perdue.png");
	
	public StatusBar() {
		initLabel();
		setGameInProgress();
	}
	
	public StatusBar(boolean won) {
		initLabel();
		if(won) setGameWon();
		else setGameLost();
	}

	private void initLabel() {
		this.setLayout(new FlowLayout());
		this.setIconTextGap(50);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	public void setGameInProgress() {
		this.setText(strPlay);
		this.setIcon(iconGame);
	}
	
	public void setGameWon() {
		this.setText(strWon);
		this.setIcon(iconWon);
	}
	
	public void setGameLost() {
		this.setText(strLost);
		this.setIcon(iconLost);
	}
}
