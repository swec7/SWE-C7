package ui;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import daten.Modul;
import daten.Typ;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class KlausurenTab extends QisTab {

	//SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


	public KlausurenTab(){
		//DEBUG ----------------------------------------------------------
		//Data from parser follows
		float durchschnittsnote = 1.6f;
		int versuche = 2;
		//Testdaten follows
        Modul m1 = new Modul(51101, "Höhere Mathematik 1", 8, 3.0f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1, Typ.PFLICHT, 0);
        Modul m2 = new Modul(51104, "Grundlagen der Informatik und höhere Programmiersprache für Informatik", 11, 1.3f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1, Typ.PFLICHT, 0);
        Modul m3 = new Modul(55667, "Kommunikationstechnicken", 2, 0.0f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 2, Typ.SOFTSKILL, 0);
        Modul m4 = new Modul(55668, "Wissenschaftliches Arbeiten", 2, 0.0f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1, Typ.SOFTSKILL, 0);
        Modul m5 = new Modul(52105, "Technische Informatik", 2, 1.3f, 2, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1, Typ.PFLICHT, 0);
        Modul m6 = new Modul(55667, "Algorithmen und Datenstrukturen", 8, -1.0f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 2, Typ.PFLICHT, 0);
		//END DEBUG ------------------------------------------------------


		//Output Texte des Tabs
		Text bestandenTx = new Text("Bestandene Klausuren");
		Text durchschnittTx = new Text("Aktuelle durchschnittsnote:\t"+durchschnittsnote);
		Text verbesserungsversucheTx = new Text("Verbleibende Verbesserungsversuche:\t" + versuche);
		Text nichtBestandenTx = new Text("nicht Bestandene Klausuren");
		//CSS Styles der Texte
		bestandenTx.getStyleClass().add("zueberschrift");
		durchschnittTx.getStyleClass().add("tabtext");
		verbesserungsversucheTx.getStyleClass().add("tabtext");
		nichtBestandenTx.getStyleClass().add("zueberschrift");
		//Properties der Texte
		GridPane.setValignment(bestandenTx, VPos.CENTER);
		GridPane.setHalignment(bestandenTx, HPos.CENTER);

		//Tabellen
		TableView<Modul> bestandeneKlausuren = new TableView<>();
		TableView<Modul> nichtBestandeneKlausuren = new TableView<>();
		//CSS Styles der Tabellen
		bestandeneKlausuren.getStyleClass().add("grey");
		nichtBestandeneKlausuren.getStyleClass().add("grey");
		//Colomns von beiden Tabellen
		TableColumn<Modul, String> modulname = new TableColumn<>("Modulname");
		TableColumn<Modul, Integer> semester = new TableColumn<>("Semester");
		TableColumn<Modul, Integer> credits = new TableColumn<>("Credits");
		//Columns von bestandeneKlausuren
		TableColumn<Modul, Float> note = new TableColumn<>("Note");
		TableColumn<Modul, Float> verbesserungspotenzial = new TableColumn<>("Verbesserungspotenzial");
		TableColumn<Modul, LocalDate> ablaufdatum = new TableColumn<>("Ablaufdatum");
		bestandeneKlausuren.getColumns().addAll(modulname, semester, credits, note, verbesserungspotenzial, ablaufdatum);
		//Properties von bestandeneKlausuren
		bestandeneKlausuren.setFocusTraversable(false);
//		note.prefWidthProperty().bind(new SimpleIntegerProperty(50));
		GridPane.setHgrow(bestandeneKlausuren, Priority.ALWAYS);
		GridPane.setVgrow(bestandeneKlausuren, Priority.SOMETIMES);
		//Columns von nichtBestandeneKlausuren
		TableColumn<Modul, Date> versucheNB = new TableColumn<>("Versuche");
		nichtBestandeneKlausuren.getColumns().addAll(modulname, semester, credits, versucheNB);
		//Properties von nichtBestandeneKlausuren
		nichtBestandeneKlausuren.setFocusTraversable(false);
		GridPane.setValignment(nichtBestandenTx, VPos.CENTER);
		GridPane.setHalignment(nichtBestandenTx, HPos.CENTER);
		GridPane.setVgrow(nichtBestandeneKlausuren, Priority.SOMETIMES);
		nichtBestandeneKlausuren.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		//Tabelle set Items
        //TODO Use Parser Data
        bestandeneKlausuren.setItems( FXCollections.observableArrayList(m1,m2,m3,m4,m5,m6));
        nichtBestandeneKlausuren.setItems( FXCollections.observableArrayList(m1,m2,m3,m4,m5,m6));

//		GridPane.setMargin(bestandenTx, new Insets(0, 60, 0, 0));
//		GridPane.setMargin(bestandeneKlausuren, new Insets(0, 60, 0, 0));

		modulname.setCellValueFactory(new PropertyValueFactory<>("name"));
		semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
		credits.setCellValueFactory(new PropertyValueFactory<>("credits"));
		note.setCellValueFactory(new PropertyValueFactory<>("note"));
		ablaufdatum.setCellValueFactory(new PropertyValueFactory<>("ablaufdatum"));
		ablaufdatum.setCellFactory((TableColumn<Modul, LocalDate> column) -> {
			   return new TableCell<Modul, LocalDate>() {
			      @Override
			      protected void updateItem(LocalDate item, boolean empty) {
			         super.updateItem(item, empty);
			         if (item == null || empty) {
			            setText(null);
			         } else {
			            setText(item.getDayOfMonth()+"."+ item.getMonthValue()+"."+item.getYear());
			        }
			     }
			   };
			});
		verbesserungspotenzial.setCellFactory((TableColumn<Modul, Float> column) -> {
			   return new TableCell<Modul, Float>() {
				      @Override
				      protected void updateItem(Float item, boolean empty) {
				         super.updateItem(item, empty);
				        //---------> Methode für verbesserungsversuch
				         setText("Verbesserung");
				     }
				   };
				});

//		GridPane.setMargin(bestandeneKlausuren, new Insets(0, 60, 0, 0));
		versucheNB.setCellValueFactory(new PropertyValueFactory<>("versuche"));
		//Location
		this.setPadding(new Insets(20, 80, 20, 20));
		this.setVgap(10);
		this.add(bestandenTx, 0, 0);
		this.add(bestandeneKlausuren, 0, 1);
		this.add(durchschnittTx, 0, 2);
		this.add(verbesserungsversucheTx, 0, 3);
		this.add(nichtBestandenTx, 0, 4);
		this.add(nichtBestandeneKlausuren, 0, 5);
//		------------------------------------------------------------------------------------------
//		this.setGridLinesVisible(true);
	}
}
