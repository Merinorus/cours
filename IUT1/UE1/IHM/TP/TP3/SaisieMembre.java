package tp;

import java.awt.*;
import javax.swing.*;

public class SaisieMembre extends JFrame {
	
	private JPanel pnlCenter = new JPanel();
	private JPanel pnlButtons = new JPanel();
	private JPanel pnlCheckBoxes = new JPanel();
	private JPanel pnlTextFields = new JPanel();
	private JPanel pnlGender = new JPanel();
	
	private ButtonGroup btnGrp = new ButtonGroup();
	
	private JButton btnOK = new JButton("OK"), btnCancel = new JButton("Annuler");
	private JLabel lblGender = new JLabel("Sexe");
	private JRadioButton radMale = new JRadioButton("Homme"), radFemale = new JRadioButton("Femme");
	private JTextField textName = new JTextField(), textFirstName = new JTextField();
	private JTextArea textAddr = new JTextArea();
	
	private String[] fields = {"Nom", "Prénom", "Adresse" };
	private String[] sports = {"Tennis", "Squash", "Natation", "Athlétisme", "Randonnée", "Foot", "Basket", "Volley", "Pétanque" };
	
	public static void main(String[] args) {
		SaisieMembre saisie = new SaisieMembre();
	}
	
	public SaisieMembre() {
		setLayout(new BorderLayout());
		setTitle("Boite de saisie");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		createLayouts();
		addComponents();
		addPanels();
		pack();
		setVisible(true);
	}
	
	private void createLayouts() {
		pnlButtons.setLayout(new FlowLayout());
		pnlCheckBoxes.setLayout(new GridLayout(0, 1));
		pnlTextFields.setLayout(new GridLayout(0, 1));
		pnlGender.setLayout(new FlowLayout());
		pnlCenter.setLayout(new BorderLayout());
	}
	
	private void addComponents() {
		pnlButtons.add(btnOK);
		pnlButtons.add(btnCancel);
		pnlGender.add(lblGender);
		pnlGender.add(radMale);
		pnlGender.add(radFemale);
		btnGrp.add(radMale);
		btnGrp.add(radFemale);
		pnlCenter.add(pnlTextFields, BorderLayout.NORTH);
		pnlCenter.add(pnlGender, BorderLayout.SOUTH);
		createCheckBoxes();
		createTextFields();
	}
	
	private void addPanels() {
		add(pnlButtons, BorderLayout.SOUTH);
		add(pnlCheckBoxes, BorderLayout.EAST);
		add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setPreferredSize(new Dimension(250, 120));
		pnlCheckBoxes.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
	}
	
	private void createCheckBoxes() {
		for(int c = 0; c < sports.length; c++) {
			JCheckBox box = new JCheckBox(sports[c]);
			pnlCheckBoxes.add(box);
		}
	}
	
	private void createTextFields() {
		pnlTextFields.add(new JLabel(fields[0]));
		pnlTextFields.add(textName);
		pnlTextFields.add(new JLabel(fields[1]));
		pnlTextFields.add(textFirstName);
		pnlTextFields.add(new JLabel(fields[2]));
		pnlTextFields.add(textAddr);
	}
}
