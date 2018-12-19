package ui;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import daten.Modul;
import daten.Typ;
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

public class KlausurrenTab extends QisTab {
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	Text bestandenTx = new Text("Bestandene Klausuren");
	public KlausurrenTab(){
		
		this.setPadding(new Insets(20, 80, 20, 20));
		this.setVgap(10);
		
		Text bestandenTx = new Text("Bestandene Klausuren");
		GridPane.setValignment(bestandenTx, VPos.CENTER);
		GridPane.setHalignment(bestandenTx, HPos.CENTER);
		bestandenTx.getStyleClass().add("zueberschrift");
		
//		GridPane.setMargin(bestandenTx, new Insets(0, 60, 0, 0));
		
		this.add(bestandenTx, 0, 0);
		
		TableView<Modul> bestandeneKlausuren = new TableView<Modul>();
		bestandeneKlausuren.getStyleClass().add("grey");
		bestandeneKlausuren.setFocusTraversable(false);
//		GridPane.setMargin(bestandeneKlausuren, new Insets(0, 60, 0, 0));
		
		TableColumn<Modul, String> modulname = new TableColumn<Modul, String>("Modulname");
		TableColumn<Modul, Integer> semester = new TableColumn<Modul, Integer>("Semester");
		TableColumn<Modul, Integer> credits = new TableColumn<Modul, Integer>("Credits");
		TableColumn<Modul, Float> note = new TableColumn<Modul, Float>("Note");
		TableColumn<Modul, Float> verbesserungspotenzial = new TableColumn<Modul, Float>("Verbesserungspotenzial");
		TableColumn<Modul, LocalDate> ablaufdatum = new TableColumn<Modul, LocalDate>("Ablaufdatum");
		
		bestandeneKlausuren.getColumns().addAll(modulname, semester, credits, note, verbesserungspotenzial, ablaufdatum);
		
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
		this.add(bestandeneKlausuren, 0, 1);
		GridPane.setHgrow(bestandeneKlausuren, Priority.ALWAYS);
		GridPane.setVgrow(bestandeneKlausuren, Priority.SOMETIMES);
		String durchschnittsnote = "1.6";
		Text durchschnittTx = new Text("Aktuelle durchschnittsnote:\t"+durchschnittsnote);
		durchschnittTx.getStyleClass().add("tabtext");
		int versuche = 2;
		Text verbesserungsversucheTx = new Text("Verbleibende Verbesserungsversuche:\t" + versuche);
		verbesserungsversucheTx.getStyleClass().add("tabtext");
		this.add(durchschnittTx, 0, 2);
		this.add(verbesserungsversucheTx, 0, 3);
		
		Text nichtBestandenTx = new Text("Bestandene Klausuren");
		GridPane.setValignment(nichtBestandenTx, VPos.CENTER);
		GridPane.setHalignment(nichtBestandenTx, HPos.CENTER);
		nichtBestandenTx.getStyleClass().add("zueberschrift");
		this.add(nichtBestandenTx, 0, 4);
		
		TableView<Modul> nichtBestandeneKlausuren = new TableView<Modul>();
		nichtBestandeneKlausuren.setFocusTraversable(false);
		nichtBestandeneKlausuren.getStyleClass().add("grey");
		GridPane.setVgrow(nichtBestandeneKlausuren, Priority.SOMETIMES);
//		GridPane.setMargin(bestandeneKlausuren, new Insets(0, 60, 0, 0));
		

		TableColumn<Modul, Date> versucheNB = new TableColumn<Modul, Date>("Versuche");
		
		nichtBestandeneKlausuren.getColumns().addAll(modulname, semester, credits, versucheNB);
		
		versucheNB.setCellValueFactory(new PropertyValueFactory<>("versuche"));
		
		this.add(nichtBestandeneKlausuren, 0, 5);
		
//		Testdaten 	------------------------------------------------------------------------------
		Modul bestanden = new Modul(1, "Gip", 8, 1.6f, 2, LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1),1,Typ.PFLICHT, 0);
		Modul durchgefallen = new Modul(2, "dnis", 8, 5.0f, 1, null,  LocalDate.of(2018, 1, 1),2,Typ.PFLICHT, 0);
		
		bestandeneKlausuren.setItems( FXCollections.observableArrayList(bestanden));
		nichtBestandeneKlausuren.setItems( FXCollections.observableArrayList(durchgefallen));
		
//		------------------------------------------------------------------------------------------
		
		
//		this.setGridLinesVisible(true);
	}
	
	
}
