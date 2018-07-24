/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import InfoClasses.PlayerInfo;
import Threads.MineThread;

/**
 *
 * @author Asus
 */

public abstract class BattleshipPlayer extends Player {
    
        
                                                              
   
    Grid playerGrid;
    Grid opGrid;
    String result;
    LinkedList<Ship> ships;
    int shipsCount;
    protected MineThread mineThread;
    protected PlayerInfo playerData;
    
    
    
    BattleshipPlayer(){
    	
        playerGrid = new Grid(BattleshipGameSettings.getGridSize(),BattleshipGameSettings.getGridSize());
        
        opGrid = new Grid(BattleshipGameSettings.getGridSize(),BattleshipGameSettings.getGridSize());
        
        ships = new LinkedList<Ship>();
        
        shipsCount = 0;
    }
    
    public MineThread getMineThread() {
        return mineThread;
    }
    
    
    
    public Grid getPlayerGrid() {
		return playerGrid;
	}

	@Override
    void SupscripeTo(Iplayable game) throws PlayerNotCompatableException {
        if(!(game instanceof BattleshipGame)){
            throw new PlayerNotCompatableException();
        }
        this.currentGame = game;
        game.Subscribe(this);
    }

    @Override
    void LeaveGame() {
        currentGame.LeavGame(this);
    }
    
    void EndGame(){
        ((BattleshipGame)this.currentGame).End();
    }
    
    abstract BattleshipMove GetNextMove();
    
    public String MineHitConfig(int i, int j, LinkedList<int[]> hits) {
        String note = "Mine Hit";
        int[][] directions = {{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1}};
        for (int[] d : directions) {
            if (GameUtil.isValidCoords(i + d[0], j + d[1])) {
            if (this.playerGrid.getSquare(i + d[0], j + d[1]).getState() == SquareState.SHIP_PART) {
                this.playerGrid.getSquare(i + d[0], j + d[1]).setState(SquareState.HIT);
                int [] a = {i + d[0], j + d[1]};
                hits.add(a);
                note = "Mine Hit : Parts Destruction";
                for (Ship ship : ships) {
                    if (ship.cheqIfPart(GameUtil.coordsToString(i + d[0], j + d[1]))) {
                        ship.setLength(ship.getLength() - 1);
                        if (ship.getLength() == 0) {
                            note = "Mine Hit : Full Ships Destruction";
                            this.shipsCount--;
                            if (this.shipsCount == 0) {
                                note = "All Ships Distroyed";
                            }
                            break;
                        }
                    }
                }
            }
        }
        }
        return note;
    }
    
    BattleshipMoveResult AcceptPlayerMove(BattleshipMove move){
    	
        Square st=this.playerGrid.getSquare(move.coords);
        String note = "Water";
        
        if(st.getState() == SquareState.WATER) {
        	st.setState(SquareState.MISS);
        }
        
        if (st.getState() == SquareState.MINE) {
            st.setState(SquareState.MINE_HIT);
            LinkedList<int[]> hits = new LinkedList<>();
            note = MineHitConfig(GameUtil.coordsToRow(move.getCoords()), GameUtil.coordsToCol(move.getCoords()),hits);
            this.mineThread.interrupt();
            return new BattleshipMoveResult(move.getCoords(), st.getState(), note,hits);
        }
        
        
        if(st.getState() == SquareState.SHIP_PART){
            st.setState(SquareState.HIT);
            note = "Part Destruction";
            for (Ship ship : ships) {
                if(ship.cheqIfPart(move.coords)){
                    ship.setLength(ship.getLength()-1);
                    if(ship.getLength() == 0){
                        note = "Full Ship Destruction";
                        this.shipsCount--;
                        if(this.shipsCount == 0){
                            note = "All Ships Distroyed";
                        }
                    }
                    break;
                }
            }
            
        }
        return new BattleshipMoveResult(move.coords,st.getState(),note);
    }
    void NotifyPlayer(BattleshipMoveResult result){
        opGrid.SetState(GameUtil.coordsToRow(result.coords),GameUtil.coordsToCol(result.coords), result.squareState);
        
        
        String string;
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
        string = sd.format(date)+ " " + this.playerData.getPlayerName() + "(" + GameUtil.coordsToRow(result.getCoords())+ "," +GameUtil.coordsToCol(result.getCoords())+") " + result.getNotfication();
        ((BattleshipGame)this.currentGame).getSteps().add(string);
        
        
        this.result = result.notfication;
        if(this.result.equals("Part Destruction")&&(this instanceof ComputerPlayer)) {
        	
        	
        	((ComputerPlayer)this).currentStrategy.setPush(true);
        }
        if(this.result.equals("All Ships Distroyed")){
        	
        	if (this instanceof ComputerPlayer) {
        		Game game =  (Game)this.currentGame;
        		game.SetWinner("You Lose!");
        		
        	}
        	else if(this instanceof HumanPlayer) {
        		Game game = (Game)this.currentGame;
        		game.SetWinner("you Win!!");
        	}
        	
            EndGame();
        }
        
        
    }
    
