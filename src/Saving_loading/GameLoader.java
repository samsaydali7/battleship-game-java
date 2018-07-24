package Saving_loading;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import BattleShipGamePackage.BattleScene;
import BattleShipGamePackage.BattleshipGame;
import BattleShipGamePackage.BattleshipPlayer;
import GuiWindows.SavedGames;
import InfoClasses.PlayerInfo;
import Threads.Counter;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class GameLoader{
	
	
	private static  String fileName = "Save.txt";
	 private static  String  PlayersInfo_file = "playerinfo.txt";

	public static GameState state;

	
	
	
	 public static void  LoadState(Stage Window,Scene MainMenu,ComboBox<GameState> GameList) throws ClassNotFoundException, IOException {
		  
	     
		 state.RestoreStaticSettings();
		 
		 
		 BattleshipPlayer human = state.getHuman();
		 human.getMineThread().setPlayer(true);
		human.getMineThread().setDaemon(true);
		 
		 
		 
		 
		 
		 BattleshipPlayer computer = state.getComputer();
		 computer.getMineThread().setPlayer(false);
		 computer.getMineThread().setDaemon(true);
		 
		 
		 
		 
		 
		PlayerInfo info =  GameLoader.getPlayerInfo(computer.getPlayerData().getPlayerName());
		if(info!=null) {
			computer.setPlayerData(info);
		}
		
		PlayerInfo info2 = GameLoader.getPlayerInfo(human.getPlayerData().getPlayerName());
		if(info2!=null) {
			
			human.setPlayerData(info2);
		}
		 
		 
		 
		 
		 
		 
		 
		 BattleshipGame game = (BattleshipGame)human.returnGame(); 
		 game.GamesList=GameList;													//so that the game can now close or return to the current mainmenu and current window
		 game.SetMainmenu(MainMenu);
		 game.setStage(Window);
		 
		 
		 Counter counter= state.getCounter();
		 Thread t = new Thread(counter);
		 
		 t.setDaemon(true);
		 
		 
		 
		 
	     BattleScene battle = new BattleScene();
	     battle.InitScene();
		 battle.SetHuman(human);
		 battle.SetAI(computer);
		 battle.setCounterThread(counter, t);
	     battle.SetWindow(Window);
	     battle.SetMainMenu(MainMenu);
	     
		
		 
		 
		 counter.SetBattle(battle);
		 human.getMineThread().setBattleScene(battle);
		 computer.getMineThread().setBattleScene(battle);
		 
		 t.start();
		human.getMineThread().start();
		computer.getMineThread().start();
		 
		 
		 battle.ColorGrid();
		 Window.setScene(battle.getScene());
		 
	      
	   }
	 
	 public static ArrayList<GameState> GetGames() throws ClassNotFoundException, IOException{
		 
		  ArrayList<GameState> listGames = new ArrayList<GameState>();
		  
		    FileInputStream fis = new FileInputStream(fileName);

	      ObjectInputStream obj = new ObjectInputStream(fis);
	      try {
	         while (fis.available() != -1) {
	         
	        	 GameState temp_state = (GameState) obj.readObject();
	        	 listGames.add(temp_state);
	         }
	      } catch (EOFException ex) {
	        
	      }
	      obj.close();
	      return listGames;
	      
		 
		 
		 
	 }
	 public static PlayerInfo getPlayerInfo(String Name) throws IOException, ClassNotFoundException {
		 
		 ArrayList<PlayerInfo> playersinfo = new ArrayList<PlayerInfo>();
		  
		    FileInputStream fis = new FileInputStream(PlayersInfo_file);

		    ObjectInputStream obj = new ObjectInputStream(fis);
	      try {
	         while (fis.available() != -1) {
	         
	        	 PlayerInfo info = (PlayerInfo) obj.readObject();
	        	 playersinfo.add(info);
	         }
	      } catch (EOFException ex) {
	        
	      }
	      obj.close();
	    for(PlayerInfo info : playersinfo) {
	    	
	    	if(info.getPlayerName().equals(Name)) {
	    		
	    		return info;
	    	}
	    	
	    	
	    	
	    }
	    return null;
	      
	      
	      
	      
		 
		 
		 
		 
		 
	 }


}
	