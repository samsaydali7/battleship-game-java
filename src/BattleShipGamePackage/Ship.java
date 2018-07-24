/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Asus
 */


public class Ship implements Serializable{
    
     private int length;
    private LinkedList<String> parts;
    String location;
    
    public Ship(String loc,Alignment align,int length){
        this.length = length;
        this.parts = new LinkedList<>();
        
        int x=GameUtil.coordsToCol(loc);
        int y= GameUtil.coordsToRow(loc);
        
        switch(align) {
        
        
        case RIGHT:
        	
        	for(int i=0; i< length ; i++) {
        		this.parts.add(GameUtil.coordsToString(y, x+i));
        		
        		
        		
        	}
        	break;
        	
        case LEFT:
        	
        	for(int i=0; i <length; i++) {
        		
        		this.parts.add(GameUtil.coordsToString(y, x-i));
        		
        	}
        	break;
        	
        case TOP:
        	
        	for(int i=0; i <length; i++) {
        		
        		this.parts.add(GameUtil.coordsToString(y-i, x));
        		
        	}
        	break;
        	
        case BOTTOM:
        	
        	for(int i=0; i<length ; i++) {
        		
        		this.parts.add(GameUtil.coordsToString(y+i, x));
        		
        		
        	}
        	
        	break;
        
        
        
        
        
        }
        
        
    }
    
    boolean cheqIfPart(String part){
        boolean is = false;
            for (String part1 : parts) {
                if (part.equals(part1)){
                        is = true;
                        return is;
                }
            }
        return is;
    }
    
   

    public void setLength(int length) {
        this.length = length;
    }

    public void setParts(LinkedList<String> parts) {
        this.parts = parts;
    }

    public int getLength() {
        return length;
    }

    public LinkedList<String> getParts() {
        return parts;
    }
    
    
    
}
