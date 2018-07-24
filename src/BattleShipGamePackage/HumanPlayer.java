/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import InfoClasses.PlayerInfo;

/**
 *
 * @author Asus
 */
public class HumanPlayer extends BattleshipPlayer implements Serializable{       

    
    public HumanPlayer(){
        super();  
   
       
        
        
        
    }
    
    public void initPlayerinfo(String name) {
    	
    	 this.playerData = new PlayerInfo();
    	 this.playerData.setPlayerName(name);
         
         this.playerData.setMark(0.0);
         this.playerData.setWins(0);
         this.playerData.setPlayedGames(0);
         
         assignPlayerId();
    	
    }
    
    private void assignPlayerId() {
        try {
            try (DataInputStream in = new DataInputStream(new FileInputStream("lastPlayerId.bin"))) {
                int id = in.readInt() + 1;
             
                this.playerData.setPlayerID(id);
            }
            try {
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("lastPlayerId.bin")));
                out.writeInt(this.playerData.getPlayerID());
                out.close();
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            try {
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("lastPlayerId.bin")));
                out.writeInt(1);
                out.close();
            } catch (IOException iooe) {
                System.out.println(iooe.getMessage());
            }

        }
        
    }

    @Override
    BattleshipMove GetNextMove() {                                           //for console
       
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Coords");
        String input = in.nextLine();
        return new BattleshipMove(input);
    
        
    }
    

    
}
