package it.polito.ferrovie;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DistanzeStazioni extends Application {

	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/gui.fxml")) ;
		Parent view = (Parent)loader.load() ;
		
		Scene main = new Scene(view) ;
		primaryStage.setScene(main) ;
		primaryStage.show() ;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
