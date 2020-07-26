package application;
	
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FillTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class MyAnimation extends Application {
	public static final int cellSize = 300;
	
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
	    /*
		// Code for animating the horse in motion
		final int COLUMNS = 4;
	    final int NUM_OF_IMAGES = 11;
	    final int OFFSET_X = 18;
	    final int OFFSET_Y = 25;
	    final int WIDTH = 374;
	    final int HEIGHT = 243;
		// From: https://upload.wikimedia.org/wikipedia/commons/7/73/The_Horse_in_Motion.jpg
        ImageView imageView = new ImageView(new Image("application/The_Horse_in_Motion.jpg"));
		*/
		
		// Code for animating the stick figure
	    final int COLUMNS = 2;
	    final int NUM_OF_IMAGES = 2;
	    final int OFFSET_X = 2;
	    final int OFFSET_Y = 0;
	    final int WIDTH = 326;
	    final int HEIGHT = 310;
        ImageView imageView = new ImageView(new Image("application/Stick.jpg"));
		
		// The rest of the code is used for both images
        imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
        grid.add(imageView, column, row);

        Animation animation = new SpriteAnimation(
                imageView,
                Duration.millis(1000),
                NUM_OF_IMAGES, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

		
	}
	
	public void addTimer(int row, int column) {
		Label timerLabel = new Label();
		timerLabel.setFont(new Font(20));
		grid.add(timerLabel, column, row);
		
		AnimationTimer timer = new AnimationTimer() {
		    @Override
		    public void handle(long now) {
				timerLabel.setText(Long.toString(now));
		    }
		};
				
				
		timer.start();
	}
	
	public void addRectangle(int row, int column) {
		Rectangle rect = new Rectangle(cellSize, 2*cellSize/3.0, Color.DARKORANGE); 
		grid.add(rect, column, row);

		ScaleTransition st = new ScaleTransition();
		st.setDuration(Duration.millis(3000));
		st.setNode(rect);
		st.setByX(1.1f);
		st.setByY(1.9f);
		st.setCycleCount(6);
		st.setAutoReverse(true);
		st.play();		
	}
	
	public void addHexagon(int row, int column) {
		
		Polygon hexagon = new Polygon();
		hexagon.getPoints().addAll(new Double[]{
			cellSize/3.0, 0.0, // first point in hexagon
			2*cellSize/3.0, 0.0, // second point
			(double) cellSize, cellSize/2.0, // third point
			2*cellSize/3.0,(double) cellSize, // fourth point
			cellSize/3.0,(double) cellSize, // fifth point
			0.0, cellSize/2.0  // sixth point
		}); 
		hexagon.setFill(Color.GREEN);
		
		grid.add(hexagon, column, row);
		//Creating a rotate transition.  This will, over 100 milliseconds,
		// do a 360 degree rotation of the hexagon node.
		RotateTransition rotateTransition = new RotateTransition(); 
		rotateTransition.setNode(hexagon);
		rotateTransition.setDuration(Duration.millis(1000));
		rotateTransition.setByAngle(180);
		rotateTransition.setCycleCount(Animation.INDEFINITE); 
		rotateTransition.setAutoReverse(true); 
		
		rotateTransition.play();
		
	}
	
	public void addCircle(int row, int column) {
		Circle circle = new Circle(cellSize/2, Color.BLUEVIOLET); 	
		grid.add(circle, column, row);

		FillTransition ft = new FillTransition();
		ft.setDuration(Duration.millis(3000));
		ft.setShape(circle);
		ft.setFromValue(Color.BLUEVIOLET);
		ft.setToValue(Color.ALICEBLUE);
		ft.setCycleCount(4);
		ft.setAutoReverse(false);
		ft.play();		
	}
	
	public void emptyCell(int row, int col) {
		Rectangle sq = new Rectangle(cellSize,cellSize);
		
		grid.add(sq, col, row);
	}
	
}
