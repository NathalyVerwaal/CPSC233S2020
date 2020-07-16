package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class WidgetDemo extends Application {
	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox();
		Label label1 = new Label("Hello");
		root.getChildren().add(label1);
		
		Circle c = new Circle(50,50,20);
		root.getChildren().add(c);
		
		HBox innerBox = new HBox();
		Button b = new Button("click here");
		innerBox.getChildren().add(b);
		
		Rectangle rect = new Rectangle(10,20);
		innerBox.getChildren().add(rect);
		
		root.getChildren().add(innerBox);
		
		
		Scene scene = new Scene(root, 300, 300);
		primaryStage.setTitle("Widget Demo");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
