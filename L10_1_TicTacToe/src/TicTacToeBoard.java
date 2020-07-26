public class TicTacToeBoard {
	private char[][] grid = {{'.','.','.'},	{'.','.','.'}, {'.','.','.'}};
	private char[][] fullgrid = {{'x','o','x'},{'x','o','x'},{'o','x','o'}};
	
	public TicTacToeBoard(){}
	
	TicTacToeBoard(int configuration) {
		switch (configuration) {
			case 1:
				grid = fullgrid;
				break;
			default:
		}
	}
	
	public TicTacToeBoard(TicTacToeBoard boardToCopy){
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				grid[row][column] = boardToCopy.grid[row][column];
			}
		}
		
	}
	
	public void placeToken(char token, int row, int column) throws InvalidPlacementException{
		// is row,column on the board?
		// is the spot at row,column empty?
		if (grid[row][column] == '.') {
			grid[row][column] = token;
		} else {
			// don't know what to do
			// don't want player to skip their turn.
			// I need the game to know there is a problem
			// I need to initiate (throw) and exception
			throw new InvalidPlacementException("Can't place token at (" + row + "," +
						column + ". There already is a token at this location.");
		}
		
	}
	
	public boolean isValidPlacement(int row, int column) {
		return grid[row][column] == '.';
	}
	
	public boolean hasWon(char token) {
		boolean threeInARow = false;
		// check rows and columns
		for (int index = 0; index < 3 && !threeInARow; index++) {
			threeInARow = (grid[index][0] == token && 
							grid[index][1] == token && 
							grid[index][2] == token) ||
							
							(grid[0][index] == token &&
							grid[1][index] == token &&
							grid[2][index] == token);
		}
		// is there a diagonal of tokens
		
		threeInARow = threeInARow || 
			(grid[0][0] == token && grid[1][1] == token && grid[2][2] == token);
		
		threeInARow = threeInARow ||
			(grid[0][2] == token && grid[1][1] == token && grid[2][0] == token);
			
		return threeInARow;
	}
	
	public boolean boardFull() {
		boolean full = true;
		for (int row = 0; row < 3 && full; row++){
			for (int column = 0; column < 3 && full; column++){
				if (grid[row][column] == '.') {
					full = false;
				}
			}
		}
		return full;
	}
	
	public boolean gameOver(){
		return hasWon('x') || hasWon('o') || boardFull();
	}
	
	public void print(){
		for (int row = 0; row < 3; row++){
			for (int column = 0; column < 3; column++){
				System.out.print(grid[row][column]);
			}
			System.out.println();
		}
	}
}