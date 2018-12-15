package ui;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StartTab extends QisTab{
	public StartTab(){
		
		GridPane anleitungBox = new GridPane();
		anleitungBox.setHgap(15);
		anleitungBox.getStyleClass().add("border");
		anleitungBox.setPadding(new Insets(15));
		this.setPadding(new Insets(20, 20, 20, 20));
		GridPane.setMargin(anleitungBox, new Insets(0, 50, 0, 0));
		Label z1 = new Label("1.");
		HBox anleitungZ1 = new HBox();
		WrapLabel anleitung1_1 = new WrapLabel("Melden Sie sich unter ");
		
		Hyperlink qisLink = new Hyperlink("https://www.qis.fh-aachen.de");
		qisLink.setOnAction((ActionEvent event)->{
			super.getHostServices().showDocument(qisLink.getText());
			
		});
		qisLink.setWrapText(true);
		WrapLabel anleitung1_2 = new WrapLabel(" mit Ihren Nutzerdaten an.");
		anleitungZ1.getChildren().addAll(anleitung1_1, qisLink, anleitung1_2);
		
		Label z2 = new Label("2.");
		WrapLabel anleitung2 = new WrapLabel("Klicken Sie auf den Punkt 'Notenspiegel' Und danach hinter dem" +
				"entsprechenden Studiengang auf 'Info'.");
		
		anleitungBox.addRow(0, z1, anleitungZ1);
		anleitungBox.addRow(1, z2, anleitung2);
		
		GridPane.setHgrow(anleitungBox, Priority.ALWAYS);
		
//		GridPane.setMargin(anleitungBox, new Insets(20, 0, 0, 20));
//		anleitungBox.getChildren().addAll(anleitungZ1, anleitung2);
		this.add(anleitungBox, 0, 0,3,1);
//		Text anleitung2 = new Text("Anleitung");
//		this.add(anleitung2, 6, 6);
//		TableView<String> table = new TableView<String>();
//		table.setPrefSize(100, 100);
//		this.add(table, 7, 7);
	}
	

}
