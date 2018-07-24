/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

import java.awt.Paint;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import InfoClasses.GameInfo;
import InfoClasses.GameSteps;
import InfoClasses.PlayerInfo;
import JsonHandling.GameInfoHandler;
import JsonHandling.GameStepsHandler;
import JsonHandling.PlayerInfoHandler;
import Saving_loading.GameLoader;
import Saving_loading.GameSaver;
import Saving_loading.GameState;
import Threads.Counter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class BattleshipGame extends Game {

   transient Stage window;
   transient Scene Mainmenu;
   transient  BattleScene battle;
   transient ChoosingShipsScene Choice;
   public transient ComboBox<GameState> GamesList;
    
    
    


    private void InitializeUserInterface() {

        battle.InitScene();
        battle.SetMainMenu(Mainmenu);
        battle.SetWindow(window);
        battle.list=GamesList;
        
        Choice.InitScene();
        Choice.setWindow(window);
        Choice.SetBattle(battle);

    }

    public BattleshipGame() {
        super();
        
        battle = new BattleScene();

        Choice = new ChoosingShipsScene();
        
    }

    @Override
    void Start() {
        for (Player p : players) {

            ((BattleshipPlayer) p).Init();

            if (p instanceof ComputerPlayer) {
                battle.SetAI(p);
                Choice.SetAI(p);

            } else if (p instanceof HumanPlayer) {
                battle.SetHuman(p);
                Choice.SetHuman(p);
            }

        }
    }

    @Override
    void End() {

    	((BattleshipPlayer)(this.players.get(0))).getMineThread().Terminate();
        ((BattleshipPlayer)(this.players.get(1))).getMineThread().Terminate();
        
        
        PlayerInfo info1 = ((BattleshipPlayer) (this.players.get(0))).getPlayerData();
        PlayerInfo info2  =((BattleshipPlayer) (this.players.get(1))).getPlayerData();
        
        info1.setPlayedGames(info1.getPlayedGames()+1);
        info2.setPlayedGames(info2.getPlayedGames()+1);
        
         if(Winner=="You Lose!") {
        	 
        	 info2.setWins(info2.getWins()+1);
        	
        	 
        	 
        	 
         }
         
         else if(Winner=="you Win!!") {
        	 
        	 info1.setWins(info1.getWins()+1);
        
        	 
         }
         info2.setMark((double)info2.getWins()/info2.getPlayedGames());
    	 info1.setMark((double)info1.getWins()/info1.getPlayedGames());
        
        PlayerInfoHandler pih1 = new PlayerInfoHandler(info1);
        PlayerInfoHandler pih2 = new PlayerInfoHandler(info2);
        pih1.writeJsonToFile();
        pih2.writeJsonToFile();
        
        
        
        GameSteps gs = new GameSteps(this.gameID, steps, firstPlayerGrid,secondPlayerGrid);
        
      /*  for (int i = 0; i < BattleshipGameSettings.getGridSize(); i++) {
            for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {
                System.out.print(firstPlayerGrid.getSquares()[i][j].getState() + " ");
            }
            System.out.println("\n");
        }
        
        for (int i = 0; i < BattleshipGameSettings.getGridSize(); i++) {
            for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {
                System.out.print(secondPlayerGrid.getSquares()[i][j].getState() + " ");
            }
            System.out.println("\n");
        }
        
*/
        GameStepsHandler gsh = new GameStepsHandler(gs);
        gsh.writeJsonToFile();
        
        
        
        
        Date d = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
        this.endDate = sd.format(d).toString();
        String fpn = ((BattleshipPlayer) (this.players.get(0))).getPlayerData().getPlayerName();
        String spn = ((BattleshipPlayer) (this.players.get(1))).getPlayerData().getPlayerName();
        double fpm = ((BattleshipPlayer) (this.players.get(0))).getPlayerData().getMark();
        double spm = ((BattleshipPlayer) (this.players.get(1))).getPlayerData().getMark();
        GameInfo gi;
        
        if(Winner=="You Lose!") {
             gi = new GameInfo(this.gameID, fpn, spn, fpm, spm, this.startDate, this.endDate, spn);
             GameInfoHandler gih = new GameInfoHandler(gi);
             gih.writeJsonToFile();
             System.out.println("lose");

        }
        else if(Winner=="you Win!!") {
            gi = new GameInfo(this.gameID, fpn, spn, fpm, spm, this.startDate, this.endDate, fpn);
            GameInfoHandler gih = new GameInfoHandler(gi);
            gih.writeJsonToFile();
            System.out.println("win");
            
        }
        
        try {
			GameSaver.SavePlayerInfo(info1);
			GameSaver.SavePlayerInfo(info2);
			
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
    
        

        
        
        
        
        
    	
        AnchorPane Pane = new AnchorPane();

        Button btn1 = new Button("Return to mainMenu");
        btn1.setLayoutX(300);
        btn1.setLayoutY(18 + 150);
        btn1.setPrefSize(350, 150);
        btn1.setOnAction(e -> {

            window.setScene(Mainmenu);
        });

        Button btn2 = new Button("Quit");
        btn2.setLayoutX(350);
        btn2.setLayoutY(380);
        btn2.setPrefSize(250, 150);

        btn2.setOnAction(e -> {
            window.close();

        });

        Label label = new Label("YOU LOSE!");
        label.setText(Winner);
        label.setStyle("-fx-background-color: #FFFFFF");
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(300, 90);
        label.setLayoutX(320);
        label.setLayoutY(18);
        Pane.getChildren().addAll(btn1, btn2, label);
        
        try {
        	GamesList.getItems().clear();
			 ArrayList<GameState> Games;
			 Games = GameLoader.GetGames();
			if(!Games.isEmpty()) {
	        	
	        	for(GameState sta: Games) {
	        		
	        		GamesList.getItems().add(sta);
	        		
	        	}
	        	
        }
		} catch (ClassNotFoundException | IOException e2) {
		
		
		}

        Scene endScene = new Scene(Pane, 1000, 600);
        endScene.getStylesheets().add(getClass().getResource("EndScene.css").toExternalForm());
        Platform.runLater(()-> window.setScene(endScene) );
        
        

    }

    @Override
    public void Subscribe(Player player) {
        players.add(player);
    }

    @Override
    public void LeavGame(Player player) {
    	
        End();
        
    }

    public void SetMainmenu(Scene scene) {
        Mainmenu = scene;

    }

    public void setStage(Stage window) {

        this.window = window;

    }

    void RunGame() {

        InitializeUserInterface();

        window.setTitle("BattleShips Game ");
        window.setScene(Choice.getScene());
        window.show();

    }
    
    
}
