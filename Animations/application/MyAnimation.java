package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;


public class MyAnimation extends Application {
	private int cellSize = 300;
	
	private GridPane grid = new GridPane();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			MyController controller = new MyController(this);
			Scene scene = new Scene(grid,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void addSprite(int row, int column) {
	}
	
	public void addTimer(int row, int column) {
	}
	
	public void addRectangle(int row, int column) {
	}
	
	public void addHexagon(int row, int column) {
	}
	
	public void addCircle(int row, int column) {
	}
	
	public void emptyCell(int row, int col) {
		Rectangle sq = new Rectangle(cellSize,cellSize);
		grid.add(sq, col, row);
	}
	
}
