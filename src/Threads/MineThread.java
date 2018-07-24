/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;



import java.io.Serializable;
import java.util.LinkedList;

import BattleShipGamePackage.BattleScene;
import BattleShipGamePackage.BattleshipGameSettings;
import BattleShipGamePackage.Mine;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Samer2
 */
public class  MineThread extends Thread implements Serializable{

    private Mine mineObj;
    private boolean running = true;
    transient private BattleScene battle;
    boolean player;

    public MineThread(Mine mineObj) {
        this.mineObj = mineObj;
    }

    public void Terminate() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
            	 
              
              
               
                
                if(!player) {
                	
                	if(BattleshipGameSettings.isShowOpGrid()) {
                		
                		updateGui();
                	}
                	
                	
                }
                else
                {
                	updateGui();
                	
                }
                sleep(1500);
               
                mineObj.updatePosition2();
                
                
                
                
                
            } catch (InterruptedException ex) {
                this.mineObj.endMine();
                System.out.println("Mine Ended!");
                this.Terminate();
                
            }
        }
    }
    
    private void updateGui() {
    	
    	
    	 if(player) {  
             GridPane pane=  battle.getHumanGridPane();
             
             LinkedList<int[]> temp = mineObj.getList();
             
             for(int[] t : temp) {
            	 Button btn1= (Button) battle.getNodeFromGridPane(pane, t[1], t[0]);
                 btn1.setStyle("-fx-background-color: #ADD8E6");
            	 
             }
             temp.clear();
             
             
             int a = mineObj.getYCoordinate();
             int b= mineObj.getXCoordinate();
            
             
            
             
             Button btn2 = (Button) battle.getNodeFromGridPane(pane, b, a);
             btn2.setStyle("-fx-background-color: #40E0D0");
             }
    	 
             
             else if(!player) {
           	  GridPane pane=  battle.getAIPane();
           	  
           	  LinkedList<int[]> temp = mineObj.getList();
             
             for(int[] t : temp) {
            	 Button btn1= (Button) battle.getNodeFromGridPane(pane, t[1], t[0]);
                 btn1.setStyle(null);
            	 
             }
             
             temp.clear();
             
             int a = mineObj.getYCoordinate();
             int b= mineObj.getXCoordinate();
            
                 
                 
                 
                 
               
                 
                 Button btn2 = (Button) battle.getNodeFromGridPane(pane, b, a);
                 btn2.setStyle("-fx-background-color: #40E0D0");
           	  
           	  
           	  
             }
             
    	
    	
    }
    
 public Mine getMineobject() {
	 
	 return mineObj;
 }
 
 public void setBattleScene(BattleScene battle) {
 	this.battle=battle;
 	
 }
 
 public void setPlayer(boolean player) {
 	
 	this.player=player;
 }

}
