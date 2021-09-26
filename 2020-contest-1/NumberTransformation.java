/**
 * 2019-2020 ACSL Contest #1 Senior Division - Number Transformation
 *
 * @author David Grossman
 * @version 1.0 2019/12/8
 */

import java.util.*;

public class NumberTransformation {
    public static String transformNum(String n, int p) {
        String newN = "";
        
        for (int i = 0; i < n.length(); i++) {
            if (i < n.length() - p) {
                newN += (int)(n.charAt(i) + n.charAt(n.length() - p)) - 96;
            }
            else if (i > n.length() - p) {
                newN += Math.abs((int)(n.charAt(i) - n.charAt(n.length() - p)));
            }
            else {
                newN += numDistinctPrimeFactors(Long.parseLong(n));
            }
        }
        
        return newN;
    }
    
    public static int numDistinctPrimeFactors(long n) {
        int numFactors = 0;
        
        for (int i = 2; i <= Math.sqrt(n); i++) {
            boolean isDivis = false;
            
            while (n % i == 0) {
                isDivis = true;
                n /= i;
            }
            
            if (isDivis) {
                numFactors++;
            }
        }
        
        if (n > 1) {
            numFactors++;
        }
        
        return numFactors;
    }
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        for (int i = 1; i <= 5; i++) {
            System.out.print("INPUT LINE " + i + ": ");
            String input = keyboard.nextLine();
            
            String[] inputNums = input.trim().split(" ");
            
            System.out.println(transformNum(inputNums[0], Integer.parseInt(inputNums[inputNums.length - 1])));
            System.out.println();
        }
    }
}
