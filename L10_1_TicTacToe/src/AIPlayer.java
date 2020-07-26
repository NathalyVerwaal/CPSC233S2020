public class AIPlayer {
	private char token = 'x';
	
	public AIPlayer(char aToken) {
		setToken(aToken);
	}
	
	public void setToken(char aToken){
		if (aToken == 'x' || aToken == 'o') {
			token = aToken;
		}
	}
	
	public char getToken() {
		return token;
	}
	
	public Move getMove(TicTacToeBoard board){
		int row = 0;
		int column = 0;
		while (!board.isValidPlacement(row,column)) {
			row++;
			if (row >= 3) {
				row = 0;
				column++;
			}
		}
		return new Move(row,column);
	}
}