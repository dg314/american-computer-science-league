/**
 * 2018-2019 ACSL Contest #4 Senior Division - Prefix Evaluation
 *
 * @author David Grossman
 * @version 1.0 2019/3/31
 */

import java.util.*;

public class PrefixEvaluation {
    public static ArrayList<String> inputToArray(String input) {
        ArrayList<String> arr = new ArrayList<String>(Arrays.asList(input.split(" ")));
        arr.removeAll(Arrays.asList("", null));
        
        return arr;
    }
    
    public static ArrayList<String> evaluatePrefixExpression(ArrayList<String> exp) {
        if (exp.size() == 1) {
            return exp;
        }
        
        for (int i=0; i<exp.size()-1; i++) {
            if (isOperand(exp.get(i))) {
                boolean willBeCalculated = true;
                
                for (int j=1; j<=getNumOperands(exp.get(i)); j++) {
                    if (isOperand(exp.get(i+j))) {
                        willBeCalculated = false;
                        break;
                    }
                }
                
                if (willBeCalculated) {
                    int result = calculateOperation(exp.get(i), exp.subList(i+1, i+1+getNumOperands(exp.get(i))));
                    
                    int numToRemove = getNumOperands(exp.get(i)) + 1;
                    
                    for (int j=0; j<numToRemove; j++) {
                        exp.remove(i);
                    }
                    
                    exp.add(i, "" + result);
                    
                    break;
                }
            }
        }
        
        return evaluatePrefixExpression(exp);
    }
    
    public static int getNumOperands(String operation) {
        switch (operation) {
            case "|":
                return 1;
            case "+":
            case "-":
            case "*":
                return 2;
            case "@":
            case ">":
                return 3;
            default:
                return 0;
        }
    }
    
    public static boolean isOperand(String operation) {
        return getNumOperands(operation) > 0;
    }
    
    public static int calculateOperation(String operation, List<String> operands) {
        int[] nums = new int[getNumOperands(operation)];
        
        for (int i=0; i<operands.size(); i++) {
            nums[i] = Integer.parseInt(operands.get(i));
        }
        
        switch (operation) {
            case "|":
                return Math.abs(nums[0]);
            case "+":
                return nums[0] + nums[1];
            case "-":
                return nums[0] - nums[1];
            case "*":
                return nums[0] * nums[1];
            case "@":
                return (nums[0] > 0) ? nums[1] : nums[2];
            case ">":
                return Math.max(Math.max(nums[0], nums[1]), nums[2]);
            default:
                return 0;
        }
    }
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        for (int i=1; i<=5; i++) {
            System.out.println("INPUT LINE " + i + ":");
            String input = keyboard.nextLine();
            
            System.out.println(evaluatePrefixExpression(inputToArray(input)).get(0));
            
            System.out.println();
        }
    }
}