package tp;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class ChangeColor extends JFrame {
	private JLabel lblRed = new JLabel(), lblGreen = new JLabel(), lblBlue = new JLabel(), lblAll = new JLabel();
	private JSlider slideR = new JSlider(), slideG = new JSlider(), slideB = new JSlider(), slideAll = new JSlider();
	private JPanel pnlLabels = new JPanel(), pnlSliders = new JPanel();
	private Color bgCol = Color.white, fgCol = Color.black;
	
	public static void main(String[] args) {
		ChangeColor chg = new ChangeColor();
	}
	
	public ChangeColor() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(true);
		createSliders();
		createLabels();
		placeComponents();
		pack();
		setVisible(true);
	}
	
	private void placeComponents() {
		pnlLabels.setLayout(new GridLayout(0,1));
		pnlSliders.setLayout(new GridLayout(0,1));
		
		pnlLabels.add(lblRed);
		pnlLabels.add(lblGreen);
		pnlLabels.add(lblBlue);
		pnlLabels.add(lblAll);
		pnlLabels.setPreferredSize(new Dimension(100,0));
		add(pnlLabels, BorderLayout.WEST);
		
		pnlSliders.add(slideR);
		pnlSliders.add(slideG);
		pnlSliders.add(slideB);
		pnlSliders.add(slideAll);
		add(pnlSliders, BorderLayout.CENTER);
	}
	
	private void createSliders() {
		slideR.setMaximum(255);
		slideG.setMaximum(255);
		slideB.setMaximum(255);
		slideAll.setMaximum(0xFFFFFF);
		
		slideR.setMinimum(0);
		slideG.setMinimum(0);
		slideB.setMinimum(0);
		slideAll.setMinimum(0);
		
		slideR.setValue(bgCol.getRed());
		slideG.setValue(bgCol.getGreen());
		slideB.setValue(bgCol.getBlue());
		slideAll.setValue(bgCol.getRed()*0x010000 + bgCol.getGreen()*0x000100 + bgCol.getBlue()*0x01);
		
		
		slideR.addChangeListener(new RGBSliderChange());
		slideG.addChangeListener(new RGBSliderChange());
		slideB.addChangeListener(new RGBSliderChange());
		slideAll.addChangeListener(new AllSliderChange());
	}
	
	private void createLabels() {
		lblRed.setText("R : " + bgCol.getRed());
		lblGreen.setText("G : " + bgCol.getGreen());
		lblBlue.setText("B : " + bgCol.getBlue());
		lblAll.setText("0x" + Integer.toHexString(0x00000000 + bgCol.getRGB() - 0xFF000000));
	}
	
	private void setColor() {
		fgCol = new Color(255 - slideR.getValue(), 255 - slideG.getValue(), 255 - slideB.getValue());
		
		pnlLabels.setBackground(bgCol);
		pnlSliders.setBackground(bgCol);
		
		lblRed.setForeground(fgCol);
		lblGreen.setForeground(fgCol);
		lblBlue.setForeground(fgCol);
		lblAll.setForeground(fgCol);
	}
	
	private void setSliders() {
		slideR.setValue(bgCol.getRed());
		slideG.setValue(bgCol.getGreen());
		slideB.setValue(bgCol.getBlue());
		slideAll.setValue(bgCol.getRGB() - 0xFF000000);
	}
	
	class RGBSliderChange implements ChangeListener
	{
		public void stateChanged(ChangeEvent e) {
			bgCol = new Color(slideR.getValue(), slideG.getValue(), slideB.getValue());			
			createLabels();	
			setSliders();
		}
	}
	
	class AllSliderChange implements ChangeListener
	{
		public void stateChanged(ChangeEvent e) {
			bgCol = new Color(slideAll.getValue());
			createLabels();
			setColor();
			setSliders();
		}
	}
}
