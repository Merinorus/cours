package exo_02_02;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class ControleEtiquette extends JToolBar {
	
	private ImageIcon[] iconesBoutons = new ImageIcon[18];
	private JRadioButton[] boutons = new JRadioButton[6];
	private final String[] nomsIcones = { "bhgauche", "bhcentre", "bhdroite",
										  "bvhaut", "bvcentre", "bvbas" };
	private final int positions[] = { JLabel.LEFT, JLabel.CENTER, JLabel.RIGHT,
            						  JLabel.TOP,  JLabel.CENTER, JLabel.BOTTOM };
	private EtiquetteSimple etiquette;
	private EcouteurHorizontal[] eh = new EcouteurHorizontal[3];
	private EcouteurVertical[] ev = new EcouteurVertical[3];
	
	private ButtonGroup grp1, grp2;
	
	public static void main(String[] args) {
		FenetreSimple fs = new FenetreSimple("Ouaf");
		ImageIcon chien = new ImageIcon("RESGRAF/Chien.gif");
		EtiquetteSimple es = new EtiquetteSimple("Un chien", chien);
		ControleEtiquette ce = new ControleEtiquette(es);
		fs.add(ce, BorderLayout.NORTH);
		fs.add(es, BorderLayout.CENTER);
		fs.pack();
	}
	
	private ControleEtiquette()
	{
		creerEcouteurs();
		chargerIcones();
		creerBoutons();
		for(int b1 = 0; b1 < 3; b1++) {
			add(boutons[b1]);
		}
		add(new Separator());
		for(int b2 = 3; b2 < boutons.length; b2++) {
			add(boutons[b2]);
		}
	}
	
	public ControleEtiquette(EtiquetteSimple es)
	{
		this();
		etiquette = es;
	}
	
	private void chargerIcones() {
		for(int icoIndex = 0; icoIndex < nomsIcones.length; icoIndex++)
		{
			iconesBoutons[icoIndex] = new ImageIcon("RESGRAF/" + nomsIcones[icoIndex] + ".gif");
			iconesBoutons[icoIndex+6] = new ImageIcon("RESGRAF/" + nomsIcones[icoIndex] + "R.gif");
			iconesBoutons[icoIndex+12] = new ImageIcon("RESGRAF/" + nomsIcones[icoIndex] + "B.gif");
		}
	}
	
	private void creerBoutons() {
		grp1 = new ButtonGroup();
		grp2 = new ButtonGroup();
		
		for(int button = 0; button < boutons.length; button++)
		{
			boutons[button] = new JRadioButton(iconesBoutons[button]);
			boutons[button].setSelectedIcon(iconesBoutons[button+6]);
			boutons[button].setRolloverIcon(iconesBoutons[button+12]);
		}
		
		for(int b1 = 0; b1 < 3; b1++) {
			grp1.add(boutons[b1]);
			boutons[b1].addActionListener(eh[b1]);
		}
		for(int b2 = 3; b2 < 6; b2++) {
			grp2.add(boutons[b2]);
			boutons[b2].addActionListener(ev[b2-3]);
		}
		boutons[2].setSelected(true);
		boutons[4].setSelected(true);
	}
	
	class EcouteurHorizontal implements ActionListener {
        private int position;

        EcouteurHorizontal(int pos) {
        	position = pos;
        }

        public void actionPerformed(ActionEvent evt) {
        	etiquette.setHorizontalTextPosition(positions[position]);
        }
    }
	
	class EcouteurVertical implements ActionListener {
        private int position;

        EcouteurVertical(int pos) {
        	position = pos;
        }

        public void actionPerformed(ActionEvent evt) {
        	etiquette.setVerticalTextPosition(positions[position]);
        }
    }
	
	private void creerEcouteurs()
	{
		for(int e = 0; e < eh.length; e++) {
			eh[e] = new EcouteurHorizontal(e);
			ev[e] = new EcouteurVertical(e+3);
		}
	}

}
