package Saving_loading;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import BattleShipGamePackage.BattleScene;
import BattleShipGamePackage.BattleshipGame;
import BattleShipGamePackage.BattleshipPlayer;
import BattleShipGamePackage.SmartRandomComputerPlayer;
import GuiWindows.SavedGames;
import InfoClasses.PlayerInfo;
import Threads.Counter;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameSaver {
	
	 private static  String fileName = "Save.txt";
	 private static  String  PlayersInfo_file = "playerinfo.txt";
	 
	 

	 
	
	public static  void  SaveGame(GameState state) throws IOException, ClassNotFoundException {
		
		  ArrayList<GameState> States = new ArrayList<GameState>();
		  


	      FileInputStream fis = new FileInputStream(fileName);

	      ObjectInputStream objfile = new ObjectInputStream(fis);
	      
	      try {
	         while (fis.available() != -1) {
	           
	        	 GameState temp_state = (GameState) objfile.readObject();
	        	 
	        	 States.add(temp_state);
	        	 
	         }
	      } catch (EOFException ex) {
	       // ex.printStackTrace();
	      }
	      objfile.close();

	
	      
	
	      ArrayList<GameState> items_to_be_removed = new ArrayList<GameState>();
	      
	      for(GameState sta : States) {
	    	  
	    	  if(sta.Gameid==state.Gameid) {
	    		  
	    	
	    		 
	    		
	    		  items_to_be_removed.add(sta);
	    		  
	    	  }
	    	 
	    	  
	    	  
	    	
	      }
	      
	   
	      for(GameState j : items_to_be_removed) {
	    	  States.remove(j);
	      }
	      
	      States.add(state);
	    



	      	FileOutputStream fos;

			
			fos = new FileOutputStream(fileName);
			
			ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
			 for (Object obj : States) {

		         objOutputStream.writeObject(obj);
		        
		      }
		      objOutputStream.close();
		      
	
		      
		      
	      
	      
	
		
		
	}
	
	public static  void SavePlayerInfo(PlayerInfo info) throws IOException, ClassNotFoundException {
		
		ArrayList<PlayerInfo> infos = new ArrayList<PlayerInfo>();
		
		   FileInputStream fis = new FileInputStream(PlayersInfo_file);
		   
		   ObjectInputStream objfile = new ObjectInputStream(fis);
		   
		   try {
		         while (fis.available() != -1) {
		           
		        	 PlayerInfo temp_info = (PlayerInfo) objfile.readObject();
		        	 
		        	 infos.add(temp_info);
		        	 
		         }
		      } catch (EOFException ex) {
		       // ex.printStackTrace();
		      }
		      objfile.close();

		   
		      ArrayList<PlayerInfo> items_to_be_removed = new ArrayList<PlayerInfo>();
		      
		      for(PlayerInfo inf : infos) {
		    	  
		    	  if(inf.getPlayerName().equals(info.getPlayerName())) {
		    		  
		    		  items_to_be_removed.add(inf);
		    		  
		    	  }
		    	 
		    	  
		    	  
		    	
		      }
		      
		      for(PlayerInfo j : items_to_be_removed) {
		    	  infos.remove(j);
		      }
		      
		      
		      infos.add(info);
		      
		      FileOutputStream fos;

				
				fos = new FileOutputStream(PlayersInfo_file);
				
				ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
				 for (Object obj : infos) {

			         objOutputStream.writeObject(obj);
			        
			      }
			      objOutputStream.close();
		      
		
		
		
		   
		
	}
	
	 public static  void ResetFile(String fileName) throws IOException {
		 
		 if(fileName == "lastGameId.bin") {
			 try {
	               DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
	               out.writeInt(0);
	               out.close();
	           } catch (IOException ioe) {
	               System.err.println(ioe.getMessage());
	           }
			 
			 
			 
			 
			 
		 }
		 else if(fileName =="lastPlayerId.bin") {
			 try {
	               DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
	               out.writeInt(0);
	               out.close();
	           } catch (IOException ioe) {
	               System.err.println(ioe.getMessage());
	           }
			 
			 
		 }
		 else
		 {
		 
		 
	      FileOutputStream fos = new FileOutputStream(fileName);
	 
	      ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);


	      objOutputStream.close();

		 }
		 
		 
	   }

	

}
