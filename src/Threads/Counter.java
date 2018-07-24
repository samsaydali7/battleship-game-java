package Threads;

import java.io.Serializable;

import BattleShipGamePackage.BattleScene;
import BattleShipGamePackage.ComputerPlayer;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Counter implements Runnable ,Serializable{
	
	
	int i=0;
	transient Label label,labelTurn;
	boolean playerTurn = true;
	boolean firstEnter = true;
	transient Thread computerl;
	transient BattleScene battle;
	 private boolean running = true;


	 public void Terminate() {
	        this.running = false;
	    }
 	public void resetCounter() {
 		i=0;
	
	
	
	
 	}
 	public int getCount() {
 		
 		
 		return i;
 	}
 	public boolean getCurrentTurn() {
 		
 		return playerTurn;
 	}
@Override
public void run() {
	
	while(running) {
		
		try {
			
			Platform.runLater(()->label.setText(String.valueOf(i)));
			
			if( ( i>=0 && i<10)  &&  playerTurn==false   &&  firstEnter==true) {
				
				
				computerl=new Thread(new Runnable() {
						
						@Override
						public void run() {
							Platform.runLater(()->labelTurn.setText("Enemy turn"));
							battle.EnemyTurn();
							
						}
					});
			        
				computerl.start();
				firstEnter=false;
			}
			if(ComputerPlayer.sleepTime >=10 ) {
			
			if( i > 10  && playerTurn==false)
				{	
					playerTurn=true;
					
					i=0;
					Platform.runLater(()->labelTurn.setText("YourTurn"));
					computerl.interrupt();
			
					firstEnter=true;
				
				}
			
			}
			
			else if(ComputerPlayer.sleepTime <10  )
			
			{
				
				if( i > ComputerPlayer.sleepTime +1  && playerTurn==false)
				{	
					playerTurn=true;
					
					i=0;
					Platform.runLater(()->labelTurn.setText("YourTurn"));
					computerl.interrupt();
			
					firstEnter=true;
				
				}
				
				
				
			}
			
			Thread.sleep(1000);
			i++;
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}

	public void setLabels(Label label,Label label2) {
	
		this.label=label;	
		this.labelTurn=label2;
	}
	
	public void setPlayerTurn(boolean playturn) {
		this.playerTurn=playturn;
	
	}
	
	

	public void SetBattle(BattleScene battle) {
		
		this.battle=battle;
	}
	public int getI() {
		return i;
	}
	public Label getLabel() {
		return label;
	}
	public Label getLabelTurn() {
		return labelTurn;
	}
	public boolean isPlayerTurn() {
		return playerTurn;
	}
	public boolean isFirstEnter() {
		return firstEnter;
	}
	public Thread getComputerl() {
		return computerl;
	}
	public BattleScene getBattle() {
		return battle;
	}
	
	

}
