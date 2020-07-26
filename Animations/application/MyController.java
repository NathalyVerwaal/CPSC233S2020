package application;

public class MyController {
	private MyAnimation gui;
	private char[][] grid = {{'c', 'h', 's'},{' ', 'r', 't'}};
	
	public MyController(MyAnimation gui) {
		this.gui = gui;
		setupGrid();
	}
	
	public void setupGrid() {
		for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
			char[] row = grid[rowIndex];
			for (int colIndex = 0; colIndex < row.length; colIndex++) {
				char current = row[colIndex];
				switch (current) {
				case 'h': 
					gui.addHexagon(rowIndex, colIndex);
					break;
				case 'c':
					gui.addCircle(rowIndex, colIndex);
					break;
				case 'r':
					gui.addRectangle(rowIndex, colIndex);
					break;
				case 't':
					gui.addTimer(rowIndex, colIndex);
					break;
				case 's':
					gui.addSprite(rowIndex, colIndex);
					break;
				case ' ':
					gui.emptyCell(rowIndex, colIndex);
				}
			}
		}
	}
	
}
