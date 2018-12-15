package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.Hauptpane;

public class Main extends Application{
		public static void main(String[] args) {
			launch(args);
		}

		@Override
		public void start(Stage primaryStage) throws Exception {
			Hauptpane pane = new Hauptpane(getHostServices());
			Scene scene = new Scene(pane);
			scene.getStylesheets().add("/css/stylesheet.css");
//			scene.getStylesheets().add(getClass().getResource("/SWE-C7/resources/css/stylesheet.css").toExternalForm());

			primaryStage.setTitle("QIS");
			primaryStage.setScene(scene);
			primaryStage.setMinHeight(600);
			primaryStage.setHeight(600);
			primaryStage.setMinWidth(800);
			primaryStage.setWidth(800);
			primaryStage.show();
			
		}

}
