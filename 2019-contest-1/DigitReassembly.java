/**
 * Senior Division - Digit Reassembly
 *
 * @author David Grossman
 * @version 12/14/18
 */

import java.util.Scanner;

public class DigitReassembly {
    public static long sumOfNDigitNumbers(String s) {
        String number = s.substring(0, s.indexOf(' '));
        int n = Integer.parseInt(s.substring(s.indexOf(' ')+1));
        
        while (number.length()%n != 0) {
            number += "0";
        }
        
        long sum = 0;
        
        for (int i=0; i<number.length(); i+=n) {
            sum += Long.parseLong(number.substring(i, i+n));
        }
        
        return sum;
    }
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        for (int i=1; i<=5; i++) {
            System.out.print("Input " + i + ": ");
            String input = keyboard.nextLine();
            System.out.println(i + ".  " + sumOfNDigitNumbers(input));
        }
    }
}
