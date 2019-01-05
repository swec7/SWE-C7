package ui;

import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class QisTab extends GridPane{

	private HostServices services;

	public QisTab(){
		this.setPadding(new Insets(20, 20, 20, 20));	// setzt das Padding des Panels mit den Insets (top, right, bot, left)
		this.setVgap(20);	// Setzt vertikalen Abstand der Elemente im GridPane
		this.setHgap(20);	// Setzt horizontalen Abstand der Elemente im GridPane
		getStyleClass().add("tab");
	}
	
	public void setHostServices(HostServices services){
		this.services = services;
	}
	
	public HostServices getHostServices(){
		return services;
	}

}
