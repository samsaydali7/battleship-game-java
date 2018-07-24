package BattleShipGamePackage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import Saving_loading.GameLoader;
import Saving_loading.GameSaver;
import Saving_loading.GameState;
import Threads.Counter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BattleScene {

    final String Part_destroyed = "Part Destruction";
    final String Full_destroyed = "Full Ship Destruction";
    final String All_destroyed = "All Ships Distroyed";
    final String Nothing_destroyed = "Water";

    Scene MainMENU;
    Stage window;

    Label labelHuman, LabelAi, CounterLabel,LabelTurn;

    VBox  vbox,save_load;
    
    Scene scene;
    ComboBox<GameState> list;

    ComputerPlayer AI;
    HumanPlayer human;

    GridPane humanGridPane, AiGridPane;

    AnchorPane root;
    
    Button button,Save,Load;
    
    Counter counter;
    Thread CounterThread;

    public BattleScene() {

        root = new AnchorPane();

        humanGridPane = new GridPane();
        AiGridPane = new GridPane();

        vbox= new VBox();
        save_load=new VBox();
        
        
        
        LabelAi = new Label();
        labelHuman = new Label();
        CounterLabel =  new Label();
        LabelTurn = new Label();
        
		button = new Button();
	
		Save = new Button();

    }

    public void InitScene() {

        labelHuman.setLayoutX((18 + 60 * BattleshipGameSettings.getGridSize()) / 2 - 46);
        labelHuman.setLayoutY(140);
        labelHuman.setPrefSize(92, 23);
        labelHuman.setAlignment(Pos.CENTER);

        LabelAi.setLayoutX(((60 * BattleshipGameSettings.getGridSize() + 50) + (2 * 60 * BattleshipGameSettings.getGridSize() + 50)) / 2 - 46);
        LabelAi.setLayoutY(140);
        LabelAi.setPrefSize(92, 23);
        LabelAi.setAlignment(Pos.CENTER);
        
        
        
        
        
        
       
        CounterLabel.setPrefSize(112, 23);
        CounterLabel.setAlignment(Pos.CENTER);
        
        
        
        LabelTurn.setPrefSize(150, 35);
        
        LabelTurn.setAlignment(Pos.CENTER);
        LabelTurn.setText("Your turn");
        
        button.setPrefSize(150, 35);
        button.setAlignment(Pos.CENTER);
        button.setText("Pass turn");
        button.setOnAction(e->{
        	
        	if(counter.getCurrentTurn()) {
        		
        		 counter.setPlayerTurn(false);
        		 counter.resetCounter();
        		
        	}
        	
        });
        
      
        
        Save.setPrefSize(100, 50);
        Save.setAlignment(Pos.CENTER);
        Save.setText("Save Game");
        Save.setOnAction(e->{
        	
        
        		
        		
        		
        		try {
        			GameState state = AI.returnGame().getCurrentGameState();
            		state.updateSettingVariables();
					GameSaver.SaveGame(state);
				} catch (ClassNotFoundException | IOException e1) {
					
					e1.printStackTrace();
				}
        		
				
		
				
        	
        	
        	
        });
        
        
        
        
        vbox.setLayoutX(60 * BattleshipGameSettings.getGridSize() + 35 - (112/2) );
        vbox.setLayoutY(70);
        vbox.getChildren().addAll(LabelTurn, CounterLabel,button);
        vbox.setSpacing(5);
        vbox.setMargin(CounterLabel, new Insets(0,0,0,10));
        
        
        save_load.setLayoutX(10);
        save_load.setLayoutY(20);
        save_load.getChildren().addAll(Save);
        save_load.setSpacing(10);
        
        
        
        humanGridPane.setLayoutX(18);
        humanGridPane.setLayoutY(200);

        for (int i = 0; i < BattleshipGameSettings.getGridSize(); i++) {

            for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {

                Button button = new Button();
                button.setText(String.format("%d %d", i + 1, j + 1));
                button.setPrefHeight(60);
                button.setPrefWidth(60);

                humanGridPane.add(button, j, i);
            }

        }

        AiGridPane.setLayoutX(60 * BattleshipGameSettings.getGridSize() + 50);
        AiGridPane.setLayoutY(200);

        for (int i = 0; i < BattleshipGameSettings.getGridSize(); i++) {

            for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {

                Button button = new Button();
                button.setText(String.format("%d %d", i + 1, j + 1));
                button.setPrefHeight(60);
                button.setPrefWidth(60);
                button.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                        UserTurn(arg0);

                    }

                });

                AiGridPane.add(button, j, i);
            }

        }

        root.getChildren().addAll(humanGridPane, AiGridPane, LabelAi, labelHuman,vbox,save_load);

        scene = new Scene(root, 2 * (60 * BattleshipGameSettings.getGridSize() )+70, 60 * BattleshipGameSettings.getGridSize() + 250);
        if (BattleshipGameSettings.getCss() == 2) {
            scene.getStylesheets().add(getClass().getResource("viper2.css").toExternalForm());
        } else {
            scene.getStylesheets().add(getClass().getResource("viper.css").toExternalForm());
        }

    }

    private void UserTurn(ActionEvent e) {
    	
    	if(counter.getCurrentTurn()) {

    		
    	
    		
        Button btn = (Button) e.getSource();
        String s = btn.getText();
        String[] temp = s.split(" ");

        BattleshipMove move = new BattleshipMove(GameUtil.coordsToString(Integer.parseInt(temp[0]) - 1, Integer.parseInt(temp[1]) - 1));

        BattleshipMoveResult result = AI.AcceptPlayerMove(move);

        String norification = result.getNotfication();

        if (norification.equals(Part_destroyed) || norification.equals(All_destroyed) || norification.equals(Full_destroyed)) {
            btn.setDisable(true);
            btn.setStyle("-fx-background-color: #559684");
            if (norification.equals(Full_destroyed) || norification.equals(All_destroyed)) {
                LabelAi.setText(String.valueOf(AI.getShipsCount()));
            }
            
        } else if (norification.equals(Nothing_destroyed)) {
            btn.setDisable(true);
            btn.setStyle("-fx-background-color: #dd0000");

        }
        else if(norification.equals("Mine Hit : Parts Destruction") || norification.equals("Mine Hit : Full Ships Destruction") || norification.equals("Mine Hit") ) {
        	
        	btn.setDisable(true);
        	btn.setStyle("-fx-background-color: #dd0000");
        	
        	for(int[] d : result.getHits()) {
        		
        		Button bt = (Button)getNodeFromGridPane(AiGridPane, d[1], d[0]);
        		bt.setStyle("-fx-background-color: #559684");
        		bt.setDisable(true);
        		
        		
        	}
        	LabelAi.setText(String.valueOf(AI.getShipsCount()));
        	
        }

        human.NotifyPlayer(result);
        
        

        counter.setPlayerTurn(false);
		counter.resetCounter();
		 
     
       
        
        
    	}
    }

    public void EnemyTurn() {

        BattleshipMove move = AI.GetNextMove();
        
        if(move.coords!="Pass") {
        	
        
        Button btn = (Button) getNodeFromGridPane(humanGridPane, GameUtil.coordsToCol(move.coords), GameUtil.coordsToRow(move.coords));

        BattleshipMoveResult result = human.AcceptPlayerMove(move);

        String Notif = result.getNotfication();
        
       


        if (Notif.equals(Part_destroyed) || Notif.equals(All_destroyed) || Notif.equals(Full_destroyed)) {
            Platform.runLater(()->btn.setStyle("-fx-background-color: #dd0000"));
            if (Notif.equals(Full_destroyed) || Notif.equals(All_destroyed)) {
                Platform.runLater(()->labelHuman.setText(String.valueOf(human.getShipsCount())));
            }

        } else if (Notif.equals(Nothing_destroyed)) {
            Platform.runLater(()->btn.setStyle("-fx-background-color: #777777"));

        }
        
        
        else if(Notif.equals("Mine Hit : Parts Destruction") || Notif.equals("Mine Hit : Full Ships Destruction") || Notif.equals("Mine Hit") ) {
        	
        	
        	btn.setStyle("-fx-background-color: #777777");
        	
        	for(int[] d : result.getHits()) {
        		
        		Button bt = (Button)getNodeFromGridPane(humanGridPane, d[1], d[0]);
        		bt.setStyle("-fx-background-color: #dd0000");
        		
        		
        	}
        	 Platform.runLater(()->labelHuman.setText(String.valueOf(human.getShipsCount())));
        	
        	
        }

        
        
        
        AI.NotifyPlayer(result);

     
        

        }
        
    }

    public void ColorGrid() {
        Button butt;

        

            for (int i = 0; i < BattleshipGameSettings.getGridSize(); i++) {

                for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {

                    butt = (Button) getNodeFromGridPane(AiGridPane, j, i);

                    if (AI.getSquareAt(i, j) == "SHIP_PART" && BattleshipGameSettings.isShowOpGrid()) {
                        butt.setStyle("-fx-background-color: #DA70D6");
                    }
                    else if(AI.getSquareAt(i, j)=="MINE" && BattleshipGameSettings.isShowOpGrid()) {
                    	
                    	butt.setStyle("-fx-background-color: #40E0D0");
                    }
                    
                    else if(AI.getSquareAt(i, j)=="HIT" ) {
                    	butt.setDisable(true);
                    	butt.setStyle("-fx-background-color: #559684");
                    	
                    }
                    else if(AI.getSquareAt(i, j) == "MISS"   || AI.getSquareAt(i, j) == "MINE_HIT") {
                    	butt.setDisable(true);
                    	butt.setStyle("-fx-background-color: #dd0000");
                    	
                    }
                   

                }

            }
        

        for (int i = 0; i < BattleshipGameSettings.getGridSize(); i++) {

            for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {

                butt = (Button) getNodeFromGridPane(humanGridPane, j, i);

                if (human.getSquareAt(i, j) == "SHIP_PART") {
                    butt.setStyle("-fx-background-color: #8BC34A");
                } else if (human.getSquareAt(i, j) == "Water") {
                    butt.setStyle("-fx-background-color: #ADD8E6");

                }
                else if(human.getSquareAt(i, j)=="MINE") {
                	
                	butt.setStyle("-fx-background-color: #40E0D0");
                }
                else if(human.getSquareAt(i, j)=="HIT" ) {
               
                	butt.setStyle("-fx-background-color: #dd0000");
                	
                }
                else if(human.getSquareAt(i, j) == "MISS"  || human.getSquareAt(i, j) == "MINE_HIT") {
              
                	butt.setStyle("-fx-background-color: #777777");
                	
                }
                

            }

        }

        LabelAi.setText(String.valueOf(AI.getShipsCount()));
        labelHuman.setText(String.valueOf(human.getShipsCount()));

    }

    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public Player GetHuman() {

        return human;
    }

    public Player GetAi() {

        return AI;
    }

    public void SetHuman(Player p) {

        human = (HumanPlayer) p;
    }

    public void SetAI(Player p) {

        AI = (ComputerPlayer) p;

    }

    public void SetMainMenu(Scene scene) {

        MainMENU = scene;

    }
    public void SetWindow(Stage window) {
    	
    	this.window=window;
    }

    public Scene getScene() {

        return scene;
    }
    
    public GridPane getHumanGridPane() {
    	return humanGridPane;
    }
    public GridPane getAIPane() {
    	
    	return AiGridPane;
    }
    
    public void setCounterThread(Counter counter , Thread CounterThread) {
    	
    	this.counter=counter;
    	this.counter.setLabels(CounterLabel,LabelTurn);
    	this.CounterThread=CounterThread;
    	
    }
    

}
