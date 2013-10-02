package tp;

import java.util.Random;

public class Grid {
	private int[][] grid;
	private int gridSize;
	private int nbMines;
	
	public static final int CLEAR = -1;
	public static final int MINE  = -2;
	
	public Grid(int size, int mines) {
		nbMines = mines;
		gridSize = size;
		
		createGrid();
	}

	private void createGrid() {
		grid = new int[gridSize][gridSize];
		clearGrid();
		placeMines();
		updateSurroundings();
	}

	private void clearGrid() {
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length; y++) {
				grid[x][y] = CLEAR;
			}
		}
	}
	
	private void placeMines() {
		Random rand = new Random(System.currentTimeMillis());
		for(int mine = 0; mine < nbMines; mine++) {
			int minePos = rand.nextInt(gridSize*gridSize);
			grid[minePos/gridSize][minePos%gridSize] = MINE;
		}
	}
	
	private int getSurroundingMines(int x, int y) {
		int surroundingMines = 0;
		
		for(int checkX = x - 1; checkX <= x+1; checkX++) {
			if(checkX < 0 || checkX >= gridSize) continue;
				
			for(int checkY = y - 1; checkY <= y+1; checkY++) {
				if(checkY < 0 || checkY >= gridSize) continue;
				
				if(grid[checkX][checkY] == MINE)
					surroundingMines++;
			}
		}
		
		return surroundingMines;
	}
	
	private void updateSurroundings() {
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length; y++) {
				if(grid[x][y] == CLEAR)
					grid[x][y] = getSurroundingMines(x, y);
			}
		}
	}
	
	public int getSize() {
		return grid.length;
	}
	
	public int getSquare(int x, int y) {
		return grid[x][y];
	}
	
	public int getMines() {
		return nbMines;
	}
}
