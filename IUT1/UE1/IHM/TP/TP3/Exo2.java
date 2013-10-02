package tp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.*;

public class Exo2 extends JFrame {

	private JButton[] boutons = new JButton[12];
	private String[] mois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre" };
	private FlowLayout flow = new FlowLayout();
	
	public static void main(String[] args) {
		Exo2 tpFen = new Exo2();
	}
	
	public Exo2()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(flow);
		
		creerBoutons();
		couleurBoutons();
		ajouterBoutons();
		
		pack();
		setVisible(true);
	}

	private void creerBoutons() {
		for(int buttonId = 0; buttonId < boutons.length; buttonId++) {
			boutons[buttonId] = new JButton(mois[buttonId]);
			boutons[buttonId].setMargin(new Insets(0, 0, 0, 0));
		}
	}
	
	private void couleurBoutons() {
		for(int buttonId = 0; buttonId < boutons.length; buttonId++) {
			boutons[buttonId].setBackground(new Color(0xa6ce39));
		}
	}
	
	private void ajouterBoutons() {
		for(int buttonId = 0; buttonId < boutons.length; buttonId++) {
			add(boutons[buttonId]);
		}
	}
}
