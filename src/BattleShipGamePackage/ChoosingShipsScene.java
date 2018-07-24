package BattleShipGamePackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import Threads.Counter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

public class ChoosingShipsScene {

    Scene scene;
    BattleScene battle;
    
    //CounterThread counter;

    Random r = new Random();												//we store the scene in which the battle occurs here so we can switch to it;

    GridPane pane;
    Button btn, btn2;

    boolean set = true;

    final String yellowStyle = "-fx-background-color: #ffff20";
    final String RedStyle = "-fx-background-color: #ff0000";

    String location_to_place;

    int CurrentNumOfShipsLeft = BattleshipGameSettings.getShipsCount();                   //initilializing number of ships
    int shiptype = 0;
    Alignment align = Alignment.BOTTOM;                   //default alignment

    BorderPane root;
    Stage window;

    List<String> list = new ArrayList<String>();

    ComputerPlayer AI;
    HumanPlayer human;
    
    private void StartBattleScene() {
    	
    	
    	Counter counter = new Counter();
    	Thread t = new Thread(counter);
    	
    	AI.returnGame().getCurrentGameState().setCounter(counter);     

    	AI.returnGame().setFirstPlayerGrid(human.getPlayerGrid());
    	AI.returnGame().setSecondPlayerGrid(AI.getPlayerGrid());
    	
    	
    	
    	
    	counter.SetBattle(battle);
    	t.setDaemon(true);
    	 
    	human.setMine();
    	
    	human.getMineThread().setBattleScene(battle);
    	human.getMineThread().setDaemon(true);
    	human.getMineThread().setPlayer(true);
    	
    	AI.setMine();
    	
    	AI.getMineThread().setBattleScene(battle);
    	AI.getMineThread().setDaemon(true);
    	AI.getMineThread().setPlayer(false);
    	
    	
    	
    	
    	
    	
    	
    	AI.getMineThread().start();
    	human.getMineThread().start();

    	t.start();
    	
    	
    	
    	
    	
    	battle.setCounterThread(counter, t);

        battle.ColorGrid();
        window.setScene(battle.getScene());
        
        
        
    	
    	
    }

    public void InitScene() {

        root = new BorderPane();

        ComboBox<Alignment> box = new ComboBox();

        box.setPromptText("Choose your Alignment");
        box.setId("box");

        box.getItems().addAll(Alignment.BOTTOM, Alignment.LEFT, Alignment.RIGHT, Alignment.TOP);                       //left listview

        box.setOnAction(e -> {

            align = box.getValue();

            ResetColors();

            int x = GameUtil.coordsToCol(location_to_place);
            int y = GameUtil.coordsToRow(location_to_place);

            ColorButtons(x, y);

        });

        ComboBox<Integer> box2 = new ComboBox();

        box2.setPromptText("Choose your Current ship size");
        box2.setId("box");																					//right listview

        for (int i = 1; i <= BattleshipGameSettings.getGridSize(); i++) {
        	if(i<=6)
        		box2.getItems().add(i);

        }

        box2.setOnAction(e -> {

            shiptype = box2.getValue();
        });

        btn2 = new Button();
        btn2.setText(String.valueOf(CurrentNumOfShipsLeft));              //top button
        btn2.setMaxSize(250, 250);

        btn = new Button();
        btn.setText("press to Place ship");								//bottom button
        btn.setMaxSize(250, 250);
        btn.setOnAction(e -> {
            String s = btn.getText();

            if (s.equals("Press to Play!")) {
            	
            
            	StartBattleScene();
                
                
                

            } else {

                int x = GameUtil.coordsToCol(location_to_place);
                int y = GameUtil.coordsToRow(location_to_place);

                DisableButtons(x, y);
                human.addShip(new Ship(location_to_place, align, shiptype));
                CurrentNumOfShipsLeft--;

                btn2.setText(String.valueOf(CurrentNumOfShipsLeft));

                set = !set;
                AiChooseShip();

                if (CurrentNumOfShipsLeft == 0) {

                    btn.setText("Press to Play!");
                    set = false;

                }

            }

        });

        pane = new GridPane();

        pane.setPadding(new Insets(0));                                                     //Grid of buttons in center 

        pane.setVgap(3);
        pane.setHgap(3);

        for (int i = 0; i < BattleshipGameSettings.getGridSize(); i++) {

            for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {

                Button button = new Button();                                            //   buttons inside the grid

                button.setStyle("-fx-background-color: #ADD8E6");
                button.setText(String.format("%d %d", i + 1, j + 1));
                button.setPrefHeight(80);
                button.setPrefWidth(80);

                button.setOnMouseEntered(e -> {

                    if (set) {

                        ResetColors();

                        String st = ((Button) e.getSource()).getText();
                        String[] ar = st.split(" ");
                        int l = Integer.parseInt(ar[1]) - 1;
                        int h = Integer.parseInt(ar[0]) - 1;

                        ColorButtons(l, h);

                    }

                });

                button.setOnAction(e -> {

                    String st = ((Button) e.getSource()).getText();
                    String[] ar = st.split(" ");
                    int l = Integer.parseInt(ar[1]) - 1;
                    int h = Integer.parseInt(ar[0]) - 1;
                    location_to_place = GameUtil.coordsToString(h, l);												//to stop coloring while choosing alignment or ship size
                    set = !set;

                });

                pane.add(button, j, i);
            }

        }

        root.setAlignment(pane, Pos.CENTER);
        root.setMargin(pane, new Insets(50, 0, 0, 90));

        root.setCenter(pane);
        root.setRight(box2);
        root.setLeft(box);

        root.setAlignment(btn, Pos.CENTER);
        root.setMargin(btn, new Insets(12, 12, 12, 12));
        root.setBottom(btn);

        root.setAlignment(btn2, Pos.CENTER);
        root.setTop(btn2);

        scene = new Scene(root, BattleshipGameSettings.getGridSize() * 80 + 600, BattleshipGameSettings.getGridSize() * 80 + 150);
        if (BattleshipGameSettings.getCss() == 1) {
            scene.getStylesheets().add(getClass().getResource("ships.css").toExternalForm());
        } else {
            scene.getStylesheets().add(getClass().getResource("ships2.css").toExternalForm());
        }

    }

