/**
 * Piece
 *
 * @author David Grossman
 * @version 2019/3/3
 */

public abstract class Piece {
    private char letter;
    
    public Piece(char letter) {
        this.letter = letter;
    }
    
    public abstract int[] getCircleCoordinates(int row, int column, boolean flipped);
    
    public abstract int[][] getFilledTilesCoordinates(int row, int column, boolean flipped);
    
    public abstract int[] newPossibleRows(int row, boolean flipped);
    
    public int newCurrentColumn(int column) {
        return getCircleCoordinates(0, column, false)[1]+1;
    }
    
    public char getLetter() {
        return letter;
    }
}
