package BattleShipGamePackage;

import java.io.Serializable;

import InfoClasses.PlayerInfo;

public abstract class ComputerPlayer extends BattleshipPlayer {
	
	public static int sleepTime=0;
	
	protected AbstractComputerStrategy currentStrategy;

	
	 public ComputerPlayer() {
		
		 	super();
	
		 
	}
	 public void initPlayerinfo() {
		 	this.playerData = new PlayerInfo();
	        this.playerData.setPlayerName("Computer");
	        this.playerData.setPlayerID(0);
	        this.playerData.setMark(0.0);
	        this.playerData.setWins(0);
	        this.playerData.setPlayedGames(0);
		 
	 }
	
	@Override
    BattleshipMove GetNextMove() {
		
    	BattleshipMove bMove;
    	
        do{
            bMove = this.currentStrategy.getNextMove();
           }
        while((this.opGrid.getSquare(bMove.coords).getState()) != (SquareState.UNKNOWN));
        
        if( this instanceof longStrategyPlayer) {
        	
        	
         sleepTime = GameUtil.getSleepTimeRandom();
        
        System.out.println("iam gonna sleep for :" + sleepTime);
  
        
        
        
        try {
			Thread.sleep(sleepTime*1000);
		} catch (InterruptedException e) {
		
			
			System.out.println("IAMM INTERRUPTED : RANDOM STRATEGY");
			return new BattleshipMove("Pass");
		}
        
        
        }
        
        
       return bMove;
    }
	
	
	
	
	
	
	
	
	
	
	
}
