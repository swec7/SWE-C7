package ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import daten.Benutzer;
import daten.Datum;
import daten.Modul;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class KlausurenTab extends QisTab {

	// SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	public KlausurenTab(Benutzer benutzer) {
		// //DEBUG ----------------------------------------------------------
		// //Data from parser follows
		// float durchschnittsnote = 1.6f;
		// int versuche = 2;
		// //Testdaten follows
		// Modul m1 = new Modul(51101, "Hoehere Mathematik 1", 8, 3.0f, 1,
		// LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1, Typ.PFLICHT,
		// 0);
		// Modul m2 = new Modul(51104, "Grundlagen der Informatik und hoehere
		// Programmiersprache fuer Informatik", 11, 1.3f, 1, LocalDate.of(2019,
		// 1, 1), LocalDate.of(2018, 1, 1), 1, Typ.PFLICHT, 0);
		// Modul m3 = new Modul(55667, "Kommunikationstechnicken", 2, 0.0f, 1,
		// LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 2, Typ.SOFTSKILL,
		// 0);
		// Modul m4 = new Modul(55668, "Wissenschaftliches Arbeiten", 2, 0.0f,
		// 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1,
		// Typ.SOFTSKILL, 0);
		// Modul m5 = new Modul(52105, "Technische Informatik", 2, 1.3f, 2,
		// LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1, Typ.PFLICHT,
		// 0);
		// Modul m6 = new Modul(55667, "Algorithmen und Datenstrukturen", 8,
		// -1.0f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 2,
		// Typ.PFLICHT, 0);
		// //END DEBUG ------------------------------------------------------

		// Parser Data
		float durchschnittsnote = benutzer.durchschnittsNote();
		int versuche = (int) benutzer.getVersuche();

		// Output Texte des Tabs
		Text bestandenTx = new Text("Bestandene Klausuren");
		Text durchschnittTx = new Text("Aktuelle Durchschnittsnote:\t" + durchschnittsnote);
		Text verbesserungsversucheTx = new Text("Verbleibende Verbesserungsversuche:\t" + versuche);
		Text nichtBestandenTx = new Text("Nicht bestandene Klausuren");
		// CSS Styles der Texte
		bestandenTx.getStyleClass().add("zueberschrift");
		durchschnittTx.getStyleClass().add("tabtext");
		verbesserungsversucheTx.getStyleClass().add("tabtext");
		nichtBestandenTx.getStyleClass().add("zueberschrift");
		// Properties der Texte
		GridPane.setValignment(bestandenTx, VPos.CENTER);
		GridPane.setHalignment(bestandenTx, HPos.CENTER);

		// Tabellen
		TableView<Modul> bestandeneKlausuren = new TableView<>();
		TableView<Modul> nichtBestandeneKlausuren = new TableView<>();
		// CSS Styles der Tabellen
		bestandeneKlausuren.getStyleClass().add("grey");
		nichtBestandeneKlausuren.getStyleClass().add("grey");
		// Columns von bestandeneKlausuren
		TableColumn<Modul, String> modulname = new TableColumn<>("Modulname");
		TableColumn<Modul, Integer> semester = new TableColumn<>("Semester");
		TableColumn<Modul, Integer> credits = new TableColumn<>("Credits");
		TableColumn<Modul, Float> note = new TableColumn<>("Note");
		TableColumn<Modul, Float> verbesserungspotenzial = new TableColumn<>("Verbesserungspotenzial");
		TableColumn<Modul, Datum> ablaufdatum = new TableColumn<>("Ablaufdatum");
		bestandeneKlausuren.getColumns().addAll(modulname, semester, credits, note, verbesserungspotenzial,
				ablaufdatum);
		// Properties von bestandeneKlausuren
		bestandeneKlausuren.setFocusTraversable(false);
		// note.prefWidthProperty().bind(new SimpleIntegerProperty(50));
		GridPane.setHgrow(bestandeneKlausuren, Priority.ALWAYS);
		GridPane.setVgrow(bestandeneKlausuren, Priority.SOMETIMES);

		bestandeneKlausuren.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		// Columns von nichtBestandeneKlausuren
		TableColumn<Modul, String> modulname1 = new TableColumn<>("Modulname");
		TableColumn<Modul, Integer> semester1 = new TableColumn<>("Semester");
		TableColumn<Modul, Integer> credits1 = new TableColumn<>("Credits");
		TableColumn<Modul, Date> versucheNB = new TableColumn<>("Versuche");
		nichtBestandeneKlausuren.getColumns().addAll(modulname1, semester1, credits1, versucheNB);
		// Properties von nichtBestandeneKlausuren
		nichtBestandeneKlausuren.setFocusTraversable(false);
		GridPane.setValignment(nichtBestandenTx, VPos.CENTER);
		GridPane.setHalignment(nichtBestandenTx, HPos.CENTER);
		GridPane.setVgrow(nichtBestandeneKlausuren, Priority.SOMETIMES);
		nichtBestandeneKlausuren.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		bestandeneKlausuren.setItems(FXCollections.observableArrayList(benutzer.getStudiengang().getBModule()));
		nichtBestandeneKlausuren.setItems(FXCollections.observableArrayList(benutzer.getStudiengang().getNModule()));

		// GridPane.setMargin(bestandenTx, new Insets(0, 60, 0, 0));
		// GridPane.setMargin(bestandeneKlausuren, new Insets(0, 60, 0, 0));

		modulname.setCellValueFactory(new PropertyValueFactory<>("name"));
		semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
		credits.setCellValueFactory(new PropertyValueFactory<>("credits"));
		modulname1.setCellValueFactory(new PropertyValueFactory<>("name"));
		semester1.setCellValueFactory(new PropertyValueFactory<>("semester"));
		credits1.setCellValueFactory(new PropertyValueFactory<>("credits"));
		note.setCellValueFactory(new PropertyValueFactory<>("note"));
		ablaufdatum.setCellValueFactory(new PropertyValueFactory<>("ablaufdatum"));
		ablaufdatum.setCellFactory((TableColumn<Modul, Datum> column) -> {
			return new TableCell<Modul, Datum>() {
				@Override
				protected void updateItem(Datum item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.toString());
					}
				}
			};
		});
		verbesserungspotenzial.setCellValueFactory(new PropertyValueFactory<>("verbesserungspotenzial"));
		verbesserungspotenzial.setCellFactory((TableColumn<Modul, Float> column) -> {
			return new TableCell<Modul, Float>() {
				@Override
				protected void updateItem(Float item, boolean empty) {
					super.updateItem(item, empty);
					if (!empty) {
						BigDecimal bd = new BigDecimal(item);
						BigDecimal res = bd.setScale(2, RoundingMode.HALF_UP);
						setText(Float.toString(res.floatValue()));
					}

				}
			};
		});
		/*
		 * verbesserungspotenzial.setCellFactory((TableColumn<Modul, Modul>
		 * column) -> { return new TableCell<Modul, Modul>() {
		 * 
		 * @Override protected void updateItem(Modul item, boolean empty) {
		 * super.updateItem(item, empty); // ---------> Methode fuer
		 * verbesserungsversuch if (item == null || empty) { setText(null); }
		 * else { setText(""+ item.getModulnummer()); } } }; });
		 */
		// GridPane.setMargin(bestandeneKlausuren, new Insets(0, 60, 0, 0));
		versucheNB.setCellValueFactory(new PropertyValueFactory<>("versuche"));
		// Location
		// bestandenTx.setOnMouseClicked(event -> {
		// bestandeneKlausuren.refresh();
		// });
		bestandeneKlausuren.widthProperty().addListener(event -> {
			bestandeneKlausuren.refresh();
		});
		this.setPadding(new Insets(20, 80, 20, 20));
		this.setVgap(10);
		this.add(bestandenTx, 0, 0);
		this.add(bestandeneKlausuren, 0, 1);
		this.add(durchschnittTx, 0, 2);
		this.add(verbesserungsversucheTx, 0, 3);
		this.add(nichtBestandenTx, 0, 4);
		this.add(nichtBestandeneKlausuren, 0, 5);

		// ------------------------------------------------------------------------------------------
		// this.setGridLinesVisible(true);
	}
}
