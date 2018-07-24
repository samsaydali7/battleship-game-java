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
public class BattleshipGameSettings {

    private static int gridSize = 10;
    private static int shipsCount = 5;
    private static boolean showOpGrid = false;
    private static int css = 2;

    public static int getCss() {
        return css;
    }

    public static void setCss(int css) {
        BattleshipGameSettings.css = css;
    }

    public static int getGridSize() {
        return gridSize;
    }

    public static void setGridSize(int gridSize) {
        BattleshipGameSettings.gridSize = gridSize;
    }

    public static int getShipsCount() {
        return shipsCount;
    }

    public static void setShipsCount(int shipsCount) {
        BattleshipGameSettings.shipsCount = shipsCount;
    }

    public static boolean isShowOpGrid() {
        return showOpGrid;
    }

    public static void setShowOpGrid(boolean showOpGrid) {
        BattleshipGameSettings.showOpGrid = showOpGrid;
    }

}
