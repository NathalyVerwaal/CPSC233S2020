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
			try {
				board.placeToken(player1.getToken(),move.row,move.column);
			} catch (InvalidPlacementException ipe) {
				System.out.println("invalid move " + ipe.getMessage());
				move = player1.getMove(new TicTacToeBoard(board));
			}
			// check if game is over yet before prompting second player
			move = player2.getMove(board);
			try {
				board.placeToken(player2.getToken(),move.row,move.column);
			} catch (InvalidPlacementException e) {
				// TODO Auto-generated catch block
				move = player2.getMove(new TicTacToeBoard(board));
			}
		}
		
		// check who has won
	}
	
	public static void main(String[] args){
		TicTacToeGame game = new TicTacToeGame();
		game.play();
	}
}