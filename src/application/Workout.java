package application;

import javafx.beans.property.SimpleStringProperty;

public class Workout {
	
	private SimpleStringProperty date;
	private SimpleStringProperty name;
	private SimpleStringProperty time;
	
	public Workout(String date, String name, String time){
		this.date = new SimpleStringProperty(date);
		this.name = new SimpleStringProperty(name);
		this.time = new SimpleStringProperty(time);
	}
	
	public String getDate() {
		return date.get();
	}

	public void setDate(String date) {
		this.date = new SimpleStringProperty(date);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getTime() {
		return time.get();
	}

	public void setTime(String time) {
		this.time = new SimpleStringProperty(time);
	}



}
