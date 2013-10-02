package tp;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ActionBar extends JPanel implements ActionListener {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new ActionBar());
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private int action;
	
	private ButtonGroup btnGroup;
	
	private JRadioButton btnWalk;
	private JRadioButton btnBeCareful;
	private JRadioButton btnMarkMine;
	
	private static final String basePath = "images/";
	
	public ActionBar() {
		this.setLayout(new FlowLayout());
		initRadioButtons();
		addRadioButtons();
		action = MineSweeper.WALKING;
	}

	private void addRadioButtons() {
		btnGroup = new ButtonGroup();
		btnGroup.add(btnWalk);
		btnGroup.add(btnBeCareful);
		btnGroup.add(btnMarkMine);
		
		add(btnWalk);
		add(btnBeCareful);
		add(btnMarkMine);
	}

	private void initRadioButtons() {
		btnWalk = new JRadioButton();
		btnWalk.setIcon(new ImageIcon(basePath + "marcher_off.png"));
		btnWalk.setSelectedIcon(new ImageIcon(basePath + "marcher_on.png"));
		btnWalk.setRolloverIcon(new ImageIcon(basePath + "marcher_roll.png"));
		btnWalk.setActionCommand(Integer.toString(MineSweeper.WALKING));
		btnWalk.addActionListener(this);
		
		btnBeCareful = new JRadioButton();
		btnBeCareful.setIcon(new ImageIcon(basePath + "marquer_doute_off.png"));
		btnBeCareful.setSelectedIcon(new ImageIcon(basePath + "marquer_doute_on.png"));
		btnBeCareful.setRolloverIcon(new ImageIcon(basePath + "marquer_doute_roll.png"));
		btnBeCareful.setActionCommand(Integer.toString(MineSweeper.DOUBTING));
		btnBeCareful.addActionListener(this);
		
		btnMarkMine = new JRadioButton();
		btnMarkMine.setIcon(new ImageIcon(basePath + "marquer_mine_off.png"));
		btnMarkMine.setSelectedIcon(new ImageIcon(basePath + "marquer_mine_on.png"));
		btnMarkMine.setRolloverIcon(new ImageIcon(basePath + "marquer_mine_roll.png"));
		btnMarkMine.setActionCommand(Integer.toString(MineSweeper.MARKING));
		btnMarkMine.addActionListener(this);
	}
	
	public boolean isActionWalking() {
		return action == MineSweeper.WALKING;
	}
	
	public boolean isActionBeingCareful() {
		return action == MineSweeper.DOUBTING;
	}
	
	public boolean isActionMarkingMine() {
		return action == MineSweeper.MARKING;
	}
	
	public int getAction() {
		return action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.action = Integer.parseInt(e.getActionCommand());
	}
}
