package ui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;


public class StartTab extends QisTab{
	public StartTab(){
		
		GridPane anleitungBox = new GridPane();
		anleitungBox.setHgap(10);
		anleitungBox.setVgap(10);
		anleitungBox.getStyleClass().add("border");
		anleitungBox.setPadding(new Insets(15));
		GridPane.setMargin(anleitungBox, new Insets(0, 60, 0, 0)); 	//setzt margin Für die Anleitung (Berührt nicht das Logo)
		
		Label z1 = new Label("1.");
		GridPane.setValignment(z1, VPos.TOP); 
		HBox anleitungZ1 = new HBox();
		
		Label anleitung1_1 = new Label("Melden Sie sich im ");
		anleitung1_1.setMinWidth(Region.USE_PREF_SIZE);
		Hyperlink qisLink = new Hyperlink("QIS");
		qisLink.setOnAction((ActionEvent event)->{
			super.getHostServices().showDocument("https://www.qis.fh-aachen.de"); 	// Öffnet den Browser zur QIS-Seite		
		});
		qisLink.setMinWidth(Region.USE_PREF_SIZE);
//		qisLink.setWrapText(true);
		WrapLabel anleitung1_2 = new WrapLabel(" mit Ihren Nutzerdaten an.");
		anleitungZ1.getChildren().addAll(anleitung1_1, qisLink, anleitung1_2);
		
		Label z2 = new Label("2.");
		GridPane.setValignment(z2, VPos.TOP);
		WrapLabel anleitung2 = new WrapLabel("Klicken Sie auf den Punkt 'Notenspiegel' Und danach hinter dem" +
				"entsprechenden Studiengang auf 'Info'.");
		
		Label z3 = new Label("3.");
		GridPane.setValignment(z3, VPos.TOP);
		WrapLabel anleitung3 = new WrapLabel("Exportieren Sie die HTML-Seite, indem Sie Strg und s" +
				" auf Ihrer Tastatur Drücken oder mit Rechtsklick irgendwo auf die Seite und '(Seite)" +
				" speichern unter...' drücken");
		
		Label z4 = new Label("4.");
		GridPane.setValignment(z4, VPos.TOP);
		WrapLabel anleitung4 = new WrapLabel("Importieren Sie nun die HTML-Datei mit hilfe des '...' Buttons."
				+ "Wählen Sie im geöffneten Datei-Browse die entsprechende Datei aus und bestätigen Sie die"
				+ "Auswahl. Im Textfeld neben dem Button wird nun der Pfad der Datei angezeigt. Zum schluss"
				+ "drücken Sie aud 'Upload' um die Daten in das Programm  zu Lesen");
		
		
		anleitungBox.addRow(0, z1, anleitungZ1);
		anleitungBox.addRow(1, z2, anleitung2);
		anleitungBox.addRow(2, z3,anleitung3);
		anleitungBox.addRow(3, z4, anleitung4);
		GridPane.setHgrow(anleitungBox, Priority.ALWAYS); // Gibt an ob das Element bei resizing Horizontal größer werden soll/darf 
		
//		GridPane.setMargin(anleitungBox, new Insets(20, 0, 0, 20));
//		anleitungBox.getChildren().addAll(anleitungZ1, anleitung2);
		this.add(anleitungBox, 0, 0,2,1);
//		Text anleitung2 = new Text("Anleitung");
//		this.add(anleitung2, 6, 6);
//		TableView<String> table = new TableView<String>();
//		table.setPrefSize(100, 100);
//		this.add(table, 7, 7);
		
		HBox importBox = new HBox();
		importBox.setMaxWidth(400);
		importBox.setPrefWidth(400);
		TextField importTf = new TextField();
		importTf.setPromptText("Durchsuchen...");
		importTf.getStyleClass().add("importTf");
		
		Button importBtn = new Button("...");
		importBtn.getStyleClass().add("importBtn");
		importBtn.setOnAction(event ->{
			
		});
		HBox.setHgrow(importTf, Priority.ALWAYS);
		HBox.setHgrow(importBtn, Priority.NEVER);
//		GridPane.setHgrow(importBox, Priority.ALWAYS);
		importBox.getChildren().addAll(importTf,importBtn);
		
		
		
		this.add(importBox, 0, 1);
		Button upload = new Button("Upload");
		GridPane.setHgrow(upload, Priority.NEVER);
		upload.setOnAction(event ->{
			
		});
		this.add(upload, 1, 1);
		
		HBox versucheBox = new HBox();
		versucheBox.setSpacing(15.0);
		Text versucheTx = new Text("Verbesserungsversuche:");
		versucheTx.getStyleClass().add("tabtext");

		ToggleGroup versucheGr = new ToggleGroup();
		
		RadioButton v0 = new RadioButton("0");
		v0.setToggleGroup(versucheGr);
		v0.setSelected(true);
		
		RadioButton v1 = new RadioButton("1");
		v1.setToggleGroup(versucheGr);
		
		RadioButton v2 = new RadioButton("2");
		v2.setToggleGroup(versucheGr);
		
		RadioButton v3 = new RadioButton("3");
		v3.setToggleGroup(versucheGr);
		
		versucheBox.getChildren().addAll(versucheTx, v0, v1, v2, v3);
		this.add(versucheBox, 0, 2);
//		this.setGridLinesVisible(true);
	}
	

}
