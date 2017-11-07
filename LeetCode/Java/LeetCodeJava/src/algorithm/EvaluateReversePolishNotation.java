package algorithm;

import java.util.Stack;

/**
 * Created by rajin on 11/5/2017.
 */
public class EvaluateReversePolishNotation {
    public static void main(String[] args){
        String[] tokens = new String[]{"4", "13", "5", "/", "+"};
        System.out.println(new EvaluateReversePolishNotation().evalRPN(tokens));
    }
    public enum Operation{
        Plus, Minus, Div, Mult
    }
    public int evalRPN(String[] tokens) {
        if(tokens.length == 0)
        {
            return 0;
        }
        Stack<Integer> stack = new Stack<Integer>();
        for(String token: tokens)
        {
            if(token.equals("+"))
            {
                evaluate(stack,Operation.Plus);
            }
            else if(token.equals("-"))
            {
                evaluate(stack,Operation.Minus);
            }
            else if(token.equals("*"))
            {
                evaluate(stack,Operation.Mult);
            }
            else if(token.equals("/"))
            {
                evaluate(stack,Operation.Div);
            }
            else
            {
                Integer val = Integer.parseInt(token);
                stack.push(val);
            }
        }

        int result = stack.pop();

        return result;
    }
    public int evaluate(Stack<Integer> stack, Operation op)
    {
        Integer op2 = stack.pop();
        Integer op1 = stack.pop();
        Integer val =0;
        if(op == Operation.Plus)
        {
            val = op1+op2;
        }
        else if(op == Operation.Minus)
        {
            val = op1-op2;
        }
        else if(op == Operation.Mult)
        {
            val = op1*op2;
        }
        else if(op == Operation.Div)
        {
            val = op1/op2;
        }
        stack.push(val);
        return val;
    }

}
