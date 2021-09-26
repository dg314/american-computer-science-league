/**
 * PieceA
 *
 * @author David Grossman
 * @version 2019/3/3
 */

public class PieceA extends Piece {
    public PieceA() {
        super('A');
    }
    
    public int[] getCircleCoordinates(int row, int column, boolean flipped) {
        return new int[] {row, column+2};
    }
    
    public int[][] getFilledTilesCoordinates(int row, int column, boolean flipped) {
        return new int[][] {{row, column+1}};
    }
    
    public int[] newPossibleRows(int row, boolean flipped) {
        return new int[] {row};
    }
}
