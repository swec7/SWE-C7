package ui;

import static java.lang.Math.round;

import java.util.ArrayList;

import daten.Benutzer;
import daten.Modul;
import daten.Typ;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class KalkulatorTab extends QisTab {

	/*
	 * //DEBUG ----------------------------------------------------------
	 * //Testdaten follows Modul m1 = new Modul(51101, "Hoehere Mathematik 1",
	 * 8, 3.0f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1,
	 * Typ.PFLICHT, 0); Modul m2 = new Modul(51104,
	 * "Grundlagen der Informatik und hoehere Programmiersprache fuer Informatik"
	 * , 11, 1.3f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1,
	 * Typ.PFLICHT, 0); Modul m3 = new Modul(55667, "Kommunikationstechnicken",
	 * 2, 0.0f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 2,
	 * Typ.SOFTSKILL, 0); Modul m4 = new Modul(55668,
	 * "Wissenschaftliches Arbeiten", 2, 0.0f, 1, LocalDate.of(2019, 1, 1),
	 * LocalDate.of(2018, 1, 1), 1, Typ.SOFTSKILL, 0); Modul m5 = new
	 * Modul(52105, "Technische Informatik", 2, 1.3f, 2, LocalDate.of(2019, 1,
	 * 1), LocalDate.of(2018, 1, 1), 1, Typ.PFLICHT, 0); Modul m6 = new
	 * Modul(55667, "Algorithmen und Datenstrukturen", 8, 1.0f, 1,
	 * LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 2, Typ.PFLICHT, 0);
	 * //END DEBUG ------------------------------------------------------
	 * 
	 * ArrayList<Modul> daten = new
	 * ArrayList<>(Arrays.asList(m1,m2,m3,m4,m5,m6)); //
	 */

	public KalkulatorTab(Benutzer benutzer) {

		/*
		 * ArrayList<Modul> daten = new ArrayList<>(); for (int i = 0; i <
		 * benutzer.getStudiengang().getModuleSize(); i++) {
		 * daten.add(benutzer.getStudiengang().getModul(i)); } //
		 */
		ArrayList<Modul> daten = (ArrayList<Modul>) benutzer.getStudiengang().getModule();
		Benutzer backup = benutzer;

		// Output Texte des Tabs
		Text aktDurchschnittTx = new Text(
				"Aktuelle Durchschnittsnote:\t" + String.format("%.1f", benutzer.durchschnittsNote()));
		Text errechDurchschnittTx = new Text("Errechnete Durchschnittsnote:\t");
		Text verblVersuche = new Text("Verbleibende Verbesserungsversuche:\t" + round(benutzer.getVersuche()));
		Text wunschnoteRechTx = new Text("Wunschnotenenrechner");
		Text wunschnoteTx = new Text("Wunschnote");
		Text wunschnoteNichtBerTx = new Text("Deine Wunschnote ist nicht ohne Verbesserungsversuche erreichbar");
		TextField wunschnoteTf = new TextField();
		// CSS Styles der Texte
		aktDurchschnittTx.getStyleClass().add("tabtext");
		errechDurchschnittTx.getStyleClass().add("tabtext");
		verblVersuche.getStyleClass().add("tabtext");
		wunschnoteRechTx.getStyleClass().addAll("zueberschrift");
		wunschnoteTx.getStyleClass().add("tabtext");
		// Properties der Texte
		GridPane.setHalignment(wunschnoteNichtBerTx, HPos.CENTER);

		// Tabelle
		TableView<Modul> kalkulator = new TableView<>();
		// CSS Styles der Tabelle
		kalkulator.getStyleClass().add("grey");
		// Colums der Tabelle
		TableColumn<Modul, String> modulname = new TableColumn<>("Modulname");
		TableColumn<Modul, Integer> credits = new TableColumn<>("Credits");
		TableColumn<Modul, Float> note = new TableColumn<>("Note");
		kalkulator.getColumns().addAll(modulname, credits, note);
		// Properties der Tabelle
		GridPane.setMargin(kalkulator, new Insets(0, 60, 0, 0));
		GridPane.setHgrow(kalkulator, Priority.ALWAYS);
		GridPane.setVgrow(kalkulator, Priority.ALWAYS);
		kalkulator.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		kalkulator.setEditable(true);

		modulname.setCellValueFactory(new PropertyValueFactory<>("name"));
		credits.setCellValueFactory(new PropertyValueFactory<>("credits"));
		note.setCellValueFactory(new PropertyValueFactory<>("note"));
		note.setCellFactory((TableColumn<Modul, Float> param) -> new EditingCell());
		/*
		 * note.setOnEditCommit((TableColumn.CellEditEvent<Modul,Float> e)-> (
		 * e.getTableView().getItems().get(e.getTablePosition().getRow())).
		 * setPlanNote(e.getNewValue()) );
		 * note.setOnEditCommit((TableColumn.CellEditEvent<Modul,Float> e)-> (
		 * benutzer.getStudiengang().getModul(e.getTablePosition().getRow())).
		 * setPlanNote(e.getNewValue()) );
		 */
		// *
		note.setOnEditCommit(e -> {
			e.getRowValue().setPlanNote(round_note(e.getNewValue()));
			// benutzer.getStudiengang().getModul(e.getTablePosition().getRow()).setPlanNote(round_note(e.getNewValue()));

			ArrayList<Modul> wertung = new ArrayList<>();
			for (int c = 0; c < benutzer.getStudiengang().getModuleSize(); c++) {
				if (daten.get(c).isGeschrieben() || benutzer.getStudiengang().getModul(c).getPlanNote() != 0) {
					wertung.add(benutzer.getStudiengang().getModul(c));
				}
			}

			float scalar = 0;
			int partialcredit = 0;
			for (int c = 0; c < wertung.size(); c++) {
				if (wertung.get(c).getPlanNote() == 0 && wertung.get(c).getNote() != 0) {
					wertung.get(c).setPlanNote(wertung.get(c).getNote());
					scalar += wertung.get(c).getCredits() * wertung.get(c).getPlanNote();
					partialcredit += wertung.get(c).getCredits();
				} else if (wertung.get(c).getPlanNote() != 0) {
					scalar += wertung.get(c).getCredits() * wertung.get(c).getPlanNote();
					partialcredit += wertung.get(c).getCredits();
				}
			}
			float schnitt = scalar / partialcredit;

			aktDurchschnittTx
					.setText("Aktuelle Durchschnittsnote:\t" + String.format("%.1f", benutzer.durchschnittsNote()));
			verblVersuche.setText("Verbleibende Verbesserungsversuche:\t" + round(benutzer.getVersuche()));
			errechDurchschnittTx.setText("Errechnete Durchschnittsnote:\t" + String.format("%.1f", schnitt));
			note.setCellValueFactory(new PropertyValueFactory<>("planNote"));
			kalkulator.refresh();
		});// */
		kalkulator.setItems(FXCollections.observableArrayList(benutzer.getStudiengang().getModule()));

		Button reset = new Button("Reset");
		reset.setOnAction(e -> {
			// TODO Daten aus TableView-> Plannote auf note setzen
			// Update items in TableView
			// Text aktualisieren
			benutzer.reset(backup);
			for (int c = 0; c < benutzer.getStudiengang().getModule().size(); c++) {
				benutzer.getStudiengang().getModule().get(c).setPlanNote(0);
			}
			aktDurchschnittTx
					.setText("Aktuelle Durchschnittsnote:\t" + String.format("%.1f", benutzer.durchschnittsNote()));
			verblVersuche.setText("Verbleibende Verbesserungsversuche:\t" + round(benutzer.getVersuche()));
			errechDurchschnittTx.setText("Errechnete Durchschnittsnote:\t");
			note.setCellValueFactory(new PropertyValueFactory<>("note"));
			kalkulator.refresh();
		});

		HBox wunschnoteBox = new HBox();
		wunschnoteBox.getStyleClass().add("border-top");
		wunschnoteBox.setAlignment(Pos.CENTER);
		wunschnoteBox.getChildren().add(wunschnoteRechTx);
		Button wunschnoteBer = new Button("OK");
		wunschnoteBer.setOnAction(e -> {
			// TODO Berechnungsmethode Hier

			float wunschnote = 2.5f;
			boolean wrongFormat = false;
			try {
				wunschnote = Float.parseFloat(wunschnoteTf.getCharacters().toString());

			} catch (NumberFormatException exc) {
				try {
					String s = wunschnoteTf.getCharacters().toString();
					s = s.replaceAll(",", ".");
					wunschnote = Float.parseFloat(s);
				} catch (NumberFormatException ex) {
					wrongFormat = true;
				}
			}
			if (wunschnote > 4.0 || wunschnote < 1.0) {
				wrongFormat = true;
			}
			Benutzer calc = benutzer;
			for (int c = 0; c < calc.getStudiengang().getModuleSize(); c++) {
				calc.getStudiengang().getModul(c).setPlanNote(calc.getStudiengang().getModul(c).getNote());
			}

			int credit = 0;
			float scoreone = 0;
			int scalar = 0;

			int partial = 0;
			for (int c = 0; c < calc.getStudiengang().getModuleSize(); c++) {
				if (calc.getStudiengang().getModul(c).getTyp() == Typ.SOFTSKILL)
					continue;
				scalar += calc.getStudiengang().getModul(c).getCredits()
						* calc.getStudiengang().getModul(c).getPlanNote();
				credit += calc.getStudiengang().getModul(c).getCredits();
				if (calc.getStudiengang().getModul(c).isGeschrieben()
						&& calc.getStudiengang().getModul(c).getNote() < 5) {
					scoreone += calc.getStudiengang().getModul(c).getCredits()
							* calc.getStudiengang().getModul(c).getPlanNote();
				} else {
					scoreone += calc.getStudiengang().getModul(c).getCredits();
					partial += calc.getStudiengang().getModul(c).getCredits();
				}
			}
			scoreone = round_final(scoreone / credit);

			float rawscore = (wunschnote * credit - scalar) / partial;
			float score = round_note_down((wunschnote * credit - scalar) / partial);

			calc.reset(benutzer);
			for (int c = 0; c < calc.getStudiengang().getModuleSize(); c++) {
				calc.getStudiengang().getModul(c).setPlanNote(calc.getStudiengang().getModul(c).getNote());
			}

			credit = 0;
			float scoretwo = 0;
			for (int c = 0; c < calc.getVersuche(); c++) {
				int max = 0;
				for (int i = 0; i < calc.getStudiengang().getModuleSize(); i++) {
					if (calc.getStudiengang().getModul(i).getNote() > calc.getStudiengang().getModul(max).getNote())
						max = i;
				}
				calc.getStudiengang().getModul(max).setPlanNote(1);
			}

			for (int c = 0; c < calc.getStudiengang().getModuleSize(); c++) {
				if (calc.getStudiengang().getModul(c).getTyp() == Typ.SOFTSKILL)
					continue;
				credit += calc.getStudiengang().getModul(c).getCredits();
				if (calc.getStudiengang().getModul(c).isGeschrieben()
						&& calc.getStudiengang().getModul(c).getNote() < 5) {
					scoretwo += calc.getStudiengang().getModul(c).getCredits()
							* calc.getStudiengang().getModul(c).getPlanNote();
				} else {
					scoretwo += calc.getStudiengang().getModul(c).getCredits();
				}
			}

			scoretwo = round_final(scoretwo / credit);
			Boolean without = wunschnote > scoreone;
			Boolean with = wunschnote > scoretwo;
			if (wrongFormat) {
				wunschnoteNichtBerTx.setText("Ungültige Eingabe");
				wunschnoteNichtBerTx.getStyleClass().add("redText");
			} else if (without)
				wunschnoteNichtBerTx.setText("Um deine Wunschnote zu erreichen, musst du einen Notendurchschnitt von "
						+ score
						+ "\nin den ausstehenden Klausuren erreichen. Dazu sind keine Verbesserungsversuche notwendig");
			else if (with)
				wunschnoteNichtBerTx.setText("Um deine Wunschnote zu erreichen, musst du einen Notendurchschnitt von "
						+ score
						+ "\nin den ausstehenden Klausuren erreichen. Dazu sind Verbesserungsversuche notwendig");
			else
				wunschnoteNichtBerTx.setText("Deine Wunschnote ist nicht erreichbar");
			wunschnoteNichtBerTx.setVisible(true);

		});

		this.add(kalkulator, 0, 0, 4, 1);
		this.add(aktDurchschnittTx, 0, 1, 2, 1);
		this.add(errechDurchschnittTx, 0, 2, 2, 1);
		this.add(reset, 2, 1, 1, 2);

		this.add(verblVersuche, 0, 3, 2, 1);

		this.add(wunschnoteBox, 0, 4, 3, 1);
		this.add(wunschnoteTx, 0, 5);
		this.add(wunschnoteTf, 1, 5);
		this.add(wunschnoteBer, 2, 5);
		this.add(wunschnoteNichtBerTx, 0, 6, 3, 1);

		// ColumnConstraints first = new ColumnConstraints();
		// first.setHgrow(Priority.NEVER);
		// ColumnConstraints second = new ColumnConstraints();Eugen Burikov
		// second.setHgrow(Priority.NEVER);
		// ColumnConstraints third = new ColumnConstraints();
		// third.setHgrow(Priority.NEVER);
		// ColumnConstraints fourth = new ColumnConstraints();
		// fourth.setHgrow(Priority.ALWAYS);

		// this.getColumnConstraints().addAll(first,second,third,fourth);
		//
		// this.setGridLinesVisible(true);
		//
		// GridPane.setHgrow(wunschnoteNichtBerTx, Priority.ALWAYS);

	}

	private float round_note(float Note) {
		float ganzenote = Math.round(Note * 10) / 10;
		if (Note - ganzenote < 0.299)
			Note = ganzenote;
		else if (Note % 1 < 0.7)
			Note = ganzenote + (float) 0.3;
		else
			Note = ganzenote + (float) 0.7;
		if (Note < 1)
			Note = (float) 1.0;
		if (Note > 4)
			return (float) 5.0;
		return Note;
	}

	public float round_final(float note) {
		float score = Math.round(note * 10) / 10;

		if (score < 1)
			return 1;
		if (score > 4 && score < 5)
			return 4;
		if (score > 5)
			return 5;
		return score;
	}

	private float round_note_down(float Note) {
		float ganzenote = (long) Note;
		if (Note % 1 < 0.299)
			Note = ganzenote;
		else if (Note % 1 < 0.7)
			Note = ganzenote + (float) 0.3;
		else
			Note = ganzenote + (float) 0.7;
		if (Note < 1)
			Note = (float) 1.0;
		if (Note >= 4)
			return (float) 4.0;
		return Note;
	}

	private float round_note_up(float Note) {
		float ganzenote = (long) Note;
		if (Note % 1 < 0.299)
			Note = ganzenote + (float) 0.3;
		else if (Note % 1 < 0.7)
			Note = ganzenote + (float) 0.7;
		else
			Note = ganzenote + 1;
		if (Note >= 5)
			Note = 5f;
		return Note;
	}

	class EditingCell extends TableCell<Modul, Float> {
		private TextField textField;

		private EditingCell() {
		}

		@Override
		public void startEdit() {
			if (!isEmpty()) {
				super.startEdit();
				createTextField();
				setText(null);
				setGraphic(textField);
				textField.selectAll();
			}
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();
			setText(getItem().toString());
			setGraphic(null);
		}

		@Override
		public void updateItem(Float item, boolean empty) {
			super.updateItem(item, empty);
			if (empty || item.equals(0.0f)) {
				// setText(item.toString());
				setText("");
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (textField != null) {
						textField.setText(getString());

						// setGraphic(null);
					}

					setText(null);
					setGraphic(textField);
				} else {

					setText(getString());
					setGraphic(null);
				}
			}
		}

		private void createTextField() {
			textField = new TextField(getString());
			textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			textField.setOnAction((e) -> {
				float number = 2.0f;
				try {
					number = Float.parseFloat(textField.getText());
				} catch (NumberFormatException exc) {
					try {
						String s = textField.getText();
						s = s.replaceAll(",", ".");
						number = Float.parseFloat(s);
					} catch (NumberFormatException ex) {
						number = super.getItem().floatValue();
					}
				}
				commitEdit(number);
			});
			textField.focusedProperty().addListener(
					(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
						if (!newValue) {
							// System.out.println("Commiting " +
							// textField.getText());
							float number;
							try {
								number = Float.parseFloat(textField.getText());
							} catch (NumberFormatException exc) {
								try {
									String s = textField.getText();
									s = s.replaceAll(",", ".");
									number = Float.parseFloat(s);
								} catch (NumberFormatException ex) {
									number = super.getItem().floatValue();
								}
							}
							commitEdit(number);
						}
					});
		}

		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}
}
