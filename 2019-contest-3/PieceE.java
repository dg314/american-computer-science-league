/**
 * PieceE
 *
 * @author David Grossman
 * @version 2019/3/3
 */

public class PieceE extends Piece {
    public PieceE() {
        super('E');
    }
    
    public int[] getCircleCoordinates(int row, int column, boolean flipped) {
        if (flipped) {
            return new int[] {row-1, column+2};
        }
        else {
            return new int[] {row+1, column+2};
        }
    }
    
    public int[][] getFilledTilesCoordinates(int row, int column, boolean flipped) {
        if (flipped) {
            return new int[][] {{row, column+1}, {row-1, column+1}};
        }
        else {
            return new int[][] {{row, column+1}, {row+1, column+1}};
        }
    }
    
    public int[] newPossibleRows(int row, boolean flipped) {
        if (flipped) {
            return new int[] {row-1};
        }
        else {
            return new int[] {row+1};
        }
    }
}
