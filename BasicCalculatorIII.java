package leetcode.amazon;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * The expression string contains only non-negative integers, +, -, *, / operators, open ( and closing parentheses ) and empty spaces.
 * Input - (2+6* 3+5- (3*14/7+2)*5)+3
 *
 *
 * **/

public class BasicCalculatorIII {
    public static void main(String[] args) {
        int result = calculate("(2+6* 3+5- (3*14/7+2)*5)+3");
        System.out.println("Result "+result);
    }

    public static int calculate(String s) {
        Queue<Character> queue = new LinkedList<>();
        for(char c:s.toCharArray()){
            if(c != ' ')
                queue.offer(c);
        }
        queue.offer(' ');
        return helper(queue);
    }

    private static int helper(Queue<Character> queue) {
        int sum = 0;
        int prev = 0;
        int num = 0;
        char prevOps = '+';
        while (!queue.isEmpty()){
            char c = queue.poll();
            if(c == '('){
                num = helper(queue);
            }else if(Character.isDigit(c)){
                num = num * 10 + c - '0';
            }
            //else if(c == ')') break;
            else{
                switch (prevOps){
                    case '+':
                        sum += prev;
                        prev = num;
                        break;
                    case '-':
                        sum += prev;
                        prev = -num;
                        break;
                    case '*':
                        prev *= num;
                        break;
                    case '/':
                        prev /= num;
                        break;
                }
                if(c == ')') break;
                num = 0;
                prevOps = c;
            }
        }
        return sum+prev;
    }

}
