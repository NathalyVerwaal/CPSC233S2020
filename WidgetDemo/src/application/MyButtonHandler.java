package application;

import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class MyButtonHandler implements EventHandler<ActionEvent> {
	private Label msgLabel;
	
	public void setMessageLabel(Label messageLabel) {
		msgLabel = messageLabel;		
	}
	public void handle(ActionEvent event){
		System.out.println("clicked");
		Button source = (Button)event.getSource();
		source.setText("clicked");
		msgLabel.setText("The button was clicked");
	}
}