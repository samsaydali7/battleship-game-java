/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

/**
 *
 * @author Asus
 */
public class PlayerNotCompatableException extends Exception{
    public PlayerNotCompatableException(){
        super();
    }
    public PlayerNotCompatableException(String message){
        super(message);
    }
    
    
}
