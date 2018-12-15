package ui;

import javafx.application.HostServices;
import javafx.scene.layout.GridPane;

public class QisTab extends GridPane{
	private HostServices services;

	public QisTab(){
		
		getStyleClass().add("tab");
	}
	
	public void setHostServices(HostServices services){
		this.services = services;
	}
	
	public HostServices getHostServices(){
		return services;
	}

}
