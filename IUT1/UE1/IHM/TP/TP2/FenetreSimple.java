package exo_02_02;

import javax.swing.*;
import java.awt.event.*;

public class FenetreSimple extends JFrame {
	static final int LARGEUR = 300;
	static final int HAUTEUR = 150;
	
	public static void Main(String[] args)
	{
		FenetreSimple fs = new FenetreSimple("Une fenêtre simple");
	}
	
	public FenetreSimple(String titre)
	{
		super(titre);
		setSize(LARGEUR, HAUTEUR);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
	        public void windowClosing(WindowEvent e) {
	            System.exit(0);
	        }
	    });
	}
}
