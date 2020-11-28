package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

public class TabController implements Initializable{
	Date todayDate = Calendar.getInstance().getTime();
	Calendar c;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private TextArea calBox;
	
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
    private TextField commentTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private TableColumn<Workout, String> colDate;

    @FXML
    private TableColumn<Workout, String> colWorkout;
    
    @FXML
    private TableColumn<Workout, String> colComment;
    
    private ObservableList<Workout> workoutData = FXCollections.observableArrayList();
    
    public String date;
    public String time;
    public String workout;
    public String comment;
    public inputTextFile itf = new inputTextFile();
    
    //Log Tab
    @FXML
    public void addWorkout(ActionEvent event) throws IOException{
    	
    	//Assign variables from respective text field
    	date = dateTextField.getText();
		time = timeTextField.getText();
		workout = workoutTextField.getText();
		comment = commentTextField.getText();
    	
		// check if valid date
		if(!(Pattern.matches("[0-9]{2}[/][0-9]{2}[/][0-9]{4}",date ))){
			//alert 
			Alert a1 = new Alert(Alert.AlertType.INFORMATION);
			a1.setTitle("Alert");
			a1.setHeaderText("Date must me in the format XX/XX/XXXX");
			
			a1.showAndWait();
			
		
		}
		else{
			
			// check if valid time
			if(!(Pattern.matches("[0-9]{2}[:][0-9]{2}",time))){
				//alert 
				Alert a1 = new Alert(Alert.AlertType.INFORMATION);
				a1.setTitle("Alert");
				a1.setHeaderText("Time must me in the format XX:XX");
				
				a1.showAndWait();
				
			
			}
			else{
				if(comment.isEmpty()) {
					comment = "";
				}
				Workout newWorkout = new Workout(date, workout, time, comment);
				tableView.getItems().add(newWorkout);
				itf.addToTextFile(date, time, workout, comment);
			}
		}
    	
    	//Workout newWorkout = new Workout(dateTextField.getText(),workoutTextField.getText(),timeTextField.getText());
    	
    	
    	//tableView.getItems().add(newWorkout);
    	
    	dateTextField.clear();
    	workoutTextField.clear();
    	timeTextField.clear();
    	commentTextField.clear();
    }
    
    @FXML
    public void delWorkout(ActionEvent event) throws IOException{
    	ObservableList<Workout> selectedRows, allWorkouts;
    	allWorkouts = tableView.getItems();
    	
    	selectedRows = tableView.getSelectionModel().getSelectedItems();
    	
    	for (Workout workout: selectedRows){
    		allWorkouts.remove(workout);
    		itf.editTextFile(workout, workout, "rem");
    	}
    }
    
    public void changeDateCol(CellEditEvent edittedCell){
    	
    	Workout workoutSelected = tableView.getSelectionModel().getSelectedItem();
    	Workout oldWorkout = new Workout(workoutSelected.getDate(), workoutSelected.getName(), workoutSelected.getTime(), workoutSelected.getComment());
    	workoutSelected.setDate(edittedCell.getNewValue().toString());
    	Workout newWorkout = workoutSelected;
    	itf.editTextFile(oldWorkout, newWorkout, "edit");
    	
    }
    
    public void changenameCol(CellEditEvent edittedCell){
    	
    	Workout workoutSelected = tableView.getSelectionModel().getSelectedItem();
    	Workout oldWorkout = new Workout(workoutSelected.getDate(), workoutSelected.getName(), workoutSelected.getTime(), workoutSelected.getComment());
    	workoutSelected.setName(edittedCell.getNewValue().toString());
    	Workout newWorkout = workoutSelected;
    	itf.editTextFile(oldWorkout, newWorkout, "edit");

    }
    
    public void changeTimeCol(CellEditEvent edittedCell){
    	
    	Workout workoutSelected = tableView.getSelectionModel().getSelectedItem();
    	Workout oldWorkout = new Workout(workoutSelected.getDate(), workoutSelected.getName(), workoutSelected.getTime(), workoutSelected.getComment());
    	workoutSelected.setTime(edittedCell.getNewValue().toString());
    	Workout newWorkout = workoutSelected;
    	itf.editTextFile(oldWorkout, newWorkout, "edit");

    }
    
    public void changeCommentCol(CellEditEvent edittedCell){
    	
    	Workout workoutSelected = tableView.getSelectionModel().getSelectedItem();
    	Workout oldWorkout = new Workout(workoutSelected.getDate(), workoutSelected.getName(), workoutSelected.getTime(), workoutSelected.getComment());
    	workoutSelected.setComment(edittedCell.getNewValue().toString());
    	Workout newWorkout = workoutSelected;
    	itf.editTextFile(oldWorkout, newWorkout, "edit");

    }
    
    public void displayInfo(ActionEvent event) throws IOException {
    	LocalDate ld = datePicker.getValue();
    	tCalendar cal = new tCalendar();
    	cal.printInfo(ld, calBox);
    	
    }
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		colDate.setCellValueFactory(new PropertyValueFactory<Workout, String>("date"));
		colWorkout.setCellValueFactory(new PropertyValueFactory<Workout, String>("name"));
		colTime.setCellValueFactory(new PropertyValueFactory<Workout, String>("time"));
		colComment.setCellValueFactory(new PropertyValueFactory<Workout, String>("comment"));
		
		tableView.setEditable(true);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		colDate.setCellFactory(TextFieldTableCell.forTableColumn());
		colWorkout.setCellFactory(TextFieldTableCell.forTableColumn());
		colTime.setCellFactory(TextFieldTableCell.forTableColumn());
		colComment.setCellFactory(TextFieldTableCell.forTableColumn());
		
		tCalendar cal = new tCalendar();
		try {
			cal.makeCalendar(datePicker);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			FileReader file = new FileReader("log.txt");
			BufferedReader br = new BufferedReader(file);
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				String[] split = currentLine.split(" ", 4);
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					Date fString = todayDate;
					try {
						fString = format.parse(split[0]);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				if(!todayDate.before(fString)) {
					if( split[3] == null) {
					Workout dayWorkout = new Workout(split[0], split[1], split[2], "");
					tableView.getItems().add(dayWorkout);
					}
					else {
						Workout dayWorkout = new Workout(split[0], split[1], split[2], split[3]);
						tableView.getItems().add(dayWorkout);
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
}
