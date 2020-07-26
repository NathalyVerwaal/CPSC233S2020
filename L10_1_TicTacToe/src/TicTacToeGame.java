public class TicTacToeGame{
	private int numberOfRounds = 1;
	private TicTacToeBoard board = new TicTacToeBoard();
	private Player player1 = new Player('x');
	private Player player2 = new Player('o');
	
	public TicTacToeGame(){}
	public TicTacToeGame(int aNumberOfRounds) {
		numberOfRounds = aNumberOfRounds;
		
	}
	
	public void play(){
		
		while (!board.gameOver()){
			// Don't trust the player so don't give the player the 
			// original board, instead give a copy by using the copy
			// constructor
			Move move = player1.getMove(new TicTacToeBoard(board));
			board.placeToken(player1.getToken(),move.row,move.column);
			// check if game is over yet before prompting second player
			move = player2.getMove(board);
			board.placeToken(player2.getToken(),move.row,move.column);
		}
		
		// check who has won
	}
	
	public static void main(String[] args){
		TicTacToeGame game = new TicTacToeGame();
		game.play();
	}
}