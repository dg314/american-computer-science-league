/**
 * 2019-2020 ACSL Contest #3 Senior Division - Veitch
 *
 * @author David Grossman
 * @version 1.0 2020/03/08
 */

import java.util.*;

public class Veitch {
    public static boolean[][] inputToGrid(int input) {
        boolean[][] grid = new boolean[4][4];
        
        for (int r = 3; r >= 0; r--) {
            for (int c = 3; c >= 0; c--) {
                grid[r][c] = (input % 2 == 1);
                
                input /= 2;
            }
        }
        
        return grid;
    }
    
    public static String gridToExpression(boolean[][] grid) {
        String expression = "";
        
        boolean start = true;
        
        while (true) {
            boolean finished = true;
            
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (grid[r][c]) {
                        finished = false;
                    }
                }  
            }
            
            if (finished) {
                return expression;
            }
            
            if (start) {
                start = false;
            }
            else {
                expression += "+";
            }
            
            expression += findLargestGroup(grid);
        }
    }
    
    public static String findLargestGroup(boolean[][] grid) {
        boolean[] rows = new boolean[4];
        boolean[] cols = new boolean[4];
        boolean[][] blocks = new boolean[4][4];
        boolean[][] hPairs = new boolean[4][4];
        boolean[][] vPairs = new boolean[4][4];
        
        Arrays.fill(rows, true);
        Arrays.fill(cols, true);
        
        for (int r = 0; r < 4; r++) {
            Arrays.fill(blocks[r], true);
            Arrays.fill(hPairs[r], true);
            Arrays.fill(vPairs[r], true);
        }
        
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (!grid[r][c]) {
                    rows[r] = false;
                    cols[c] = false;
                    
                    for (int i = 0; i < 2; i++) {
                        hPairs[r][(c - i + 4) % 4] = false;
                        vPairs[(r - i + 4) % 4][c] = false;
                        
                        for (int j = 0; j < 2; j++) {
                            blocks[(r - i + 4) % 4][(c - j + 4) % 4] = false;
                        }
                    }
                }
            }  
        }
        
        if (rows[0] && rows[1]) {
            removeRow(grid, 0);
            removeRow(grid, 1);
            return "B";
        }
        else if (rows[1] && rows[2]) {
            removeRow(grid, 1);
            removeRow(grid, 2);
            return "D";
        }
        else if (rows[2] && rows[3]) {
            removeRow(grid, 2);
            removeRow(grid, 3);
            return "~B";
        }
        else if (cols[0] && cols[1]) {
            removeCol(grid, 0);
            removeCol(grid, 1);
            return "A";
        }
        else if (cols[1] && cols[2]) {
            removeCol(grid, 1);
            removeCol(grid, 2);
            return "C";
        }
        else if (cols[2] && cols[3]) {
            removeCol(grid, 2);
            removeCol(grid, 3);
            return "~A";
        }
        else if (rows[0] && rows[3]) {
            removeRow(grid, 0);
            removeRow(grid, 3);
            return "~D";
        }
        else if (cols[0] && cols[3]) {
            removeCol(grid, 0);
            removeCol(grid, 3);
            return "~C";
        }
        else if (rows[0]) {
            removeRow(grid, 0);
            return "B~D";
        }
        else if (rows[1]) {
            removeRow(grid, 1);
            return "BD";
        }
        else if (rows[2]) {
            removeRow(grid, 2);
            return "~BD";
        }
        else if (rows[3]) {
            removeRow(grid, 3);
            return "~B~D";
        }
        else if (cols[0]) {
            removeCol(grid, 0);
            return "A~C";
        }
        else if (cols[1]) {
            removeCol(grid, 1);
            return "AC";
        }
        else if (cols[2]) {
            removeCol(grid, 2);
            return "~AC";
        }
        else if (cols[3]) {
            removeCol(grid, 3);
            return "~A~C";
        }
        else if (blocks[0][0]) {
            removeBlock(grid, 0, 0);
            return "AB";
        }
        else if (blocks[0][1]) {
            removeBlock(grid, 0, 1);
            return "BC";
        }
        else if (blocks[0][2]) {
            removeBlock(grid, 0, 2);
            return "~AB";
        }
        else if (blocks[1][0]) {
            removeBlock(grid, 1, 0);
            return "AD";
        }
        else if (blocks[1][1]) {
            removeBlock(grid, 1, 1);
            return "CD";
        }
        else if (blocks[1][2]) {
            removeBlock(grid, 1, 2);
            return "~AD";
        }
        else if (blocks[2][0]) {
            removeBlock(grid, 2, 0);
            return "A~B";
        }
        else if (blocks[2][1]) {
            removeBlock(grid, 2, 1);
            return "~BC";
        }
        else if (blocks[2][2]) {
            removeBlock(grid, 2, 2);
            return "~A~B";
        }
        else if (blocks[0][3]) {
            removeBlock(grid, 0, 3);
            return "B~C";
        }
        else if (blocks[1][3]) {
            removeBlock(grid, 1, 3);
            return "~CD";
        }
        else if (blocks[2][3]) {
            removeBlock(grid, 2, 3);
            return "~B~C";
        }
        else if (blocks[3][0]) {
            removeBlock(grid, 3, 0);
            return "A~D";
        }
        else if (blocks[3][1]) {
            removeBlock(grid, 3, 1);
            return "C~D";
        }
        else if (blocks[3][2]) {
            removeBlock(grid, 3, 2);
            return "~A~D";
        }
        else if (blocks[3][3]) {
            removeBlock(grid, 3, 3);
            return "~C~D";
        }
        else if (hPairs[0][0]) {
            removeHPair(grid, 0, 0);
            return "AB~D";
        }
        else if (hPairs[0][1]) {
            removeHPair(grid, 0, 1);
            return "BC~D";
        }
        else if (hPairs[0][2]) {
            removeHPair(grid, 0, 2);
            return "~AB~D";
        }
        else if (hPairs[1][0]) {
            removeHPair(grid, 1, 0);
            return "ABD";
        }
        else if (hPairs[1][1]) {
            removeHPair(grid, 1, 1);
            return "BCD";
        }
        else if (hPairs[1][2]) {
            removeHPair(grid, 1, 2);
            return "~ABD";
        }
        else if (hPairs[2][0]) {
            removeHPair(grid, 2, 0);
            return "A~BD";
        }
        else if (hPairs[2][1]) {
            removeHPair(grid, 2, 1);
            return "~BCD";
        }
        else if (hPairs[2][2]) {
            removeHPair(grid, 2, 2);
            return "~A~BD";
        }
        else if (hPairs[3][0]) {
            removeHPair(grid, 3, 0);
            return "A~B~D";
        }
        else if (hPairs[3][1]) {
            removeHPair(grid, 3, 1);
            return "~BC~D";
        }
        else if (hPairs[3][2]) {
            removeHPair(grid, 3, 2);
            return "~A~B~D";
        }
        else if (vPairs[0][0]) {
            removeVPair(grid, 0, 0);
            return "AB~C";
        }
        else if (vPairs[1][0]) {
            removeVPair(grid, 1, 0);
            return "A~CD";
        }
        else if (vPairs[2][0]) {
            removeVPair(grid, 2, 0);
            return "A~B~C";
        }
        else if (vPairs[0][1]) {
            removeVPair(grid, 0, 1);
            return "ABC";
        }
        else if (vPairs[1][1]) {
            removeVPair(grid, 1, 1);
            return "ACD";
        }
        else if (vPairs[2][1]) {
            removeVPair(grid, 2, 1);
            return "A~BC";
        }
        else if (vPairs[0][2]) {
            removeVPair(grid, 0, 2);
            return "~ABC";
        }
        else if (vPairs[1][2]) {
            removeVPair(grid, 1, 2);
            return "~ACD";
        }
        else if (vPairs[2][2]) {
            removeVPair(grid, 2, 2);
            return "~A~BC";
        }
        else if (vPairs[0][3]) {
            removeVPair(grid, 0, 3);
            return "~AB~C";
        }
        else if (vPairs[1][3]) {
            removeVPair(grid, 1, 3);
            return "~A~CD";
        }
        else if (vPairs[2][3]) {
            removeVPair(grid, 2, 3);
            return "~A~B~C";
        }
        else if (hPairs[0][3]) {
            removeHPair(grid, 0, 3);
            return "B~C~D";
        }
        else if (hPairs[1][3]) {
            removeHPair(grid, 1, 3);
            return "B~CD";
        }
        else if (hPairs[2][3]) {
            removeHPair(grid, 2, 3);
            return "~B~CD";
        }
        else if (hPairs[3][3]) {
            removeHPair(grid, 3, 3);
            return "~B~C~D";
        }
        else if (vPairs[3][0]) {
            removeVPair(grid, 3, 0);
            return "A~C~D";
        }
        else if (vPairs[3][1]) {
            removeVPair(grid, 3, 1);
            return "AC~D";
        }
        else if (vPairs[3][2]) {
            removeVPair(grid, 3, 2);
            return "~AC~D";
        }
        else if (vPairs[3][3]) {
            removeVPair(grid, 3, 3);
            return "~A~C~D";
        }
        else if (grid[0][0]) {
            removeCell(grid, 0, 0);
            return "AB~C~D";
        }
        else if (grid[0][1]) {
            removeCell(grid, 0, 1);
            return "ABC~D";
        }
        else if (grid[0][2]) {
            removeCell(grid, 0, 2);
            return "~ABC~D";
        }
        else if (grid[0][3]) {
            removeCell(grid, 0, 3);
            return "~AB~C~D";
        }
        else if (grid[1][0]) {
            removeCell(grid, 1, 0);
            return "AB~CD";
        }
        else if (grid[1][1]) {
            removeCell(grid, 1, 1);
            return "ABCD";
        }
        else if (grid[1][2]) {
            removeCell(grid, 1, 2);
            return "~ABCD";
        }
        else if (grid[1][3]) {
            removeCell(grid, 1, 3);
            return "~AB~CD";
        }
        else if (grid[2][0]) {
            removeCell(grid, 2, 0);
            return "A~B~CD";
        }
        else if (grid[2][1]) {
            removeCell(grid, 2, 1);
            return "A~BCD";
        }
        else if (grid[2][2]) {
            removeCell(grid, 2, 2);
            return "~A~BCD";
        }
        else if (grid[2][3]) {
            removeCell(grid, 2, 3);
            return "~A~B~CD";
        }
        else if (grid[3][0]) {
            removeCell(grid, 3, 0);
            return "A~B~C~D";
        }
        else if (grid[3][1]) {
            removeCell(grid, 3, 1);
            return "A~BC~D";
        }
        else if (grid[3][2]) {
            removeCell(grid, 3, 2);
            return "~A~BC~D";
        }
        else if (grid[3][3]) {
            removeCell(grid, 3, 3);
            return "~A~B~C~D";
        }
        
        return "";
    }
    
    public static void removeRow(boolean[][] grid, int r) {
        for (int c = 0; c < 4; c++) {
            grid[r][c] = false;
        }
    }
    
    public static void removeCol(boolean[][] grid, int c) {
        for (int r = 0; r < 4; r++) {
            grid[r][c] = false;
        }
    }
    
    public static void removeBlock(boolean[][] grid, int r, int c) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                grid[(r + i) % 4][(c + j) % 4] = false;
            }
        }
    }
    
    public static void removeHPair(boolean[][] grid, int r, int c) {
        for (int i = 0; i < 2; i++) {
            grid[r][(c + i) % 4] = false;
        }
    }
    
    public static void removeVPair(boolean[][] grid, int r, int c) {
        for (int i = 0; i < 2; i++) {
            grid[(r + i) % 4][c] = false;
        }
    }
    
    public static void removeCell(boolean[][] grid, int r, int c) {
        grid[r][c] = false;
    }
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        for (int i = 1; i <= 5; i++) {
            System.out.println("INPUT LINE " + i + ":");
            int input = Integer.parseInt(keyboard.nextLine(), 16);
            
            boolean[][] grid = inputToGrid(input);
            
            System.out.println("Output: " + gridToExpression(grid));
            System.out.println();
        }
    }
}
