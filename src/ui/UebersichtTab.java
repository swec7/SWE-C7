package ui;

import daten.Benutzer;
import daten.Datum;
import daten.Modul;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class UebersichtTab extends QisTab {
	// SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	public UebersichtTab(Benutzer benutzer) {
		// //DEBUG ----------------------------------------------------------
		// //Data from parser follows
		// //float durchschnittsnote = 1.6f;
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

		float durchschnittsnote = benutzer.durchschnitsNote();
		this.setPadding(new Insets(20, 20, 60, 20));

		// Texte des Tabs
		Text schnitt = new Text("Aktuelle Durchschnittsnote:\t" + durchschnittsnote);
		// CSS Styles der Texte
		schnitt.getStyleClass().add("tabtext");

		// Tabelle
		TableView<Modul> uebersicht = new TableView<>();
		// Columns der Tabelle
		TableColumn<Modul, String> modulname = new TableColumn<>("Modulname");
		TableColumn<Modul, Integer> semester = new TableColumn<>("Semester");
		TableColumn<Modul, Integer> credits = new TableColumn<>("Credits");
		TableColumn<Modul, Float> note = new TableColumn<>("Note");
		TableColumn<Modul, Datum> ablaufdatum = new TableColumn<>("Ablaufdatum");
		TableColumn<Modul, Integer> versuch = new TableColumn<>("Versuch");
		uebersicht.getColumns().addAll(modulname, semester, credits, note, ablaufdatum, versuch);
		// Properties der Tabelle
		GridPane.setMargin(uebersicht, new Insets(0, 60, 0, 0));
		// semester.prefWidthProperty().bind(new SimpleIntegerProperty(50));
		// credits.prefWidthProperty().bind(new SimpleIntegerProperty(50));
		// note.prefWidthProperty().bind(new SimpleIntegerProperty(50));
		// ablaufdatum.prefWidthProperty().bind(new SimpleIntegerProperty(50));
		// versuch.prefWidthProperty().bind(new SimpleIntegerProperty(50));
		GridPane.setHgrow(uebersicht, Priority.ALWAYS);
		GridPane.setVgrow(uebersicht, Priority.ALWAYS);
		uebersicht.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		// Tabelle set Items
		uebersicht.setItems(FXCollections.observableList(benutzer.getStudiengang().getModule()));

		modulname.setCellValueFactory(new PropertyValueFactory<>("name"));
		semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
		credits.setCellValueFactory(new PropertyValueFactory<>("credits"));
		note.setCellValueFactory(new PropertyValueFactory<>("note"));
		note.setCellFactory((TableColumn<Modul, Float> column) -> new TableCell<Modul, Float>() {
			@Override
			protected void updateItem(Float item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || item == 0 || empty) {
					setText(null);
				} else {
					setText(item.toString());
					// if(item == 5.0f){
					// getTableRow().getStyleClass().setAll("rowRed");
					// }else if(item>=1.0f){
					// getTableRow().getStyleClass().setAll("rowGreen");
					// }
				}
			}
		});
		ablaufdatum.setCellValueFactory(new PropertyValueFactory<>("ablaufdatum"));
		ablaufdatum.setCellFactory((TableColumn<Modul, Datum> column) -> new TableCell<Modul, Datum>() {
			@Override
			protected void updateItem(Datum item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty) {
					setText(null);
				} else {
					setText(item.toString());
				}
			}
		});
		versuch.setCellValueFactory(new PropertyValueFactory<>("versuche"));
		versuch.setCellFactory((TableColumn<Modul, Integer> column) -> new TableCell<Modul, Integer>() {
			@Override
			protected void updateItem(Integer item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || item == 0 || empty) {
					setText(null);
					// getTableRow().getStyleClass().setAll("rowGrey");
				} else {
					setText(item.toString());
				}
			}
		});

		semester.setSortType(TableColumn.SortType.ASCENDING);

		uebersicht.setRowFactory(row -> new TableRow<Modul>() {
			@Override
			public void updateItem(Modul item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty) {
					getStyleClass().remove("rowGreen");
					getStyleClass().remove("rowRed");
					getStyleClass().add("rowGrey");
				} else if (item.getNote() == 0) {
					getStyleClass().remove("rowGreen");
					getStyleClass().remove("rowRed");
					getStyleClass().add("rowGrey");
				} else if (item.getNote() > durchschnittsnote) {
					getStyleClass().remove("rowGreen");
					getStyleClass().remove("rowGrey");
					getStyleClass().add("rowRed");
				} else {
					getStyleClass().remove("rowGrey");
					getStyleClass().remove("rowRed");
					getStyleClass().add("rowGreen");
				}
			}

		});
		// Datum d = Datum.of(2019, 1, 1);
		uebersicht.getSortOrder().add(semester);

		this.add(uebersicht, 0, 0);
		this.add(schnitt, 0, 1);
		// this.setGridLinesVisible(true);

	}

}
