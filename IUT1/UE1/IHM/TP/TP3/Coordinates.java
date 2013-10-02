package tp;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Coordinates extends JFrame {

	private JButton[] boutons = new JButton[Rows*Cols];
	final static int Rows = 8, Cols = 8;
	private GridLayout grid = new GridLayout();
	
	public static void main(String[] args) {
		Coordinates tpFen = new Coordinates();
	}
	
	public Coordinates()
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
			boutons[buttonId].setActionCommand(Integer.toString(buttonId%8) + "," + Integer.toString(buttonId/8));
			boutons[buttonId].setPreferredSize(new Dimension(32,32));
			boutons[buttonId].addActionListener(new CaseClicked());
		}
	}
	
	private void couleurBoutons() {
		for(int Y = 0; Y < Rows; Y++) {
			for(int X = 0; X < Cols; X++) {
				Color col;
				
				if(Y % 2 == 0) {
					col = (X % 2 == 0) ? Color.white : Color.gray;
				}
				else {
					col = (X % 2 == 0) ? Color.gray : Color.white;
				}
				
				boutons[Y*Rows + X].setBackground(col);
			}
		}
	}
	
	private void ajouterBoutons() {
		for(int buttonId = 0; buttonId < boutons.length; buttonId++) {
			add(boutons[buttonId]);
		}
	}
	
	class CaseClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			setTitle(source.getActionCommand());
		}
	}
}