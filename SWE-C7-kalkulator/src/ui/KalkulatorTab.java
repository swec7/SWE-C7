package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.util.Callback;

import static java.lang.Math.round;
import static java.lang.Math.toIntExact;

public class KalkulatorTab extends QisTab {

    /*
    //DEBUG ----------------------------------------------------------
    //Testdaten follows
    Modul m1 = new Modul(51101, "Hoehere Mathematik 1", 8, 3.0f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1, Typ.PFLICHT, 0);
    Modul m2 = new Modul(51104, "Grundlagen der Informatik und hoehere Programmiersprache fuer Informatik", 11, 1.3f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1, Typ.PFLICHT, 0);
    Modul m3 = new Modul(55667, "Kommunikationstechnicken", 2, 0.0f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 2, Typ.SOFTSKILL, 0);
    Modul m4 = new Modul(55668, "Wissenschaftliches Arbeiten", 2, 0.0f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1, Typ.SOFTSKILL, 0);
    Modul m5 = new Modul(52105, "Technische Informatik", 2, 1.3f, 2, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 1, Typ.PFLICHT, 0);
    Modul m6 = new Modul(55667, "Algorithmen und Datenstrukturen", 8, 1.0f, 1, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1), 2, Typ.PFLICHT, 0);
    //END DEBUG ------------------------------------------------------

    ArrayList<Modul> daten = new ArrayList<>(Arrays.asList(m1,m2,m3,m4,m5,m6));
    //*/