    public Scene getScene() {

        return scene;

    }

    private void ResetColors() {

        for (String s : list) {

            String[] st = s.split(" ");
            Button bt = (Button) getNodeFromGridPane(pane, Integer.parseInt(st[0]), Integer.parseInt(st[1]));
            bt.setStyle("-fx-background-color: #ADD8E6");

        }
        list.clear();

    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    private void DisableButtons(int l, int h) {

        switch (align) {

            case RIGHT:

                for (int k = 0; k < shiptype; k++) {

                    Button bt = (Button) getNodeFromGridPane(pane, l + k, h);
                    bt.setDisable(true);

                }
                break;

            case LEFT:

                for (int k = 0; k < shiptype; k++) {

                    Button bt = (Button) getNodeFromGridPane(pane, l - k, h);
                    bt.setDisable(true);

                }
                break;

            case TOP:

                for (int k = 0; k < shiptype; k++) {

                    Button bt = (Button) getNodeFromGridPane(pane, l, h - k);
                    bt.setDisable(true);

                }
                break;

            case BOTTOM:

                for (int k = 0; k < shiptype; k++) {

                    Button bt = (Button) getNodeFromGridPane(pane, l, h + k);
                    bt.setDisable(true);

                }

                break;

        }

    }

    private void ColorButtons(int l, int h) {

        String ColorToUse = yellowStyle;

        Validity valid = human.IsValid(align, l, h, shiptype);

        if (!valid.isValid()) {
            ColorToUse = RedStyle;
            btn.setDisable(true);
        } else {
            btn.setDisable(false);
        }
        switch (align) {

            case RIGHT:

                for (int k = 0; k < valid.getSize(); k++) {

                    Button bt = (Button) getNodeFromGridPane(pane, l + k, h);
                    bt.setStyle(ColorToUse);

                    list.add(String.format("%d %d", l + k, h));

                }
                break;

            case LEFT:

                for (int k = 0; k < valid.getSize(); k++) {

                    Button bt = (Button) getNodeFromGridPane(pane, l - k, h);
                    bt.setStyle(ColorToUse);

                    list.add(String.format("%d %d", l - k, h));

                }
                break;

            case TOP:

                for (int k = 0; k < valid.getSize(); k++) {

                    Button bt = (Button) getNodeFromGridPane(pane, l, h - k);
                    bt.setStyle(ColorToUse);

                    list.add(String.format("%d %d", l, h - k));

                }
                break;

            case BOTTOM:

                for (int k = 0; k < valid.getSize(); k++) {

                    Button bt = (Button) getNodeFromGridPane(pane, l, h + k);
                    bt.setStyle(ColorToUse);

                    list.add(String.format("%d %d", l, h + k));

                }

                break;

        }
    }

    private void AiChooseShip() {

        Validity valid;
        int i, x, y;
        Alignment aligns[] = {Alignment.RIGHT, Alignment.BOTTOM, Alignment.LEFT, Alignment.TOP};
        do {

            i = r.nextInt(4);
            x = r.nextInt(BattleshipGameSettings.getGridSize());
            y = r.nextInt(BattleshipGameSettings.getGridSize());
            valid = AI.IsValid(aligns[i], x, y, shiptype);

        } while (!valid.isValid());

        AI.addShip(new Ship(GameUtil.coordsToString(y, x), aligns[i], shiptype));

    }

    public void setWindow(Stage window) {

        this.window = window;

    }

    public void SetBattle(BattleScene battle) {

        this.battle = battle;

    }

    public void SetHuman(Player p) {

        human = (HumanPlayer) p;
    }

    public void SetAI(Player p) {

        AI = (ComputerPlayer) p;

    }

}
