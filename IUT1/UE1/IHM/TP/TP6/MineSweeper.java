package tp;

import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;
import java.awt.event.*;

public class MineSweeper extends JPanel implements ActionListener {
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		MineSweeper ms = new MineSweeper(new ActionBar(), new StatusBar());
		frame.add(ms);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private Grid grid;
	private ActionBar actionBar;
	private StatusBar statusBar;
	
	private JButton[][] gridButtons;
	private ImageIcon iconNormal, iconDoubt, iconMarked, iconMined, iconExploded;
	private ImageIcon[] iconDigits;
	
	public final static int WALKING = 0;
	public final static int DOUBTING = 1;
	public final static int MARKING = 2;
	private final static int maxAutoDiscover = 0;
	private int clickMode = WALKING;
	
	private int discoveredSquares = 0;
	private int discoveredMines = 0;
	
	public MineSweeper(ActionBar abar, StatusBar sbar) {
		grid = new Grid(15, 10);
		actionBar = abar;
		statusBar = sbar;
		setLayout(new GridLayout(grid.getSize(), grid.getSize()));
		
		loadIcons();
		createButtons();
	}
	
	private void loadIcons() {
		iconNormal = new ImageIcon("images/case_normale.png");
		iconDoubt = new ImageIcon("images/case_marquee_douteuse.png");
		iconMarked = new ImageIcon("images/case_marquee_minee.png");
		iconMined = new ImageIcon("images/case_mine.png");
		iconExploded = new ImageIcon("images/case_mine_explosee.png");
		
		loadIconsDigits();
	}

	private void loadIconsDigits() {
		iconDigits = new ImageIcon[9];
		for(int digit = 0; digit < iconDigits.length; digit++) {
			iconDigits[digit] = new ImageIcon("images/case_" + digit + ".png");
		}
	}

	private void createButtons() {
		gridButtons = new JButton[grid.getSize()][grid.getSize()];
		for(int x = 0; x < gridButtons.length; x++) {
			for(int y = 0; y < gridButtons[x].length; y++) {
				gridButtons[x][y] = new JButton();
				gridButtons[x][y].setActionCommand(x + " " + y);
				gridButtons[x][y].setMargin(new Insets(0, 0, 0, 0));
				setNormal(x, y);
				gridButtons[x][y].addActionListener(this);
				add(gridButtons[x][y]);
			}
		}
	}
	
	private int getButtonX(String actionCommand) {
		return Integer.parseInt(actionCommand.substring(0, actionCommand.indexOf(" ")));
	}
	
	private int getButtonY(String actionCommand) {
		return Integer.parseInt(actionCommand.substring(actionCommand.indexOf(" ") + 1));
	}
	
	public void setMode(int mode) {
		clickMode = mode;
	}
	
	private void setNormal(int x, int y) {
		gridButtons[x][y].setIcon(iconNormal);
	}
	
	public void setDoubt(int x, int y) {
		gridButtons[x][y].setIcon(iconDoubt);
	}
	
	public void setDigit(int x, int y) {
		if(grid.getSquare(x, y) >= 0)
			gridButtons[x][y].setIcon(iconDigits[grid.getSquare(x, y)]);
	}
	
	private void setMarked(int x, int y) {
		gridButtons[x][y].setIcon(iconMarked);
	}
	
	private void setMined(int x, int y) {
		gridButtons[x][y].setIcon(iconMined);
	}
	
	private void setExploded(int x, int y) {
		gridButtons[x][y].setIcon(iconExploded);
	}
	
	private void discover(int x, int y) {
		setDigit(x,y);
		for(int offset = 1; ;offset++) {
			if(x+offset >= grid.getSize() || y+offset >= grid.getSize() ||
			   x-offset < 0 || y-offset < 0)
				break;
			
			if(grid.getSquare(x+offset,y) <= maxAutoDiscover
			   && grid.getSquare(x+offset,y) >= 0) {
				setDigit(x+offset,y);
				gridButtons[x+offset][y].removeActionListener(this);
			} else break;
			
			if(grid.getSquare(x,y+offset) <= maxAutoDiscover
				&& grid.getSquare(x,y+offset) >= 0) {
				setDigit(x,y+offset);
				gridButtons[x][y+offset].removeActionListener(this);
			} else break;
			
			if(grid.getSquare(x+offset,y+offset) <= maxAutoDiscover
				&& grid.getSquare(x+offset,y+offset) >= 0) {
				setDigit(x+offset,y+offset);
				gridButtons[x+offset][y+offset].removeActionListener(this);
			} else break;
			
			if(grid.getSquare(x-offset,y) <= maxAutoDiscover
				&& grid.getSquare(x-offset,y-offset) >= 0) {
				setDigit(x-offset,y);
				gridButtons[x-offset][y].removeActionListener(this);
			} else break;
			
			if(grid.getSquare(x,y-offset) <= maxAutoDiscover
				&& grid.getSquare(x,y-offset) >= 0) {
				setDigit(x,y-offset);
				gridButtons[x][y-offset].removeActionListener(this);
			} else break;
			
			if(grid.getSquare(x-offset,y-offset) <= maxAutoDiscover
				&& grid.getSquare(x-offset,y-offset) >= 0) {
				setDigit(x-offset,y-offset);
				gridButtons[x-offset][y-offset].removeActionListener(this);
			} else break;
		}
	}
	
	private void win(int x, int y) {
		statusBar.setGameWon();
		for(int winX = 0; winX < grid.getSize(); winX++) {
			for(int winY = 0; winY < grid.getSize(); winY++) {
				gridButtons[x][y].removeActionListener(this);
			}
		}
	}
	
	public void loose(int loseX, int loseY) {
		for(int x = 0; x < grid.getSize(); x++) {
			for(int y = 0; y < grid.getSize(); y++) {
				if(grid.getSquare(x, y) == grid.MINE)
					setMined(x, y);
				else {
					setNormal(x, y);
					gridButtons[x][y].setEnabled(false);
				}
				
				gridButtons[x][y].removeActionListener(this);
			}
		}
		
		setExploded(loseX, loseY);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int x = getButtonX(e.getActionCommand());
		int y = getButtonY(e.getActionCommand());
		
		setMode(actionBar.getAction());
		
		if(clickMode == WALKING) {
			if(grid.getSquare(x, y) == grid.MINE) {
				loose(x, y);
				statusBar.setGameLost();
			} else {
				discover(x,y);
			}
			((JButton)e.getSource()).removeActionListener(this);
		}
		else if(clickMode == DOUBTING) {
			if(grid.getSquare(x, y) == grid.MINE) {
				discoveredMines--;
			}
			setDoubt(x, y);
		}
		else if(clickMode == MARKING) {
			if(grid.getSquare(x, y) == grid.MINE) {
				discoveredMines++;
			}
			setMarked(x, y);
		}
		
		
		if(discoveredSquares == grid.getSize()*grid.getSize()
		   || discoveredMines == grid.getMines()) {
			win(x, y);
		}
	}
}
