import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.scene.input.*;


public class TicTacToeGUI extends Application {
	public class HandleKeyboardInput implements EventHandler<KeyEvent> {
		public void handle(KeyEvent event){
			if (event.getCharacter().charAt(0) == 'q') {
				System.exit(0);
			}
		}
	}
	
	public class HandleCellClick implements EventHandler<ActionEvent> {
		private int row;
		private int column;
		public HandleCellClick(int aRow, int aColumn) {
			row = aRow;
			column = aColumn;
		}
		public void handle(ActionEvent event){
			// place token
			// check if this is a valid placement
			board.placeToken('x', row, column);
			//gridButtons[row][column].setText("x");
			Button b = gridButtons[row][column];
			b.setText("x");
			//should disable button
			
			// check if the game is over
			if (board.hasWon('x')) {
				messageLabel.setText("You won!");
			} else {
				// let ai take a turn
				Move m = ai.getMove(board);
				board.placeToken('o', m.row, m.column);
				b = gridButtons[m.row][m.column];
				b.setText("o");
				
				// should disable this button
				
				// check if the game is over
				if (board.hasWon('o')) {
					messageLabel.setText("You lost!");
				}
			}
		}
	}
	
	private Button[][] gridButtons = new Button[3][3];
	private Label messageLabel = new Label("It's your turn, you're 'x'");
	
	private TicTacToeBoard board = new TicTacToeBoard();
	private AIPlayer ai = new AIPlayer('o');
	
	
	@Override
	public void start(Stage primaryStage) {
		// build grid of buttons for the board (visual component)
		GridPane grid = new GridPane();
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++){
				Button b = new Button(" ");
				b.setOnAction(new HandleCellClick(row,column));
				gridButtons[row][column] = b;
				grid.add(gridButtons[row][column], row, column);				
			}
		}
		
		// build entire scene: grid and label
		BorderPane root = new BorderPane();
		root.setCenter(grid);
		root.setBottom(messageLabel);
		
		Scene scene = new Scene(root, 300, 300);
		scene.setOnKeyTyped(new HandleKeyboardInput());

		primaryStage.setTitle("Tic Tac Toe");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}