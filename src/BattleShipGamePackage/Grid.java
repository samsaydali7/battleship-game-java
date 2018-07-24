/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class Grid implements Serializable{
    private Square[][] squares;
    public Square getSquare(String adress){
        int i , j;
        i = GameUtil.coordsToRow(adress);
        j = GameUtil.coordsToCol(adress);
        return this.squares[i][j];
    }
    public Square getSquare(int i,int j){
    	
        return this.squares[i][j];
    	
    	
    }
    public void SetState(int i ,int j,SquareState state){
    	
    	
        this.squares[i][j].setState(state);
        
    }

    public Square[][] getSquares() {
        return squares;
    }
    public Grid(int m,int n) {
        this.squares = new Square[m][n];        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                this.squares[i][j] = new Square();
            }
        }
        
    }
    public Grid() {
        this.squares = new Square[BattleshipGameSettings.getGridSize()][BattleshipGameSettings.getGridSize()];
        for (int i = 0; i < BattleshipGameSettings.getGridSize(); i++) {
            for (int j = 0; j <BattleshipGameSettings.getGridSize() ; j++) {
                this.squares[i][j] = new Square();
            }
        }
    }
    
    
}
