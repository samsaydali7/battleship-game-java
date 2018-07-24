package Saving_loading;

import java.io.Serializable;

import BattleShipGamePackage.BattleshipGameSettings;
import BattleShipGamePackage.BattleshipPlayer;
import BattleShipGamePackage.ComputerPlayer;
import BattleShipGamePackage.Player;
import Threads.Counter;

public class GameState implements Serializable{
	
	BattleshipPlayer Human,Computer;
	Counter  counter;
	int gridSize ;
    int shipsCount;
    boolean showOpGrid = false;
    int css = 2;
    int ComputerSleepTime;

    int Gameid;
    
    
    public GameState() {
    	counter=null;
    	Human=Computer=null;
    	gridSize =0;
    	shipsCount=0;
    	showOpGrid=false;
    	int css=2;
    	ComputerSleepTime=0;
    }
    
	
	public void setHuman(BattleshipPlayer human) {
		Human=human;
	}
    public void setComputer(BattleshipPlayer computer) {
    	
    	Computer=computer;
    	
    	
    	
    }
    public void setCounter(Counter counter) {
    	this.counter=counter;
    }
    
    public void updateSettingVariables() {
    	
    	gridSize=BattleshipGameSettings.getGridSize();
    	shipsCount=BattleshipGameSettings.getShipsCount();
    	css=BattleshipGameSettings.getCss();
    	showOpGrid = BattleshipGameSettings.isShowOpGrid();
    	ComputerSleepTime=ComputerPlayer.sleepTime;
    	
    	
    }
    public void RestoreStaticSettings() {
    	
    	
    	BattleshipGameSettings.setGridSize(gridSize);
    	BattleshipGameSettings.setShipsCount(shipsCount);
    	BattleshipGameSettings.setCss(css);
    	BattleshipGameSettings.setShowOpGrid(showOpGrid);
    	ComputerPlayer.sleepTime=this.ComputerSleepTime;
    	
    }
    public void setgameId(int id) {
    	this.Gameid=id;
    	
    }


	public BattleshipPlayer getHuman() {
		return Human;
	}


	public BattleshipPlayer getComputer() {
		return Computer;
	}


	public Counter getCounter() {
		return counter;
	}


	public int getGridSize() {
		return gridSize;
	}


	public int getShipsCount() {
		return shipsCount;
	}


	public boolean isShowOpGrid() {
		return showOpGrid;
	}


	public int getCss() {
		return css;
	}


	public int getComputerSleepTime() {
		return ComputerSleepTime;
	}


	public int getGameid() {
		return Gameid;
	}
    
    public String toString() {
    	return String.valueOf(Gameid);
    }

}
