package application;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


public class Controller {
	private ArrayList<Student> students = new ArrayList<Student>();

    @FXML
    private TextField gpaTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<Student> studentChoiceBox;

    @FXML
    void addClicked(ActionEvent event) {
    	Student toAdd = new Student();
    	toAdd.id = Integer.parseInt(idTextField.getText());
    	toAdd.name = nameTextField.getText();
    	toAdd.gpa = Float.parseFloat(gpaTextField.getText());
    	students.add(toAdd);
    	updateChoiceBox();
    }
    
    @FXML
    void deleteClicked(ActionEvent event) {
    	students.remove(studentChoiceBox.getSelectionModel().getSelectedIndex());
    	updateChoiceBox();
    }
    

    @FXML
    void editClicked(ActionEvent event) {
    	Student selected = students.get(studentChoiceBox.getSelectionModel().getSelectedIndex());
    	selected.id = Integer.parseInt(idTextField.getText());
    	selected.name = nameTextField.getText();
    	selected.gpa = Float.parseFloat(gpaTextField.getText());
    	updateChoiceBox();
    }

    @FXML
    void saveClicked(ActionEvent event) {
    	// TODO:  save all students in the choicebox to a binary file.
    }

    @FXML
    void initialize() {
        assert gpaTextField != null : "fx:id=\"gpaTextField\" was not injected: check your FXML file 'View.fxml'.";
        assert idTextField != null : "fx:id=\"idTextField\" was not injected: check your FXML file 'View.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'View.fxml'.";
        assert studentChoiceBox != null : "fx:id=\"studentChoiceBox\" was not injected: check your FXML file 'View.fxml'.";
        
        // TODO:  read all students from a file and place them in the choice box.
    }

    private void updateChoiceBox() {
    	System.out.println(students);
        studentChoiceBox.setItems(FXCollections.observableArrayList(students));
		studentChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
			new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue observable, Number oldValue, Number newValue) {
					int index = newValue.intValue(); 
					if (index >= 0) {
						Student selected = students.get(index);
						idTextField.setText("" + selected.id);
						nameTextField.setText(selected.name);
						gpaTextField.setText("" + selected.gpa);
					}
				}			
			}
		);

    }
}
