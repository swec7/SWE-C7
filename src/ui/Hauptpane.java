package ui;

import javafx.application.HostServices;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Hauptpane extends BorderPane {
	private Text activeText;
	public Hauptpane(HostServices services){
		//this.setMinSize(800, 600);
		this.setPrefSize(800, 600);
		VBox box = new VBox();
		box.getStyleClass().add("box");
		box.setPrefSize(200, 600);
		box.setMaxWidth(200);
		HBox hStart = new HBox();
		hStart.getStyleClass().add("tabbox");
		hStart.setAlignment(Pos.CENTER);
		hStart.setPrefSize(200, 100);
		Text startText = new Text("Start");
		startText.getStyleClass().add("tabtext");
		hStart.getChildren().add(startText);
		startText.getStyleClass().add("pressed");
		activeText = startText;
		
		HBox hUebersicht = new HBox();
		hUebersicht.getStyleClass().add("tabbox");
		hUebersicht.setAlignment(Pos.CENTER);
		hUebersicht.setPrefSize(200, 100);
		Text uebersichtText = new Text("Übersicht");
		uebersichtText.getStyleClass().add("tabtext");
		hUebersicht.getChildren().add(uebersichtText);
		
		HBox hKlausuren = new HBox();
		hKlausuren.getStyleClass().add("tabbox");
		hKlausuren.setAlignment(Pos.CENTER);
		hKlausuren.setPrefSize(200, 100);
		Text klausurenText = new Text("Klausuren");
		klausurenText.getStyleClass().add("tabtext");
		hKlausuren.getChildren().add(klausurenText);
		
		HBox hKalkulator = new HBox();
		hKalkulator.getStyleClass().add("tabbox");
		hKalkulator.setAlignment(Pos.CENTER);
		hKalkulator.setPrefSize(200, 100);
		Text kalkulatorText = new Text("Kalkulator");
		kalkulatorText.getStyleClass().add("tabtext");
		hKalkulator.getChildren().add(kalkulatorText);
		
		StartTab start = new StartTab();
		start.setHostServices(services);
		QisTab uebersicht = new QisTab();
		QisTab klausuren = new QisTab();
		QisTab kalkulator = new QisTab();

		hStart.setOnMouseClicked(event ->{
			activeText.getStyleClass().remove("pressed");
			activeText = startText;
			startText.getStyleClass().add("pressed");	
			this.setCenter(start);
		});
		
		hUebersicht.setOnMouseClicked(event ->{
			activeText.getStyleClass().remove("pressed");
			activeText = uebersichtText;
			uebersichtText.getStyleClass().add("pressed");	
			this.setCenter(uebersicht);
		});
		
		hKlausuren.setOnMouseClicked(event ->{
			activeText.getStyleClass().remove("pressed");
			activeText = klausurenText;
			klausurenText.getStyleClass().add("pressed");	
			this.setCenter(klausuren);
		});
		
		hKalkulator.setOnMouseClicked(event ->{
			activeText.getStyleClass().remove("pressed");
			activeText = kalkulatorText;
			kalkulatorText.getStyleClass().add("pressed");	
			this.setCenter(kalkulator);
		});
		
		
		
		HBox ueberschriftBox = new HBox();
		ueberschriftBox.getStyleClass().add("ueberschriftBox");
		
		Text ueberschrift = new Text("Klausurenplaner");
		ueberschrift.getStyleClass().add("ueberschrift");
		
		ueberschriftBox.getChildren().add(ueberschrift);
		
		
		box.getChildren().add(hStart);
		box.getChildren().add(hUebersicht);
		box.getChildren().add(hKlausuren);
		box.getChildren().add(hKalkulator);
		this.setLeft(box);
		this.setCenter(start);
		this.setTop(ueberschriftBox);

		
		
	}
}
