package tp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Exo1 extends JFrame {

	private JButton h, g, c, d, b;
	
	public static void main(String[] args) {
		Exo1 tpFen = new Exo1();
	}
	
	public Exo1()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		creerBoutons();
		couleurBoutons();
		ajouterBoutons();
		
		pack();
		setVisible(true);
	}

	private void creerBoutons() {
		h = new JButton("Haut");
		g = new JButton("Gauche");
		c = new JButton("Centre");
		d = new JButton("Droite");
		b = new JButton("Bas");
		
		d.setPreferredSize(new Dimension(150, d.getSize().height));
		b.setPreferredSize(new Dimension(b.getSize().width, 150));
	}
	
	private void couleurBoutons() {
		h.setBackground(Color.CYAN);
		g.setBackground(Color.MAGENTA);
		c.setBackground(Color.GREEN);
		d.setBackground(Color.YELLOW);
		b.setBackground(Color.RED);
	}
	
	private void ajouterBoutons() {
		add(h, BorderLayout.NORTH);
		add(g, BorderLayout.WEST);
		add(c, BorderLayout.CENTER);
		add(d, BorderLayout.EAST);
		add(b, BorderLayout.SOUTH);
	}
}
