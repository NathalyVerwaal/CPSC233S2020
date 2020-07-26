import java.util.Scanner;

public class Player {
	private String id;
	private char token = 'x';
	
	public Player(char aToken) {
		setToken(aToken);
		System.out.print("What is your name: ");
		Scanner keyboard = new Scanner(System.in);
		id = keyboard.nextLine();
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
		board.print();
		System.out.println(id + " it's your turn.  You're token is " + token);
		Scanner keyboard = new Scanner(System.in);
		int row = 0;
		int column = 0;
		
		do {
			System.out.print("Enter row: ");
			row = keyboard.nextInt();
			System.out.print("Enter column: ");
			column = keyboard.nextInt();
		} while (!board.isValidPlacement(row,column));
		
		Move move = new Move(row, column);
		return move;	
		
	}

}