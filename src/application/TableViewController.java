package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class TableViewController implements Initializable{

	
    @FXML
    private TextField workoutTextField;

    @FXML
    private TableColumn<Workout, String> colTime;

    @FXML
    private TextField dateTextField;

    @FXML
    private Button delButton;

    @FXML
    private TableView<Workout> tableView;

    @FXML
    private TextField timeTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private TableColumn<Workout, String> colDate;

    @FXML
    private TableColumn<Workout, String> colWorkout;
    
    private ObservableList<Workout> workoutData = FXCollections.observableArrayList();
    
    
    @FXML
    public void addWorkout(ActionEvent event) throws IOException{
    	
		// check if valid date
		if(!(Pattern.matches("[0-9]{2}[/][0-9]{2}[/][0-9]{4}",dateTextField.getText() ))){
			//alert 
			Alert a1 = new Alert(Alert.AlertType.INFORMATION);
			a1.setTitle("Alert");
			a1.setHeaderText("Date must me in the format XX/XX/XXXX");
			
			a1.showAndWait();
			
		
		}
		else{
			
			// check if valid time
			if(!(Pattern.matches("[0-9]{2}[:][0-9]{2}",timeTextField.getText() ))){
				//alert 
				Alert a1 = new Alert(Alert.AlertType.INFORMATION);
				a1.setTitle("Alert");
				a1.setHeaderText("Time must me in the format XX:XX");
				
				a1.showAndWait();
				
			
			}
			else{
				Workout newWorkout = new Workout(dateTextField.getText(),workoutTextField.getText(),timeTextField.getText());
				tableView.getItems().add(newWorkout);
			}
		}
    	
    	//Workout newWorkout = new Workout(dateTextField.getText(),workoutTextField.getText(),timeTextField.getText());
    	
    	
    	//tableView.getItems().add(newWorkout);
    	
    	dateTextField.clear();
    	workoutTextField.clear();
    	timeTextField.clear();
    }
    
    @FXML
    public void delWorkout(ActionEvent event) throws IOException{
    	ObservableList<Workout> selectedRows, allWorkouts;
    	allWorkouts = tableView.getItems();
    	
    	selectedRows = tableView.getSelectionModel().getSelectedItems();
    	
    	for (Workout workout: selectedRows){
    		allWorkouts.remove(workout);
    	}
    }
    
    public void changeDateCol(CellEditEvent edittedCell){
    	
    	Workout workoutSelected = tableView.getSelectionModel().getSelectedItem();
    	workoutSelected.setDate(edittedCell.getNewValue().toString());
    }
    
    public void changenameCol(CellEditEvent edittedCell){
    	
    	Workout workoutSelected = tableView.getSelectionModel().getSelectedItem();
    	workoutSelected.setName(edittedCell.getNewValue().toString());
    }
    
    public void changeTimeCol(CellEditEvent edittedCell){
    	
    	Workout workoutSelected = tableView.getSelectionModel().getSelectedItem();
    	workoutSelected.setTime(edittedCell.getNewValue().toString());
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		colDate.setCellValueFactory(new PropertyValueFactory<Workout, String>("date"));
		colWorkout.setCellValueFactory(new PropertyValueFactory<Workout, String>("name"));
		colTime.setCellValueFactory(new PropertyValueFactory<Workout, String>("time"));
		
		tableView.setEditable(true);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		colDate.setCellFactory(TextFieldTableCell.forTableColumn());
		colWorkout.setCellFactory(TextFieldTableCell.forTableColumn());
		colTime.setCellFactory(TextFieldTableCell.forTableColumn());
		
	}
}
