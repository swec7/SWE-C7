package ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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

public class KalkulatorTab extends QisTab {
	
	//	Testdaten 	------------------------------------------------------------------------------
	Modul bestanden = new Modul(1, "Gip", 8, 1.6f, 2, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1),1,Typ.PFLICHT, 0);
	Modul durchgefallen = new Modul(2, "dnis", 8, 5.0f, 1, null, LocalDate.of(2018, 1, 1),2,Typ.PFLICHT, 0);
	Modul offen = new Modul(3, "TI", 8, 0, 0, null, null,1,Typ.PFLICHT, 0);
	//	------------------------------------------------------------------------------------------

	ArrayList<Modul> daten = new ArrayList<Modul>(Arrays.asList(bestanden,durchgefallen,offen));

	public KalkulatorTab(Benutzer benutzer){
		
		TableView<Modul> kalkulator = new TableView<Modul>();
		GridPane.setMargin(kalkulator, new Insets(0, 60, 0, 0));
		GridPane.setHgrow(kalkulator, Priority.ALWAYS);
		GridPane.setVgrow(kalkulator, Priority.ALWAYS);
		kalkulator.setEditable(true);
		kalkulator.getStyleClass().add("grey");
		
		TableColumn<Modul, String> modulname = new TableColumn<Modul, String>("Modulname");
		TableColumn<Modul, Integer> credits = new TableColumn<Modul, Integer>("Credits");
		TableColumn<Modul, Float> note = new TableColumn<Modul, Float>("Note");
		
		kalkulator.getColumns().addAll(modulname,credits,note);
		
		modulname.setCellValueFactory(new PropertyValueFactory<>("name"));
		credits.setCellValueFactory(new PropertyValueFactory<>("credits"));
		note.setCellValueFactory(new PropertyValueFactory<Modul, Float>("note"));
		note.setCellFactory((TableColumn<Modul, Float> param) -> new EditingCell());
		note.setOnEditCommit((TableColumn.CellEditEvent<Modul,Float> e)-> {
			((Modul) e.getTableView().getItems().get(e.getTablePosition().getRow())).setPlanNote(e.getNewValue());
		});
		kalkulator.setItems(FXCollections.observableArrayList(daten));
		
		
		Text aktDurchschnittTx = new Text("Aktuelle Durchschnittsnote:\t");
		aktDurchschnittTx.getStyleClass().add("tabtext");
		Text errechDurchschnittTx = new Text("Errechnete Durchschnitsnote:\t");
		errechDurchschnittTx.getStyleClass().add("tabtext");
		Text verblVersuche = new Text("Verbleibende Verbesserungsversuche:\t");
		verblVersuche.getStyleClass().add("tabtext");
		
		Button reset = new Button("Reset");
		reset.setOnAction(e->{
			//Daten aus TableView-> Plannote auf note setzen
			//Update items in TableView
			//Durchschnitt neu berechnen 
			//Text aktualisieren
		});
		
		HBox wunschnoteBox = new HBox();
		wunschnoteBox.getStyleClass().add("border-top");
		wunschnoteBox.setAlignment(Pos.CENTER);
		Text wunschnoteRechTx = new Text("Wunschnotenenrechner");
		wunschnoteRechTx.getStyleClass().addAll("zueberschrift");
		wunschnoteBox.getChildren().add(wunschnoteRechTx);
		
		
		Text wunschnoteTx = new Text("Wunschnote");
		wunschnoteTx.getStyleClass().add("tabtext");
		
		TextField wunschnoteTf = new TextField();
		
		Text wunschnoteNichtBerTx = new Text("deine Wunschnote ist nicht ohne Verbesserungsversuch erreichbar");
		GridPane.setHalignment(wunschnoteNichtBerTx, HPos.CENTER);
		
		Button wunschnoteBer = new Button("OK");
		wunschnoteBer.setOnAction(e->{
			//Berschnungsmethode Hier
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
//		
//		
//		
//		this.getColumnConstraints().addAll(first,second,third,fourth);
		
		
//		this.setGridLinesVisible(true);
//		
//		GridPane.setHgrow(wunschnoteNichtBerTx, Priority.ALWAYS);
		
		
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
