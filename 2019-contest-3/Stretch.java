/**
 * Stretch
 *
 * @author David Grossman
 * @version 2019/3/3
 */

import java.util.Scanner;

public class Stretch {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        for (int line=1; line<=5; line++) {
            System.out.println("INPUT LINE " + line + ":");
            String[] inputs = keyboard.nextLine().split(" ");
            
            int[] numbers = new int[inputs.length];
            
            for (int i=0; i<inputs.length; i++) {
                numbers[i] = Integer.parseInt(inputs[i]);
            }
            
            int[] blockedCellNumbers = new int[numbers[3]];
            
            for (int i=0; i<numbers[3]; i++) {
                blockedCellNumbers[i] = numbers[i+4];
            }
            
            int totalPieceTypes = 6;
            
            Piece[][] pieces = new Piece[][] {{new PieceA()}, {new PieceBDown(), new PieceBUp()}, {new PieceC()}, {new PieceD()}, {new PieceE()}};
            Grid grid = new Grid(numbers[0], numbers[1], numbers[2], blockedCellNumbers);
            
            String path = "";
            int consecutiveFails = 0;
            
            int currentPiece = 0;
            int currentType = 0;
            
            while (true) {
                int[] possibleRows = grid.getPossibleRows();
                int column = grid.getCurrentColumn();
                boolean pieceAdded = false;
                
                for (int row : possibleRows) {
                    int[] circleCoordinates = pieces[currentPiece][currentType].getCircleCoordinates(row, column, grid.isFlipped());
                    int[][] filledTilesCoordinates = pieces[currentPiece][currentType].getFilledTilesCoordinates(row, column, grid.isFlipped());
                    
                    boolean pieceCanFit = grid.circleCanBePlacedAt(row, column) && grid.circleCanBePlacedAt(circleCoordinates[0], circleCoordinates[1]);
                    
                    for (int[] filledTileCoordinates : filledTilesCoordinates) {
                        if (!pieceCanFit) {
                            break;
                        }
                        else {
                            pieceCanFit = grid.filledTileCanBePlacedAt(filledTileCoordinates[0], filledTileCoordinates[1]);
                        }
                    }
                    
                    if (pieceCanFit) {
                        grid.addCircleAt(row, column);
                        grid.addCircleAt(circleCoordinates[0], circleCoordinates[1]);
                        
                        for (int[] filledTileCoordinates : filledTilesCoordinates) {
                            grid.addFilledTileAt(filledTileCoordinates[0], filledTileCoordinates[1]);
                        }
                        
                        grid.update(pieces[currentPiece][currentType].newPossibleRows(row, grid.isFlipped()), pieces[currentPiece][currentType].newCurrentColumn(column));
                        
                        pieceAdded = true;
                        
                        break;
                    }
                }
                
                if (pieceAdded) {
                    path += pieces[currentPiece][currentType].getLetter();
                    currentType = 0;
                    currentPiece++;
                    
                    consecutiveFails = 0;
                    
                    System.out.println(grid.toString());
                }
                else {
                    currentType++;
                    
                    if (currentType >= pieces[currentPiece].length) {
                        currentType = 0;
                        currentPiece++;
                    }
                    
                    consecutiveFails++;
                }
                
                if (currentPiece >= pieces.length) {
                    currentPiece = 0;
                }
                
                if (grid.isFinished()) {
                    System.out.println(path);
                    break;
                }
                else if (consecutiveFails > totalPieceTypes) {
                    System.out.println(path + " (Not possible to finish.)");
                    break;
                }
            }
            
            System.out.println();
        }
    }
}