package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    	int index = studentChoiceBox.getSelectionModel().getSelectedIndex();
    	Student selected = students.get(index);
    	Student updated = new Student();
    	updated.id = Integer.parseInt(idTextField.getText());
    	updated.name = nameTextField.getText();
    	updated.gpa = Float.parseFloat(gpaTextField.getText());
    	students.set(index,updated);
    	updateChoiceBox();
    }

    @FXML
    void saveClicked(ActionEvent event) {
    	DataOutputStream out = null;
    	try {
			out = new DataOutputStream(new FileOutputStream("students.bin"));
			out.writeInt(students.size());
			for (Student s : students) {
				out.writeInt(s.id);
				out.writeUTF(s.name);
				out.writeFloat(s.gpa);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Problem opening file for saving");
			nameTextField.setText("Problem saving");
		} catch (IOException e) {
			System.out.println("Problem saving student to file");
			nameTextField.setText("Problem saving student to file");
		} finally {
			try {
				if (out != null) out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }

    @FXML
    void initialize() {
        assert gpaTextField != null : "fx:id=\"gpaTextField\" was not injected: check your FXML file 'View.fxml'.";
        assert idTextField != null : "fx:id=\"idTextField\" was not injected: check your FXML file 'View.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'View.fxml'.";
        assert studentChoiceBox != null : "fx:id=\"studentChoiceBox\" was not injected: check your FXML file 'View.fxml'.";
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
		
		getStudentData();
		updateChoiceBox();
    }
    
    private void getStudentData() {
    	DataInputStream in = null;
    	try {
			in = new DataInputStream(new FileInputStream("students.bin"));
			int size = in.readInt();
			for (int counter = 0; counter < size; counter++) {
				Student current = new Student();
				current.id = in.readInt();
				current.name = in.readUTF();
				current.gpa = in.readFloat();
				students.add(current);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Problem opening file for saving");
			nameTextField.setText("Problem saving");
		} catch (IOException e) {
			System.out.println("Problem saving student to file");
			nameTextField.setText("Problem saving student to file");
		} finally {
			try {
				if (in != null) in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}    	
    }

    private void updateChoiceBox() {
    	System.out.println(students);
        studentChoiceBox.setItems(FXCollections.observableArrayList(students));
    }
}
