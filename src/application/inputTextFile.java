package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.Alert;

public class inputTextFile {
	
	public void addToTextFile(String date, String time, String workout, String comment) throws IOException{
		FileWriter file1 = new FileWriter("log.txt", true);
		BufferedWriter bw = new BufferedWriter(file1);
		bw.write(date + " " + workout + " " + time + " " + comment + "\n");
		bw.close();
	}
	
	public void editTextFile(Workout oldWorkout, Workout newWorkout, String edit){
		try {
			File log = new File("log.txt");
			File temp = new File("temp.txt");
			FileWriter file1 = new FileWriter("temp.txt");
			FileReader file2 = new FileReader("log.txt");
			BufferedWriter bw = new BufferedWriter(file1);
			BufferedReader br = new BufferedReader(file2);
			String curLine;
			String newLine;
			String inputString = oldWorkout.getDate() + " " + oldWorkout.getName() + " " + oldWorkout.getTime() + " " + oldWorkout.getComment();
			while((curLine = br.readLine()) != null) {
				newLine = curLine.trim();
				if(newLine.equals(inputString)) {
					if(edit.equals("rem")) {
						curLine = "";
						bw.write(curLine);
						continue;
					}
					else if (edit.equals("edit")) {
						curLine = newWorkout.getDate() + " " + newWorkout.getName() + " " + newWorkout.getTime() + " " + newWorkout.getComment();
					}
				}
				bw.write(curLine + "\n");
			}
			bw.close();
			br.close();
			log.delete();
			temp.renameTo(log);
			
		}
		catch(IOException e) {
			Alert a1 = new Alert(Alert.AlertType.INFORMATION);
			a1.setTitle("Alert");
			a1.setHeaderText("Could not find file");
			a1.showAndWait();
		}
	}
}
