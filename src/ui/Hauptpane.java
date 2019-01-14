package ui;

import daten.Benutzer;
import javafx.application.HostServices;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Hauptpane extends BorderPane {

	private Text activeText; // Text des aktiven Tabs. Befindet sich innerhalb
								// der klickbaren HBox
	private HBox hKlausuren;
	private HBox hKalkulator;
	private HBox hStart;
	private HBox hUebersicht;
	private Text uebersichtText;

	public Hauptpane(HostServices services) {
		this.setPrefSize(800, 600);
		VBox box = new VBox();
		box.getStyleClass().add("box"); // Fügt dem Object eine style Class aus
										// der CSS datei hinzu
		box.setPrefSize(200, 600);
		box.setMaxWidth(200);
		hStart = new HBox();
		hStart.getStyleClass().add("tabbox");
		hStart.setAlignment(Pos.CENTER); // Positioniert Elemente in der Mitte
											// der von der Box zugeteilten
											// Fläche
		hStart.setPrefSize(200, 100);
		Text startText = new Text("Start");
		startText.getStyleClass().add("tabtext");
		hStart.getChildren().add(startText); // Fügt einem JavaFX Object Objects
												// hinzu
		startText.getStyleClass().add("pressed");
		activeText = startText;

		hUebersicht = new HBox();
		hUebersicht.setDisable(true);
		hUebersicht.getStyleClass().addAll("tabbox", "disable");
		hUebersicht.setAlignment(Pos.CENTER);
		hUebersicht.setPrefSize(200, 100);
		uebersichtText = new Text("Übersicht");
		uebersichtText.getStyleClass().add("tabtext");
		hUebersicht.getChildren().add(uebersichtText);

		hKlausuren = new HBox();
		hKlausuren.setDisable(true);
		hKlausuren.getStyleClass().addAll("tabbox", "disable");
		hKlausuren.setAlignment(Pos.CENTER);
		hKlausuren.setPrefSize(200, 100);
		Text klausurenText = new Text("Klausuren");
		klausurenText.getStyleClass().add("tabtext");
		hKlausuren.getChildren().add(klausurenText);

		hKalkulator = new HBox();
		hKalkulator.setDisable(true);
		hKalkulator.getStyleClass().addAll("tabbox", "disable");
		hKalkulator.setAlignment(Pos.CENTER);
		hKalkulator.setPrefSize(200, 100);
		Text kalkulatorText = new Text("Kalkulator");
		kalkulatorText.getStyleClass().add("tabtext");
		hKalkulator.getChildren().add(kalkulatorText);

		StartTab start = new StartTab(this);
		start.setHostServices(services); // setzt die HostServices für das Tab
											// (wird beim Hyperlink benötigt)
		// UebersichtTab uebersicht = new UebersichtTab();
		// KlausurenTab klausuren = new KlausurenTab();
		// KalkulatorTab kalkulator = new KalkulatorTab();

		hStart.setOnMouseClicked(event -> { // Gibt der HBox das ClickEvent
			activeText.getStyleClass().remove("pressed"); // entfernt den Style
															// des Texts zum
															// aktiiven Tab
			activeText = startText;
			startText.getStyleClass().add("pressed");
			this.setCenter(start); // Wechselt Tab
		});

		hUebersicht.setOnMouseClicked(event -> {
			UebersichtTab uebersicht = new UebersichtTab(start.getUser());
			activeText.getStyleClass().remove("pressed");
			activeText = uebersichtText;
			uebersichtText.getStyleClass().add("pressed");
			this.setCenter(uebersicht);
		});

		hKlausuren.setOnMouseClicked(event -> {
			KlausurenTab klausuren = new KlausurenTab(start.getUser());
			activeText.getStyleClass().remove("pressed");
			activeText = klausurenText;
			klausurenText.getStyleClass().add("pressed");
			this.setCenter(klausuren);
		});

		hKalkulator.setOnMouseClicked(event -> {
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

	public void disableTabs() {
		hKlausuren.getStyleClass().add("disable");
		hUebersicht.getStyleClass().add("disable");
		hKalkulator.getStyleClass().add("disable");
		hKlausuren.setDisable(true);
		hUebersicht.setDisable(true);
		hKalkulator.setDisable(true);
	}

	public void enableTabs() {
		hKlausuren.getStyleClass().remove("disable");
		hUebersicht.getStyleClass().remove("disable");
		hKalkulator.getStyleClass().remove("disable");
		hKlausuren.setDisable(false);
		hUebersicht.setDisable(false);
		hKalkulator.setDisable(false);
	}

	public void ubersicht(Benutzer ben) {
		UebersichtTab uebersicht = new UebersichtTab(ben);
		activeText.getStyleClass().remove("pressed");
		activeText = uebersichtText;
		uebersichtText.getStyleClass().add("pressed");
		this.setCenter(uebersicht);
	}
}