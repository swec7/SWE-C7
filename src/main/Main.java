package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.Hauptpane;

public class Main extends Application{
		public static void main(String[] args) {
			launch(args); 	// führt start(Stage primaryStage) aus
		}

		/**
		 * @param Stage 
		 */
		
		@Override
		public void start(Stage primaryStage) throws Exception {
			/*
			 * HostServices (Abrufbar von Application) ermöglicht öffnen des Browsers in start tab
			 */
			Hauptpane pane = new Hauptpane(getHostServices());
			Scene scene = new Scene(pane);
			scene.getStylesheets().add("/css/stylesheet.css");

			primaryStage.setTitle("QIS Klausurplaner");
			primaryStage.setScene(scene);
			primaryStage.setMinHeight(600);
			primaryStage.setHeight(600);
			primaryStage.setMinWidth(800);
			primaryStage.setWidth(800);
			primaryStage.show();
			
		}

}
