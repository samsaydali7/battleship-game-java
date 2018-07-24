/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

/**
 *
 * @author Samer2
 */
public class PlayersData {
    private String name;
    private int Id;

    public PlayersData() {

    }
    
    public String getName() {
        return name;
    }

    public int getId() {    return Id; }


    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
