package ui;

import daten.Benutzer;
import javafx.application.HostServices;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Hauptpane extends BorderPane {
	
	private Text activeText; 	//Text des aktiven Tabs. Befindet sich innerhalb der klickbaren HBox
	
	
	public Hauptpane(HostServices services){
		

		this.setPrefSize(800, 600);
		VBox box = new VBox();
		box.getStyleClass().add("box"); 	// F?gt dem Object eine style Class aus der CSS datei hinzu
		box.setPrefSize(200, 600);
		box.setMaxWidth(200);
		HBox hStart = new HBox();
		hStart.getStyleClass().add("tabbox");
		hStart.setAlignment(Pos.CENTER);	// Positioniert Elemente in der Mitte der von der Box zugeteilten Fl?che
		hStart.setPrefSize(200, 100);
		Text startText = new Text("Start");
		startText.getStyleClass().add("tabtext");
		hStart.getChildren().add(startText);	// F?gt einem JavaFX Object Objects hinzu
		startText.getStyleClass().add("pressed");
		activeText = startText;
		
		HBox hUebersicht = new HBox();
		//hUebersicht.setDisable(true);
		hUebersicht.getStyleClass().add("tabbox");
		hUebersicht.setAlignment(Pos.CENTER);
		hUebersicht.setPrefSize(200, 100);
		Text uebersichtText = new Text("Übersicht");
		uebersichtText.getStyleClass().add("tabtext");
		hUebersicht.getChildren().add(uebersichtText);
		
		HBox hKlausuren = new HBox();
		//hKlausuren.setDisable(true);
		hKlausuren.getStyleClass().add("tabbox");
		hKlausuren.setAlignment(Pos.CENTER);
		hKlausuren.setPrefSize(200, 100);
		Text klausurenText = new Text("Klausuren");
		klausurenText.getStyleClass().add("tabtext");
		hKlausuren.getChildren().add(klausurenText);
		
		HBox hKalkulator = new HBox();
		//hKalkulator.setDisable(true);
		hKalkulator.getStyleClass().add("tabbox");
		hKalkulator.setAlignment(Pos.CENTER);
		hKalkulator.setPrefSize(200, 100);
		Text kalkulatorText = new Text("Kalkulator");
		kalkulatorText.getStyleClass().add("tabtext");
		hKalkulator.getChildren().add(kalkulatorText);
		
		StartTab start = new StartTab();
		start.setHostServices(services);	// setzt die HostServices f?r das Tab (wird beim Hyperlink ben?tigt)
		//UebersichtTab uebersicht = new UebersichtTab();
		//KlausurenTab klausuren = new KlausurenTab();
		//KalkulatorTab kalkulator = new KalkulatorTab();

		hStart.setOnMouseClicked(event ->{		// Gibt der HBox das ClickEvent
			activeText.getStyleClass().remove("pressed");	//entfernt den Style des Texts zum aktiiven Tab
			activeText = startText;
			startText.getStyleClass().add("pressed");	
			this.setCenter(start);							// Wechselt Tab
		});
		
		hUebersicht.setOnMouseClicked(event ->{
			UebersichtTab uebersicht = new UebersichtTab(start.getUser());
			activeText.getStyleClass().remove("pressed");
			activeText = uebersichtText;
			uebersichtText.getStyleClass().add("pressed");	
			this.setCenter(uebersicht);
		});
		
		hKlausuren.setOnMouseClicked(event ->{
			KlausurenTab klausuren = new KlausurenTab(start.getUser());
			activeText.getStyleClass().remove("pressed");
			activeText = klausurenText;
			klausurenText.getStyleClass().add("pressed");	
			this.setCenter(klausuren);
		});
		
		hKalkulator.setOnMouseClicked(event ->{
			KalkulatorTab kalkulator = new KalkulatorTab(start.getUser());
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
