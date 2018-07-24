package GuiWindows;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import JsonHandling.PlayerInfoHandler;
import Saving_loading.GameState;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SavedGames {
	
	static Stage window;

	static Scene scene;
	static String Name;

	
	public static String Display() {
		
		 
		
		window =new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Battle Ships");
		


			VBox box= new VBox(); 
			box.setAlignment(Pos.CENTER);
		
			box.setSpacing(25);

		   Label lblName = new Label("Enter your Name:");
		   TextField tfName = new TextField();

		   Button ok = new Button("Ok");
		   
		   ok.setOnAction(e->{
			   Name=tfName.getText();
			
			   
			 
				   window.close();
			
			   
		   });
		
		    
		
		   box.getChildren().addAll(lblName,tfName,ok);
		  
		scene = new Scene(box);
		scene.getStylesheets().add("SavedGames.css");
		window.setScene(scene);
		
		window.showAndWait();
		
		
		return Name;
		
		
	}
	

	
	


}
