/**
 * PieceC
 *
 * @author David Grossman
 * @version 2019/3/3
 */

public class PieceC extends Piece {
    public PieceC() {
        super('C');
    }
    
    public int[] getCircleCoordinates(int row, int column, boolean flipped) {
        if (flipped) {
            return new int[] {row-1, column+1};
        }
        else {
            return new int[] {row+1, column+1};
        }
    }
    
    public int[][] getFilledTilesCoordinates(int row, int column, boolean flipped) {
        if (flipped) {
            return new int[][] {{row, column+1}};
        }
        else {
            return new int[][] {{row+1, column}};
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
