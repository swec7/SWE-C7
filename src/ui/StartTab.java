package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import daten.Benutzer;
import daten.Studiengang;
import einlesen.CSVReader;
import einlesen.Factory;
import einlesen.HTMLDaten;
import einlesen.HTMLParser;
import exceptions.CSVLeseException;
import exceptions.HTMLLeseException;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartTab extends QisTab {

	String path = "";

	float anzVersuche = 3;

	Benutzer user = null;

	TextField importTf;

	Factory factory = new Factory();

	public StartTab(Hauptpane haupt) {

		GridPane anleitungBox = new GridPane();
		anleitungBox.setHgap(10);
		anleitungBox.setVgap(10);
		anleitungBox.getStyleClass().add("border");
		anleitungBox.setPadding(new Insets(15));
		GridPane.setMargin(anleitungBox, new Insets(0, 60, 0, 0)); // setzt
																	// margin
		// F?r die
		// Anleitung
		// (Ber?hrt
		// nicht das
		// Logo)

		Label z1 = new Label("1.");
		GridPane.setValignment(z1, VPos.TOP);
		HBox anleitungZ1 = new HBox();

		Label anleitung1_1 = new Label("Melden Sie sich im ");
		anleitung1_1.setMinWidth(Region.USE_PREF_SIZE);
		Hyperlink qisLink = new Hyperlink("QIS");
		qisLink.setOnAction((ActionEvent event) -> {
			super.getHostServices().showDocument("https://www.qis.fh-aachen.de"); // ?ffnet
			// den
			// Browser
			// zur
			// QIS-Seite
		});
		qisLink.setMinWidth(Region.USE_PREF_SIZE);
		// qisLink.setWrapText(true);
		WrapLabel anleitung1_2 = new WrapLabel(" mit Ihren Nutzerdaten an.");
		anleitungZ1.getChildren().addAll(anleitung1_1, qisLink, anleitung1_2);

		Label z2 = new Label("2.");
		GridPane.setValignment(z2, VPos.TOP);
		WrapLabel anleitung2 = new WrapLabel("Klicken Sie auf den Link 'Notenspiegel' und danach auf 'Info'.");

		Label z3 = new Label("3.");
		GridPane.setValignment(z3, VPos.TOP);
		WrapLabel anleitung3 = new WrapLabel("Speichern Sie die HTML-Seite, indem Sie  STRG + S " +
				"gleichzeitig auf Ihrer Tastatur drücken oder betätigen Sie die rechte " +
				"Maustaste und klicken auf" +
				" '(Seite) speichern unter...'. Anschließend wählen Sie aus, wo die Datei auf Ihrem " +
				"Computer " +
				"gespeichert werden soll (z.B. Desktop)");

		GridPane.setValignment(z3, VPos.TOP);

		Label z4 = new Label("4.");
		GridPane.setValignment(z4, VPos.TOP);
		WrapLabel anleitung4 = new WrapLabel("Nachdem Sie auf den '...' Knopf gedrückt haben, müssen Sie die Datei," +
				" welche Sie eben gespeichert haben im Datei-Browser suchen und auswählen." +
				"Im Textfeld neben dem 'Importieren' Knopf sollte nun der Pfad zur Datei angezeigt werden."+
				" Sie können noch auswählen, wie viele Verbesserungsversuche Ihnen noch verbleiben." +
				 " Abschließend drücken Sie auf 'Importieren' um Ihre Daten im Programm zu importieren");

		anleitungBox.addRow(0, z1, anleitungZ1);
		anleitungBox.addRow(1, z2, anleitung2);
		anleitungBox.addRow(2, z3, anleitung3);
		anleitungBox.addRow(3, z4, anleitung4);
		GridPane.setHgrow(anleitungBox, Priority.ALWAYS); // Gibt an ob das
															// Element
		// bei resizing Horizontal
		// gr??er werden soll/darf

		// GridPane.setMargin(anleitungBox, new Insets(20, 0, 0, 20));
		// anleitungBox.getChildren().addAll(anleitungZ1, anleitung2);
		this.add(anleitungBox, 0, 0, 2, 1);
		// Text anleitung2 = new Text("Anleitung");
		// this.add(anleitung2, 6, 6);
		// TableView<String> table = new TableView<String>();
		// table.setPrefSize(100, 100);
		// this.add(table, 7, 7);

		// TODO
		HBox importBox = new HBox();
		importBox.setMaxWidth(400);
		importBox.setPrefWidth(400);
		importTf = new TextField();
		importTf.setPromptText("Durchsuchen...");
		importTf.getStyleClass().add("importTf");

		Button importBtn = new Button("...");
		importBtn.getStyleClass().add("importBtn");
		importBtn.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File file = fileChooser.showOpenDialog(new Stage());
			if (file != null) {
				path = file.getAbsolutePath();
				importTf.setText(path);
			}
			// System.out.println("FILE " + file.getAbsolutePath());
		});
		HBox.setHgrow(importTf, Priority.ALWAYS);
		HBox.setHgrow(importBtn, Priority.NEVER);
		// GridPane.setHgrow(importBox, Priority.ALWAYS);
		HBox internUpload = new HBox();
		HBox.setHgrow(internUpload, Priority.ALWAYS);

		Button upload = new Button("Importieren");
		GridPane.setHgrow(upload, Priority.NEVER);
		upload.setOnAction(event -> {

			try {
				path = importTf.getText();
				// importTf.setText("Everythings seems fine ;)");
				user = loadBenutzer(path);
				user.setVersuche(anzVersuche);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(path);
				alert.setContentText("Die Datei wurde erfolgreich geladen.");

				alert.showAndWait();
				haupt.enableTabs();
				haupt.ubersicht(getUser());
			} catch (CSVLeseException | IOException | HTMLLeseException e) {
				// importTf.setText(e.getMessage());
				// e.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error");
				alert.setContentText(e.getMessage());

				alert.showAndWait();
				haupt.disableTabs();
			}
		});
		//this.add(upload, 1, 1);
		internUpload.getChildren().addAll(importTf, importBtn);
		importBox.getChildren().addAll(internUpload, upload);
		importBox.setSpacing(20);


		this.add(importBox, 0, 1);


		HBox versucheBox = new HBox();
		versucheBox.setSpacing(10.0);
		Text versucheTx = new Text("Verbleibende Verbesserungsversuche:");
		versucheTx.getStyleClass().add("tabtext");

		ToggleGroup versucheGr = new ToggleGroup();

		RadioButton v0 = new RadioButton("0");
		v0.setToggleGroup(versucheGr);
		v0.setSelected(true);
		v0.setOnAction(event -> {
			anzVersuche = 0;
			if (user != null) {
				user.setVersuche(anzVersuche);
			}
		});

		RadioButton v1 = new RadioButton("1");
		v1.setToggleGroup(versucheGr);
		v1.setOnAction(event -> {
			anzVersuche = 1;
			if (user != null) {
				user.setVersuche(anzVersuche);
			}
		});

		RadioButton v2 = new RadioButton("2");
		v2.setToggleGroup(versucheGr);
		v2.setOnAction(event -> {
			anzVersuche = 2;
			if (user != null) {
				user.setVersuche(anzVersuche);
			}
		});

		RadioButton v3 = new RadioButton("3");
		v3.setToggleGroup(versucheGr);
		v3.setOnAction(event -> {
			anzVersuche = 3;
			if (user != null) {
				user.setVersuche(anzVersuche);
			}
		});
		v3.setSelected(true);

		RadioButton v4 = new RadioButton("4");
		v4.setToggleGroup(versucheGr);
		v4.setOnAction(event -> {
			anzVersuche = 4;
			if (user != null) {
				user.setVersuche(anzVersuche);
			}
		});

		RadioButton v5 = new RadioButton("5");
		v5.setToggleGroup(versucheGr);
		v5.setOnAction(event -> {
			anzVersuche = 5;
			if (user != null) {
				user.setVersuche(anzVersuche);
			}
		});

		versucheBox.getChildren().addAll(versucheTx, v0, v1, v2, v3, v4, v5);
		this.add(versucheBox, 0, 2);
		// this.setGridLinesVisible(true);
	}

	void setPath(String path) {
		this.path = path;
	}

	public Benutzer loadBenutzer(String path) throws CSVLeseException, IOException, HTMLLeseException {
		try {
			HTMLDaten htmlDaten = HTMLParser.loadHTML(path);

			List<List<String>> studiengaengeCSV = CSVReader.loadCsv("Studiengaenge.csv");
			List<List<String>> moduleCSV = null;
			int i = 1;
			for (; i < studiengaengeCSV.size(); i++) {
				// System.out.println(htmlDaten.getStudiengang() + " == " +
				// studiengaengeCSV.get(i).get(0));
				if (htmlDaten.getStudiengang().equals(studiengaengeCSV.get(i).get(0))) {
					moduleCSV = CSVReader.loadCsv(studiengaengeCSV.get(i).get(6) + ".csv");
					// System.out.println(moduleCSV);
					break;
				}
			}
			// System.out.println("HALLO ICH BIN HIER");
			// return new
			// Benutzer(Factory.buildStudiengang(studiengaengeCSV.get(i),
			// moduleCSV, htmlDaten.getMap()), 0,anzVersuche);
			// System.out.println(i);
			Studiengang stud = Factory.buildStudiengang(studiengaengeCSV.get(i), moduleCSV, htmlDaten.getMap());
			Benutzer ben = new Benutzer(stud, 0, anzVersuche);

			return ben;

		} catch (FileNotFoundException e) {
			// importTf.setText("Datei nicht gefunden !");
			throw e;
		} catch (HTMLLeseException e) {
			// importTf.setText(e.getMessage());
			throw e;
		}
	}

	public Benutzer getUser() {
		return this.user;
	}

}