    void  Init(){
        
        for (int i = 0; i < BattleshipGameSettings.getGridSize(); i++) {
            for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {
            	
                this.playerGrid.SetState(i, j, SquareState.WATER);
                
            }
        }
        
       
        
        
        
     
        
        
       
            
        
        

    }
    
    public void addShip(Ship ship) {
    	
    	ships.add(ship);
    	shipsCount++;
    	for (String string : ship.getParts()) {
        	
        	
            this.playerGrid.SetState(GameUtil.coordsToRow(string), GameUtil.coordsToCol(string), SquareState.SHIP_PART);
            
            
        }
    	
    	
    	
    }
        
        
        
    
    public void printGrid(){
            for (int i = 0; i < BattleshipGameSettings.getGridSize(); i++) {
                for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {
                    System.out.print(this.playerGrid.getSquares()[i][j].getState() + " ");
                }
                System.out.println("\n");
            }
    }
    void printOpGrid(){
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(this.opGrid.getSquares()[i][j].getState() + " ");
                }
                System.out.println("\n");
            }
    }
	public int getShipsCount() {
		return shipsCount;
	}
	
	public String getSquareAt(int i,int j) {
		
		return playerGrid.getSquare(i, j).getState().toString();
		
		
	}
	
	public Validity IsValid(Alignment align, int x, int y ,int size) {
		
		int i=0;
		
		switch(align) {
		
		
		case RIGHT:
			for( i=0; i <size; i++) {
			if(x+i <BattleshipGameSettings.getGridSize()) {	
				
				if( getSquareAt(y,x+i)!="Water") {
					
					
					return new Validity(i,false);
					
				}
			}
			else
				return new Validity(i,false);
				
				
			}
			
			return new Validity(i,true);
			
		case LEFT:
			
			for(i=0; i <size; i++) {
				
			if(x-i>=0) {	
				if( getSquareAt(y,x-i)!="Water") {
					
					return new Validity(i,false);
					
				}
				
			}
			else
				return new Validity(i,false);
				
			}
			
			return new Validity(i,true);
			
		
		case TOP:
			
			for( i=0; i <size; i++) {
			   
			if(y-i >=0) {
				if( getSquareAt(y-i,x)!="Water") {
					
					return new Validity(i,false);
					
				}
				}
			else
				return new Validity(i,false);
				
				
			}
			
			return new Validity(i,true);
			
			
		case BOTTOM:
			
			for( i=0; i <size; i++) {
				
				if(y+i<BattleshipGameSettings.getGridSize()){
				if( getSquareAt(y+i,x)!="Water") {
					
					return new Validity(i,false);
					
				}
				}
				else
					return new Validity(i,false);
				
				
			}
			
			return new Validity(i,true);
			
			
		default:
			 return new Validity(i,true);
		
		
		
		
		}
		
		
		
		
	}
    
    public void setMine() {
    	 mineThread = new MineThread(new Mine(playerGrid));
    	
    	
    }
    public PlayerInfo getPlayerData() {
        return playerData;
    }

	public void setPlayerData(PlayerInfo playerData) {
		this.playerData = playerData;
	}
    
    
  
}



 
