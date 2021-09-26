/**
 * Grid
 *
 * @author David Grossman
 * @version 2019/3/3
 */

import java.util.Arrays;

public class Grid {
    public enum Tile { EMPTY, FILLED, CIRCLE, BLOCKED }
    
    private Tile[][] grid;
    private int currentColumn;
    private int[] possibleRows;
    private boolean flipped;
    
    public Grid(int rows, int columns, int startingCellNumber, int[] blockedCellNumbers) {
        this.grid = new Tile[rows][columns];
        
        for (Tile[] row : grid) {
            Arrays.fill(row, Tile.EMPTY);
        }
        
        this.flipped = (startingCellNumber-1)%columns != 0;
        
        this.currentColumn = 0;
        this.possibleRows = new int[] {(startingCellNumber-1)/columns};
        
        for (int blockedCellNumber : blockedCellNumbers) {
            if (flipped) {
                grid[(blockedCellNumber-1)/columns][columns-1-(blockedCellNumber-1)%columns] = Tile.BLOCKED;
            }
            else {
                grid[(blockedCellNumber-1)/columns][(blockedCellNumber-1)%columns] = Tile.BLOCKED;
            }
        }
    }
    
    public boolean tileIsOnGrid(int row, int column) {
        return row >= 0 && row < grid.length && column >= 0 && column < grid[0].length;
    }
    
    public boolean tileIsEmpty(int row, int column) {
        return tileIsOnGrid(row, column) && grid[row][column] == Tile.EMPTY;
    }
    
    public boolean tileIsEmptyOrBlocked(int row, int column) {
        return tileIsOnGrid(row, column) && (grid[row][column] == Tile.EMPTY || grid[row][column] == Tile.BLOCKED);
    }
    
    public boolean tileIsEmptyBlockedOrCircle(int row, int column) {
        return tileIsOnGrid(row, column) && grid[row][column] != Tile.FILLED;
    }
    
    public boolean filledTileCanBePlacedAt(int row, int column) {
        return column != 0 && column != grid[0].length-1 && tileIsEmpty(row, column) && tileIsEmptyOrBlocked(row, column-1);
    }
    
    public boolean circleCanBePlacedAt(int row, int column) {
        return tileIsEmpty(row, column) && (column == 0 || tileIsEmptyBlockedOrCircle(row, column-1));
    }
    
    public int getCurrentColumn() {
        return currentColumn;
    }
    
    public int[] getPossibleRows() {
        return possibleRows;
    }
    
    public void addCircleAt(int row, int column) {
        if (tileIsEmpty(row, column)) {
            grid[row][column] = Tile.CIRCLE;
        }
    }
    
    public void addFilledTileAt(int row, int column) {
        if (tileIsEmpty(row, column)) {
            grid[row][column] = Tile.FILLED;
        }
    }
    
    public void update(int[] newPossibleRows, int newCurrentColumn) {
        possibleRows = newPossibleRows;
        currentColumn = newCurrentColumn;
    }
    
    public boolean isFlipped() {
        return flipped;
    }
    
    public boolean isFinished() {
        return currentColumn >= grid[0].length;
    }
    
    public String toString() {
        String s = "";
        
        for (Tile[] row : grid) {
            for (Tile tile : row) {
                if (tile == Tile.BLOCKED) {
                    s += "B";
                }
                else if (tile == Tile.FILLED) {
                    s += "F";
                }
                else if (tile == Tile.CIRCLE) {
                    s += "C";
                }
                else {
                    s += "_";
                }
            }
            
            s += "\n";
        }
        
        return s;
    }
}
