/**
 * 2019-2020 ACSL Contest #4 Senior Division - Patolli
 *
 * @author David Grossman
 * @version 1.0 2020/4/27
 */

import java.util.*;

public class Patolli {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        for (int i=1; i<=5; i++) {
            System.out.println("INPUT LINE " + i + ":");
            String[] input = keyboard.nextLine().trim().split(" ");
            
            int[] markers = new int[6];
            
            for (int j = 0; j < 6; j++) {
                markers[j] = Integer.parseInt(input[j]);
            }
            
            for (int j = 0; j < Integer.parseInt(input[6]); j++) {
                int roll = Integer.parseInt(input[7 + j]);
                int player = j % 2;
                int activeMarker = player * 3;
                
                for (int k = player * 3 + 1; k < player * 3 + 3; k++) {
                    if (markers[k] < markers[activeMarker]) {
                        activeMarker = k;
                    }
                }
                
                if (!locationCovered(markers, markers[activeMarker] + roll) && markers[activeMarker] + roll <= 52) {
                    markers[activeMarker] += roll;
                    
                    if (markers[activeMarker] == 52) {
                        markers[activeMarker] = 1000;
                    }
                    else if (isPrime(markers[activeMarker])) {
                        for (int m = 1; m <= 7; m++) {
                            if (locationCovered(markers, markers[activeMarker] + m) || m == 7) {
                                markers[activeMarker] += m - 1;
                                break;
                            }
                        }
                    }
                    else if (isSquareGreaterThanFour(markers[activeMarker])) {
                        for (int m = 1; m <= 7; m++) {
                            if (locationCovered(markers, markers[activeMarker] - m) || m == 7) {
                                markers[activeMarker] -= m - 1;
                                break;
                            }
                        }
                    }
                    else {
                        boolean horizontalMove = false;
                        
                        for (int m = markers[activeMarker] - roll; m < markers[activeMarker]; m++) {
                            if (isHorizontalMove(m)) {
                                horizontalMove = true;
                            }
                            else if (horizontalMove) {
                                if (markers[activeMarker] % roll > 0 && locationCovered(markers, markers[activeMarker] - markers[activeMarker] % roll)) {
                                    markers[activeMarker] -= roll;
                                }
                                else {
                                    markers[activeMarker] -= markers[activeMarker] % roll;
                                }
                                
                                break;
                            }
                        }
                    }
                }
                else {
                    boolean horizontalMove = false;
                        
                    for (int m = markers[activeMarker]; m < markers[activeMarker] + roll; m++) {
                        if (isHorizontalMove(m)) {
                            horizontalMove = true;
                        }
                        else if (horizontalMove) {
                            if (!locationCovered(markers, markers[activeMarker] + roll - markers[activeMarker] % roll) && markers[activeMarker] + roll - markers[activeMarker] % roll <= 52) {
                                markers[activeMarker] += roll - markers[activeMarker] % roll;
                            }
                            
                            break;
                        }
                    }
                }
                
                if (markers[activeMarker] == 52) {
                    markers[activeMarker] = 1000;
                }
            }
            
            int opponentTotal = 0;
            
            for (int n = 0; n < 3; n++) {
                if (markers[n] < 52) {
                    opponentTotal += markers[n];
                }
            }
            
            int playerTotal = 0;
            
            for (int n = 3; n < 6; n++) {
                if (markers[n] < 52) {
                    playerTotal += markers[n];
                }
            }
            
            System.out.println("" + opponentTotal + " " + playerTotal);
            
            System.out.println();
        }
    }
    
    public static boolean isPrime(int i) {
        return i == 2 || i == 3 || i == 5 || i == 7 || i == 11 || i == 13 || i == 17 || i == 19 || i == 23 || i == 29 || i == 31 || i == 37 || i == 39 || i == 41 || i == 43 || i == 47;
    }
    
    public static boolean isSquareGreaterThanFour(int i) {
        return i == 9 || i == 16 || i == 25 || i == 36 || i == 49;
    }
    
    public static boolean isHorizontalMove(int i) {
        return (i >= 3 && i <= 6) || (i >= 8 && i <= 11) || (i >= 13 && i <= 16) || (i >= 18 && i <= 21) || i == 26 || (i >= 31 && i <= 34) || (i >= 36 && i <= 39) || (i >= 41 && i <= 44) || (i >= 46 && i <= 49);
    }
    
    public static boolean locationCovered(int[] markers, int location) {
        for (int i = 0; i < markers.length; i++) {
            if (markers[i] == location) {
                return true;
            }
        }
        
        return false;
    }
}
