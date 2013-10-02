package tp;

import java.awt.*;

import javax.swing.*;

public class Exo3 extends JFrame {

	private JButton[] boutons = new JButton[8*8];
	private GridLayout grid = new GridLayout();
	
	public static void main(String[] args) {
		Exo3 tpFen = new Exo3();
	}
	
	public Exo3()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		grid.setRows(8);
		setLayout(grid);
		
		creerBoutons();
		couleurBoutons();
		ajouterBoutons();
		
		pack();
		setVisible(true);
	}

	private void creerBoutons() {
		for(int buttonId = 0; buttonId < boutons.length; buttonId++) {
			boutons[buttonId] = new JButton();
			boutons[buttonId].setPreferredSize(new Dimension(32,32));
		}
	}
	
	private void couleurBoutons() {
		for(int Y = 0; Y < 8; Y++) {
			for(int X = 0; X < 8; X++) {
				Color col;
				
				if(Y % 2 == 0) {
					col = (X % 2 == 0) ? Color.white : Color.gray;
				}
				else {
					col = (X % 2 == 0) ? Color.gray : Color.white;
				}
				
				boutons[Y*8 + X].setBackground(col);
			}
		}
	}
	
	private void ajouterBoutons() {
		for(int buttonId = 0; buttonId < boutons.length; buttonId++) {
			add(boutons[buttonId]);
		}
	}
}
