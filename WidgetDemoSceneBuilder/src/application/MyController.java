package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


public class MyController {

    @FXML
    private Button myButton;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private Label myLabel;

    @FXML
    private PieChart myPieChart;

    @FXML
    private TextField myTextField;


    @FXML
    void buttonClicked(ActionEvent event) {
    	PieChart.Data[] data = new PieChart.Data[5];
    	Random rand = new Random();
    	data[0] = new PieChart.Data("zero", rand.nextInt(100));
    	data[1] = new PieChart.Data("one", rand.nextInt(100));
    	data[2] = new PieChart.Data("two", rand.nextInt(100));
    	data[3] = new PieChart.Data("three", rand.nextInt(100));
    	data[4] = new PieChart.Data("four", rand.nextInt(100));
    	
    	myPieChart.setData(FXCollections.observableArrayList(data));
    	//System.out.println("in buttonClicked");
    }

    @FXML
    void keyTyped(KeyEvent event) {
    	myLabel.setText(myTextField.getText());
    }

    @FXML
    void initialize() {
        assert myButton != null : "fx:id=\"myButton\" was not injected: check your FXML file 'WidgetDemoView.fxml'.";
        assert myChoiceBox != null : "fx:id=\"myChoiceBox\" was not injected: check your FXML file 'WidgetDemoView.fxml'.";
        assert myLabel != null : "fx:id=\"myLabel\" was not injected: check your FXML file 'WidgetDemoView.fxml'.";
        assert myPieChart != null : "fx:id=\"myPieChart\" was not injected: check your FXML file 'WidgetDemoView.fxml'.";
        assert myTextField != null : "fx:id=\"myTextField\" was not injected: check your FXML file 'WidgetDemoView.fxml'.";
        
        String[] values = {"Zero", "one", "two", "three", "four", "five"};
        myChoiceBox.setItems(FXCollections.observableArrayList(values));
        myChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
        	new	ChangeListener<Number>() {
        		@Override
        		public void changed(ObservableValue observable, Number oldValue, Number newValue) {
        			int index = newValue.intValue();
        			if (index >= 0) 
        				myLabel.setText("Selected: " + values[index] + " at location " + index);
        		}
        	}
        );


    }

}
