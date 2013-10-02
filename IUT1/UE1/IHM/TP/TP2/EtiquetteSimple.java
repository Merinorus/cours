package exo_02_02;

import javax.swing.*;

public class EtiquetteSimple extends JLabel {

	public static void main(String[] args) {
		FenetreSimple cadre = new FenetreSimple("Une fenêtre avec un chien");
		ImageIcon chien = new ImageIcon("RESGRAF/Chien.gif");
		EtiquetteSimple etiquette = new EtiquetteSimple("Un chien", chien);
		cadre.add(etiquette);
		cadre.pack();
	}

	public EtiquetteSimple(String label)
	{
		super(label);
	}
	
	public EtiquetteSimple(ImageIcon ico)
	{
		super(ico);
	}
	
	public EtiquetteSimple(String texte, ImageIcon icone)
	{
		super(texte, icone, JLabel.CENTER);
		setIconTextGap(20);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
}
