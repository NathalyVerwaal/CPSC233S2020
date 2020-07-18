package application;

import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;


public class QuizController {
    @FXML
    private Button myButton;

    @FXML
    private ChoiceBox<Integer> myChoiceBox;

    @FXML
    private PieChart myPieChart;
    
    private int numOfDataPoints = 5;
    
    private void changePieChartData() {
    	Random rand = new Random();
        PieChart.Data[] data = new PieChart.Data[numOfDataPoints];
        for (int counter = 0; counter < numOfDataPoints; counter++) {
        	data[counter] = new PieChart.Data("data " + counter, rand.nextInt(100));
        }
        myPieChart.setData(FXCollections.observableArrayList(data));    	
    }

    @FXML
    void buttonClicked(ActionEvent event) {
    	changePieChartData();
    }

    @FXML
    void initialize() {
        assert myButton != null : "fx:id=\"myButton\" was not injected: check your FXML file 'Quiz6.fxml'.";
        assert myChoiceBox != null : "fx:id=\"myChoiceBox\" was not injected: check your FXML file 'Quiz6.fxml'.";
        assert myPieChart != null : "fx:id=\"myPieChart\" was not injected: check your FXML file 'Quiz6.fxml'.";

        changePieChartData();
        Integer[] values = {1, 2, 3, 4, 5};
        myChoiceBox.setItems(FXCollections.observableArrayList(values));
        
        myChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue observable, Number oldValue, Number newValue) {
                        int index = newValue.intValue(); 
                        if (index >= 0) numOfDataPoints = values[index];
                    }
                 }
             );

    }
}
