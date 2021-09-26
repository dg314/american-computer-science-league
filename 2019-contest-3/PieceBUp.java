/**
 * PieceBUp
 *
 * @author David Grossman
 * @version 2019/3/3
 */

public class PieceBUp extends Piece {
    public PieceBUp() {
        super('B');
    }
    
    public int[] getCircleCoordinates(int row, int column, boolean flipped) {
        return new int[] {row-2, column};
    }
    
    public int[][] getFilledTilesCoordinates(int row, int column, boolean flipped) {
        return new int[][] {{row-1, column}};
    }
    
    public int[] newPossibleRows(int row, boolean flipped) {
        return new int[] {row, row-2};
    }
}
