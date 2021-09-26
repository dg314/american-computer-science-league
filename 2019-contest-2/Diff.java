/**
 * 2018-2019 ACSL Contest #2 Senior Division - Diff
 *
 * @author David Grossman
 * @version 1.0 2019/1/26
 */

import java.util.Scanner;

public class Diff {
    public static String diff(String A, String B) {
        String result = "";
        
        int startIndex = 0;
        int endIndex = A.indexOf(" ");
        
        while (true) {
            String currentWord = A.substring(startIndex);
            
            if (endIndex != -1) {
                currentWord = A.substring(startIndex, endIndex);
            }
            
            if (B.indexOf(currentWord) != -1) {
                if (result.length() > 0) {
                    result += " ";
                }
                
                result += currentWord;
                B = B.substring(0, B.indexOf(currentWord)) + B.substring(B.indexOf(currentWord)+currentWord.length());
            }
            
            if (endIndex == -1) {
                break;
            }
            
            startIndex = endIndex+1;
            endIndex = A.indexOf(" ", startIndex);
        }
        
        return result;
    }
    
    public static String combine(String firstCommonString, String secondCommonString) {
        String result = "";
        
        for (int c=0; c<firstCommonString.length(); c++) {
            char currentChar = firstCommonString.charAt(c);
            
            if (secondCommonString.length() == 0) {
                break;
            }
            
            if (currentChar != ' ' && secondCommonString.indexOf(currentChar) != -1) {
                result += currentChar;
                secondCommonString = secondCommonString.substring(secondCommonString.indexOf(currentChar)+1);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        for (int i=1; i<=5; i++) {
            System.out.println("DATA SET " + i + "\n");
            
            System.out.println("Enter String A: ");
            String A = keyboard.nextLine();
            
            System.out.println("Enter String B: ");
            String B = keyboard.nextLine();
            
            String commonString = combine(diff(A, B), diff(B, A));
            
            System.out.println("\nOutput:");
            
            if (commonString.length() == 0) {
                System.out.println("NONE");
            }
            else {
                System.out.println(commonString);
            }
            
            System.out.println("\n");
        }
    }
}
