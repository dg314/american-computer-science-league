/**
 * 2019-2020 ACSL Contest #2 Senior Division - Difference Factor
 *
 * @author David Grossman
 * @version 1.0 2020/02/02
 */

import java.util.*;

public class DifferenceFactor {
    public static int lengthOfCommonSubstrings(String lineOne, String lineTwo) {
        int maxLength = 0;
        int startLineOne = -1;
        int startLineTwo = -1;
        
        for (int i = 0; i < lineOne.length(); i++) {
            for (int j = 0; j < lineTwo.length(); j++) {
                int k = 0;
                
                while (i + k < lineOne.length() && j + k < lineTwo.length() && lineOne.charAt(i + k) == lineTwo.charAt(j + k)) {
                    k++;
                }

                if (k > 0 && ((k == maxLength && lineOne.substring(i, i + maxLength).compareTo(lineOne.substring(startLineOne, startLineOne + maxLength)) < 0) || k > maxLength)) {
                    maxLength = k;
                    startLineOne = i;
                    startLineTwo = j;
                }
            }
        }
        
        int totalLength = maxLength;
        
        if (startLineOne > -1) {
            String A = lineOne.substring(0, startLineOne);
            String B = lineOne.substring(startLineOne + maxLength);
            String C = lineTwo.substring(0, startLineTwo);
            String D = lineTwo.substring(startLineTwo + maxLength);
            
            totalLength += lengthOfCommonSubstrings(A, C) + lengthOfCommonSubstrings(B, D);
        }
        
        return totalLength;
    }
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        for (int i = 1; i <= 5; i++) {
            System.out.println("INPUT LINE " + i + ":");
            String lineOne = keyboard.nextLine().replaceAll("[^a-zA-Z]", "").toUpperCase();
            String lineTwo = keyboard.nextLine().replaceAll("[^a-zA-Z]", "").toUpperCase();
            
            System.out.println("Output: " + lengthOfCommonSubstrings(lineOne, lineTwo));
            System.out.println();
        }
    }
}
