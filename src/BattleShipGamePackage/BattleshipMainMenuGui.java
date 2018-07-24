/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

import java.io.IOException;
import java.util.ArrayList;

import GuiWindows.SavedGames;
import InfoClasses.PlayerInfo;
import JsonHandling.PlayerInfoHandler;
import Saving_loading.GameLoader;
import Saving_loading.GameSaver;
import Saving_loading.GameState;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BattleshipMainMenuGui extends Application {

    int MaxNumberOfShips = 3;
    String DifficultyMode = "Easy";
    BattleshipPlayer p2, p1;
    Scene scene;
    ComboBox<Integer> Shipsbox;
    ComboBox<Integer> GridSizeBox;
    Button Play,GameStatistics;
    ComboBox<String> Difficulty, CSS;
    CheckBox CheatMode;
    GridPane root;
    
    ComboBox<GameState> Gamelist ;

    boolean Safety = true;

    @Override
    public void start(Stage primaryStage) {
    	
    /*	try {
			GameSaver.ResetFile("lastGameId.bin");
			GameSaver.ResetFile("lastPlayerId.bin");
			GameSaver.ResetFile("Save.txt");
			GameSaver.ResetFile("PlayerInfos.JSON");
			GameSaver.ResetFile("GameInfos.JSON");
			GameSaver.ResetFile("GameSteps.JSON");
			GameSaver.ResetFile("playerinfo.txt");
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}*/
    	
    	
    	

        root = new GridPane();
        root.setVgap(5);
        root.setHgap(130);

        Shipsbox = new ComboBox<Integer>();
        Shipsbox.setPromptText("Choose The Number Of Ships ");
        Shipsbox.setId("box");
        for (int i = 1; i <= 10 * 2 / 3; i++) {

            Shipsbox.getItems().add(i);
        }

        Shipsbox.setOnAction(e -> {

            if (Safety) {
                int num = Shipsbox.getValue();
                BattleshipGameSettings.setShipsCount(num);
            }

        });

        GridSizeBox = new ComboBox<Integer>();

        GridSizeBox.setPromptText("Choose your Grid Size");
        GridSizeBox.setId("box");

        GridSizeBox.getItems().addAll(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        GridSizeBox.setOnAction(e -> {

            int num = GridSizeBox.getValue();
            BattleshipGameSettings.setGridSize(num);

            MaxNumberOfShips = num * 2 / 3;
            Safety = false;

            Shipsbox.getItems().clear();

            for (int i = 1; i <= MaxNumberOfShips; i++) {

                Shipsbox.getItems().add(i);

            }

            Safety = true;

        });
        
        GameStatistics = new Button("GameStatistics");
        GameStatistics.setOnAction(e->{
        	GuiWindows.GameStatistics.Display();
        	
        });	
        

        Play = new Button("New Game");
        Play.setLayoutX(250);
        Play.setLayoutY(250);
        Play.setPrefSize(200, 90);

        Play.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	
            	String name=SavedGames.Display();
            	
            	

            	try {
            		p1 = new HumanPlayer();
					PlayerInfo info = GameLoader.getPlayerInfo(name);
					if(info==null) {
						((HumanPlayer)(p1)).initPlayerinfo(name);
					}
					else {
						
						p1.setPlayerData(info);
					}
					
		             
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
            	
                
                

                if (DifficultyMode == "Easy") {
                    p2 = new longStrategyPlayer();

                } else if (DifficultyMode == "Hard") {
                    p2 = new SmartRandomComputerPlayer();
                }
                
                try {
            	
					PlayerInfo info = GameLoader.getPlayerInfo("Computer");
					if(info==null) {
						((ComputerPlayer)(p2)).initPlayerinfo();
					}
					else {
						
						p2.setPlayerData(info);
					}
					
		             
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                

                BattleshipGame bsg = new BattleshipGame();

                try {
                    p1.SupscripeTo(bsg);
                } catch (PlayerNotCompatableException e) {

                    e.printStackTrace();
                }

                try {
                    p2.SupscripeTo(bsg);
                } catch (PlayerNotCompatableException e) {

                    e.printStackTrace();
                }
            
                
                bsg.SetMainmenu(scene);
                bsg.setStage(primaryStage);
                bsg.GamesList=Gamelist;
                
                bsg.getCurrentGameState().setHuman(p1);
                bsg.getCurrentGameState().setComputer(p2);
                
                
                
                bsg.Start();
                bsg.RunGame();

            }

        });
        
        
        Button Load = new Button();
        
        Gamelist = new ComboBox<GameState>();
        Gamelist.setPromptText("GamesId");
        Gamelist.setId("box");
      
        
        ArrayList<GameState> games;
        
		try {
			games = GameLoader.GetGames();
			if(!games.isEmpty()) {
	        	
	        	for(GameState state: games) {
	        		
	        		Gamelist.getItems().add(state);
	        		
	        	}
	        	
        }
		} catch (ClassNotFoundException | IOException e2) {
		
		
		}
        
        Gamelist.setOnAction(e->{
        	
        	GameLoader.state=Gamelist.getValue();
        	Load.setDisable(false);
        });
        
        
       
        Load.setPrefSize(200, 90);
        Load.setAlignment(Pos.CENTER);
        Load.setText("Load Game");
        Load.setDisable(true);
        Load.setOnAction(e->{
        	
        	
        	Load.setDisable(true);
        
        
			
			
						try {
							GameLoader.LoadState(primaryStage, scene,Gamelist);
						} catch (ClassNotFoundException | IOException e1) {
						
							e1.printStackTrace();
						}
						
			
        });
        

        CheatMode = new CheckBox("Enable Cheating Mode!");
        CheatMode.setPadding(new Insets(10, 10, 30, 10));
        CheatMode.setId("check");
        CheatMode.setOnAction(e -> {
            BattleshipGameSettings.setShowOpGrid(!BattleshipGameSettings.isShowOpGrid());

        });

        Difficulty = new ComboBox<String>();
        Difficulty.setPromptText("DifficultyMode");
        Difficulty.setId("box");
        Difficulty.getItems().addAll("Easy", "Hard");
        Difficulty.setOnAction(e -> {

            DifficultyMode = Difficulty.getValue();
        });
        CSS = new ComboBox<String>();
        CSS.setPromptText("Style");
        CSS.setId("styleBox");
        CSS.getItems().addAll("1", "2");
        CSS.setOnAction(e -> {

            BattleshipGameSettings.setCss(Integer.parseInt(CSS.getValue()));
            if (BattleshipGameSettings.getCss() == 1) {
                scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
                primaryStage.setScene(scene);
            } else {
                scene.getStylesheets().add(getClass().getResource("mainMenu2.css").toExternalForm());
                primaryStage.setScene(scene);
            }

        });

        root.add(Play, 0, 0);
        root.add(Gamelist, 0, 1);
        root.add(Load, 0, 2);
        root.add(GridSizeBox, 0, 3);
        root.add(Shipsbox, 0, 4);
        root.add(Difficulty, 0, 5);
        root.add(CSS, 0, 6);
        root.add(GameStatistics, 0, 7);
        root.add(CheatMode, 4, 0);
        root.setPadding(new Insets(30, 30, 30, 0));

        scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
        if (BattleshipGameSettings.getCss() == 2) {
            scene.getStylesheets().add(getClass().getResource("mainMenu2.css").toExternalForm());
        }

        primaryStage.setTitle("BattleShips");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch();

    }

}
