package ui;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Hauptpane extends BorderPane {
	public Hauptpane(){
		this.setMinSize(800, 600);
		this.setPrefSize(800, 600);
		VBox box = new VBox();
		box.getStyleClass().add("box");
		box.setPrefSize(200, 600);
		
		HBox hStart = new HBox();
		hStart.getStyleClass().add("tabbox");
		hStart.setAlignment(Pos.CENTER);
		hStart.setPrefSize(200, 100);
		Text startText = new Text("Start");
		startText.getStyleClass().add("tabtext");
		hStart.getChildren().add(startText);
		
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
		
		
		box.getChildren().add(hStart);
		box.getChildren().add(hUebersicht);
		box.getChildren().add(hKlausuren);
		box.getChildren().add(hKalkulator);
		this.setLeft(box);
	}
}
