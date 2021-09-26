/**
 * PieceD
 *
 * @author David Grossman
 * @version 2019/3/3
 */

public class PieceD extends Piece {
    public PieceD() {
        super('D');
    }
    
    public int[] getCircleCoordinates(int row, int column, boolean flipped) {
        if (flipped) {
            return new int[] {row-2, column+1};
        }
        else {
            return new int[] {row+2, column+1};
        }
    }
    
    public int[][] getFilledTilesCoordinates(int row, int column, boolean flipped) {
        if (flipped) {
            return new int[][] {{row-1, column}, {row-2, column}};
        }
        else {
            return new int[][] {{row, column+1}, {row+1, column+1}};
        }
    }
    
    public int[] newPossibleRows(int row, boolean flipped) {
        if (flipped) {
            return new int[] {row-2};
        }
        else {
            return new int[] {row+2};
        }
    }
}
