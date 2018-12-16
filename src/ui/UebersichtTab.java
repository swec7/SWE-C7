package ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import daten.Modul;
import daten.Typ;
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

public class UebersichtTab extends QisTab{
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private float notenspiegel = 1.6f;
	
	
	public UebersichtTab(){
		this.setPadding(new Insets(20, 20, 60, 20));
		
		TableView<Modul> uebersicht = new TableView<>();   
		
		GridPane.setMargin(uebersicht, new Insets(0, 60, 0, 0)); 
		
		TableColumn<Modul, String> modulname = new TableColumn<Modul, String>("Modulname");
		TableColumn<Modul, Integer> semester = new TableColumn<Modul, Integer>("Semester");
		TableColumn<Modul, Integer> credits = new TableColumn<Modul, Integer>("Credits");
		TableColumn<Modul, Float> note = new TableColumn<Modul, Float>("Note");
		TableColumn<Modul, Date> ablaufdatum = new TableColumn<Modul, Date>("Ablaufdatum");
		TableColumn<Modul, Integer> versuch = new TableColumn<Modul, Integer>("Versuch");
		
		uebersicht.getColumns().addAll(modulname, semester, credits, note, ablaufdatum, versuch);
		
		modulname.setCellValueFactory(new PropertyValueFactory<>("name"));
		semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
		credits.setCellValueFactory(new PropertyValueFactory<>("credits"));
		note.setCellValueFactory(new PropertyValueFactory<>("note"));
		note.setCellFactory((TableColumn<Modul, Float> column) -> {
			   return new TableCell<Modul, Float>() {
			      @Override
			      protected void updateItem(Float item, boolean empty) {
			         super.updateItem(item, empty);
			         if (item == null || item == 0 || empty) {
			            setText(null);
			         } else {
			            setText(item.toString());
//			            if(item == 5.0f){
//			            	getTableRow().getStyleClass().setAll("rowRed");
//			            }else if(item>=1.0f){
//			            	getTableRow().getStyleClass().setAll("rowGreen");
//			            }
			        }
			     }
			   };
			});
		ablaufdatum.setCellValueFactory(new PropertyValueFactory<>("ablaufdatum"));
		ablaufdatum.setCellFactory((TableColumn<Modul, Date> column) -> {
			   return new TableCell<Modul, Date>() {
			      @Override
			      protected void updateItem(Date item, boolean empty) {
			         super.updateItem(item, empty);
			         if (item == null || empty) {
			            setText(null);
			         } else {
			            setText(df.format(item));
			        }
			     }
			   };
			});
		versuch.setCellValueFactory(new PropertyValueFactory<>("versuche"));
		versuch.setCellFactory((TableColumn<Modul, Integer> column) -> {
			   return new TableCell<Modul, Integer>() {
			      @Override
			      protected void updateItem(Integer item, boolean empty) {
			         super.updateItem(item, empty);
			         if (item == null || item == 0 || empty) {
			            setText(null);
//			            getTableRow().getStyleClass().setAll("rowGrey");
			         } else {
			            setText(item.toString());
			        }
			     }
			   };
			});
		
		semester.setSortType(TableColumn.SortType.ASCENDING);
		
		uebersicht.setRowFactory(row -> new TableRow<Modul>(){
			 @Override
			    public void updateItem(Modul item, boolean empty){
			        super.updateItem(item, empty);

			        if (item == null || empty) {
			        	getStyleClass().add("rowGrey");
			        }else if(item.getNote()==0){
			        	getStyleClass().add("rowGrey");
			        }else if(item.getNote()==5.0f){
			        	getStyleClass().add("rowRed");
			        }else{
			        	getStyleClass().add("rowGreen");
			        }
			    }
			
			
		});
		
		
//		Testdaten 	------------------------------------------------------------------------------
		Modul bestanden = new Modul(1, "Gip", 8, 1.6f, 2, new Date(2019, 1, 1), new Date(2018, 1, 1),1,Typ.PFLICHT, 0);
		Modul durchgefallen = new Modul(2, "dnis", 8, 5.0f, 1, null, new Date(2018, 1, 1),2,Typ.PFLICHT, 0);
		Modul offen = new Modul(3, "TI", 8, 0, 0, null, null,1,Typ.PFLICHT, 0);
		
		uebersicht.setItems( FXCollections.observableArrayList(bestanden, durchgefallen, offen));
//		------------------------------------------------------------------------------------------
		GridPane.setHgrow(uebersicht, Priority.ALWAYS);
		GridPane.setVgrow(uebersicht, Priority.ALWAYS);
		this.add(uebersicht, 0, 0);
		
		
		Text schnitt = new Text("Aktuelle Durchschnittsnote:\t"+notenspiegel);
		schnitt.getStyleClass().add("tabtext");
		
		this.add(schnitt, 0, 1);
		
		
		
		
//		this.setGridLinesVisible(true);
		
	}
	
}
