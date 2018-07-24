package GuiWindows;

import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameStatistics {
	
	
	static Stage window;

	static Scene scene;

	static WebView wv;
	
	public static void Display() {
		
		 
		
		window =new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Battle Ships");
	

		
		    
		    try {
		    	 wv = new WebView();
		    	WebEngine we = wv.getEngine();
		    	File f = new File("GameStatistics.html");
		    	wv.getEngine().load(f.toURI().toURL().toString());
		    } catch (MalformedURLException ex) {
		    	
		    }

		scene = new Scene(wv);
		scene.getStylesheets().add("SavedGames.css");
		window.setScene(scene);
		
		window.showAndWait();
		
		
		return;
		
		
	}
	
	
	
	

}