    public KalkulatorTab(Benutzer benutzer){

        /*
        ArrayList<Modul> daten = new ArrayList<>();
        for (int i = 0; i < benutzer.getStudiengang().getModuleSize(); i++)
        {
            daten.add(benutzer.getStudiengang().getModul(i));
        }
        //*/
        ArrayList<Modul> daten = (ArrayList<Modul>) benutzer.getStudiengang().getModule();

        //Output Texte des Tabs
        Text aktDurchschnittTx = new Text("Aktuelle Durchschnittsnote:\t"+benutzer.durchschnittsNote());
        Text errechDurchschnittTx = new Text("Errechnete Durchschnittsnote:\t");
        Text verblVersuche = new Text("Verbleibende Verbesserungsversuche:\t" + benutzer.getVersuche());
        Text wunschnoteRechTx = new Text("Wunschnotenenrechner");
        Text wunschnoteTx = new Text("Wunschnote");
        Text wunschnoteNichtBerTx = new Text("deine Wunschnote ist nicht ohne Verbesserungsversuche erreichbar");
        TextField wunschnoteTf = new TextField();
        //CSS Styles der Texte
        aktDurchschnittTx.getStyleClass().add("tabtext");
        errechDurchschnittTx.getStyleClass().add("tabtext");
        verblVersuche.getStyleClass().add("tabtext");
        wunschnoteRechTx.getStyleClass().addAll("zueberschrift");
        wunschnoteTx.getStyleClass().add("tabtext");
        //Properties der Texte
        GridPane.setHalignment(wunschnoteNichtBerTx, HPos.CENTER);

        //Tabelle
        TableView<Modul> kalkulator = new TableView<>();
        //CSS Styles der Tabelle
        kalkulator.getStyleClass().add("grey");
        //Colums der Tabelle
        TableColumn<Modul, String> modulname = new TableColumn<>("Modulname");
        TableColumn<Modul, Integer> credits = new TableColumn<>("Credits");
        TableColumn<Modul, Float> note = new TableColumn<>("Note");
        TableColumn<Modul, Float> verbesserung = new TableColumn<>("Verbesserung");
        kalkulator.getColumns().addAll(modulname,credits,note,verbesserung);
        //Properties der Tabelle
        GridPane.setMargin(kalkulator, new Insets(0, 60, 0, 0));
        GridPane.setHgrow(kalkulator, Priority.ALWAYS);
        GridPane.setVgrow(kalkulator, Priority.ALWAYS);
        kalkulator.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        kalkulator.setEditable(true);

        modulname.setCellValueFactory(new PropertyValueFactory<>("name"));
        credits.setCellValueFactory(new PropertyValueFactory<>("credits"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));
        verbesserung.setCellValueFactory(new PropertyValueFactory<>("verbesserung"));
        note.setCellFactory((TableColumn<Modul, Float> param) -> new EditingCell());
        note.setOnEditCommit((TableColumn.CellEditEvent<Modul,Float> e)->  (e.getTableView().getItems().get(e.getTablePosition().getRow())).setPlanNote(e.getNewValue()));
        kalkulator.setItems(FXCollections.observableArrayList(benutzer.getStudiengang().getModule()));


        Button reset = new Button("Reset");
        reset.setOnAction(e->{
            //TODO Daten aus TableView-> Plannote auf note setzen
            //Update items in TableView
            //Text aktualisieren

            for (int c = 0; c < benutzer.getStudiengang().getModule().size(); c++){
                benutzer.getStudiengang().getModule().get(c).setPlanNote(0);
            }
            aktDurchschnittTx.setText("Aktuelle Durchschnittsnote:\t" + benutzer.durchschnittsNote());
            verblVersuche.setText("Verbleibende Verbesserungsversuche:\t" + benutzer.getVersuche());
            errechDurchschnittTx.setText("Errechnete Durchschnittsnote:\t");
            kalkulator.setItems(FXCollections.observableArrayList(benutzer.getStudiengang().getModule()));
            System.out.println(benutzer.toString());
        });

        HBox wunschnoteBox = new HBox();
        wunschnoteBox.getStyleClass().add("border-top");
        wunschnoteBox.setAlignment(Pos.CENTER);
        wunschnoteBox.getChildren().add(wunschnoteRechTx);
        Button wunschnoteBer = new Button("OK");
        wunschnoteBer.setOnAction(e->{
            //TODO Berechnungsmethode Hier

            int credit = 0;
            int fixedcredits = 0;
            float scalar = 0;

            ArrayList<Modul> geschrieben = new ArrayList<>();
            ArrayList<Modul> zuschreiben = new ArrayList<>();
            for (int c = 0; c < daten.size(); c++){
                credit += daten.get(c).getCredits();
                if (daten.get(c).isGeschrieben() && daten.get(c).getNote() < 5){
                    geschrieben.add(daten.get(c));
                }
                else{
                    zuschreiben.add(daten.get(c));
                }
            }
            float wunschnote = Float.parseFloat(wunschnoteTf.getCharacters().toString());
            
            benutzer.setWunschNote(wunschnote);

            ArrayList<Modul> fixed = new ArrayList<>();
            for (int c = 0; c < geschrieben.size(); c++){
                //hier abfrage für zu verbessernde
                fixed.add(geschrieben.get(c));
                fixedcredits += geschrieben.get(c).getCredits();
                scalar += geschrieben.get(c).getCredits() * geschrieben.get(c).getNote();
            }

            float opencredit = credit - fixedcredits;
            float restnote = round_note_up((wunschnote  * credit - scalar)/opencredit);

            for (int c = 0; c < benutzer.getStudiengang().getModule().size(); c++){
                if (!fixed.contains(benutzer.getStudiengang().getModul(c)) || benutzer.getStudiengang().getModul(c).getNote() == 5 || benutzer.getStudiengang().getModul(c).getNote() == 0){
                    benutzer.getStudiengang().getModul(c).setPlanNote(restnote);
                }
                else{
                    benutzer.getStudiengang().getModul(c).setPlanNote(benutzer.getStudiengang().getModul(c).getNote());
                }
            }
            for (int c = 0; benutzer.PlanSchnitt() > wunschnote; c++){
                if (zuschreiben.contains(benutzer.getStudiengang().getModul(c % benutzer.getStudiengang().getModule().size()))){
                    benutzer.getStudiengang().getModul(c % benutzer.getStudiengang().getModule().size()).setPlanNote(round_note_down(benutzer.getStudiengang().getModul(c % benutzer.getStudiengang().getModule().size()).getPlanNote() - (float) 0.01));
                }
            }

            aktDurchschnittTx.setText("Aktuelle Durchschnittsnote:\t" + benutzer.durchschnittsNote());
            verblVersuche.setText("Verbleibende Verbesserungsversuche:\t" + benutzer.getVersuche());
            errechDurchschnittTx.setText("Errechnete Durchschnittsnote:\t" + benutzer.PlanSchnitt());
            kalkulator.setItems(FXCollections.observableArrayList(benutzer.getStudiengang().getModule()));
            System.out.println(benutzer.toString());
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


//		ColumnConstraints first = new ColumnConstraints();
//		first.setHgrow(Priority.NEVER);
//		ColumnConstraints second = new ColumnConstraints();
//		second.setHgrow(Priority.NEVER);
//		ColumnConstraints third = new ColumnConstraints();
//		third.setHgrow(Priority.NEVER);
//		ColumnConstraints fourth = new ColumnConstraints();
//		fourth.setHgrow(Priority.ALWAYS);

//		this.getColumnConstraints().addAll(first,second,third,fourth);
//
//		this.setGridLinesVisible(true);
//
//		GridPane.setHgrow(wunschnoteNichtBerTx, Priority.ALWAYS);

    }
    private float round_note_down(float Note){
        float ganzenote = (long) Note;
        if (Note%1 < 0.3)
            Note = ganzenote;
        else if ( Note%1 < 0.7)
            Note = ganzenote + (float) 0.3;
        else
            Note = ganzenote + (float) 0.7;
        if (Note >5.0)
            Note = (float) 5.0;
        return Note;
    }

    private float round_note_up(float Note){
        float ganzenote = (long) Note;
        if (Note%1 < 0.3)
            Note = ganzenote + (float) 0.3;
        else if ( Note%1 < 0.7)
            Note = ganzenote + (float) 0.7;
        else
            Note = ganzenote + (float) 1;
        if (Note < 1)
            Note = (float) 1.0;
        return Note;
    }

    class EditingCell extends TableCell<Modul, Float> {
        private TextField textField;

        private EditingCell() {}

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
            setText( getItem().toString());
            setGraphic(null);
        }

        @Override
        public void updateItem(Float item, boolean empty) {
            super.updateItem(item, empty);
            if (empty|| item.equals(0.0f)) {
//	                setText(item.toString());
                setText("");
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
//	                        setGraphic(null);
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
            textField.setOnAction((e) -> commitEdit(Float.parseFloat(textField.getText())));
            textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
//	                    System.out.println("Commiting " + textField.getText());
                    commitEdit(Float.parseFloat(textField.getText()));
                }
            });
        }
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}
