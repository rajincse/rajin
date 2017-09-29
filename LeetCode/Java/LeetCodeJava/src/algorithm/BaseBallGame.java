package algorithm;

import java.util.LinkedList;

/**
 * Created by rajin on 9/23/2017.
 */
public class BaseBallGame {
    public static void main(String[] args)
    {
        String[] ops = new String[]{"-60","D","-36","30","13","C","C","-33","53","79"};
        int points = new BaseBallGame().calPoints(ops);
        System.out.println(points);
    }
    public int calPoints(String[] ops) {
        if(ops ==null || ops.length==0)
        {
            return 0;
        }

        int sum =0;
        int lastTwoValid =0;

        LinkedList<Integer> pointStack =new LinkedList<Integer>();//stack

        LinkedList<Integer> lastTwoBuffer =new LinkedList<Integer>();//queue
        LinkedList<Integer> backUpBuffer =new LinkedList<Integer>();//queue
        for(String op: ops)
        {

            if(op.equals("C"))
            {
                if(pointStack.isEmpty() || lastTwoBuffer.isEmpty())
                {
                    continue;
                }
                int lastValid = pointStack.removeFirst();
                sum-=lastValid;
                lastTwoBuffer.removeLast();
                int backup =0;
                if(!backUpBuffer.isEmpty())
                {
                    backup=backUpBuffer.removeLast();
                    lastTwoBuffer.addFirst(backup);
                }
                lastTwoValid = lastTwoValid-lastValid+backup;

            }
            else if(op.equals("D"))
            {

                int val = 2 * pointStack.peek();
                if(pointStack.isEmpty()) {
                    continue;
                }
                sum+=val;
                pointStack.addFirst(val);

                if(lastTwoBuffer.size()<2)
                {
                    lastTwoValid+=val;
                }
                else
                {
                    int top = lastTwoBuffer.removeFirst();
                    lastTwoValid= lastTwoValid-top+val;
                    backUpBuffer.add(top);
                }
                lastTwoBuffer.add(val);
            }
            else if(op.equals("+"))
            {

                int val = lastTwoValid;
                pointStack.addFirst(val);
                sum+=val;
                if(lastTwoBuffer.size()<2)
                {
                    lastTwoValid+=val;
                }
                else
                {
                    int top = lastTwoBuffer.removeFirst();
                    lastTwoValid= lastTwoValid-top+val;
                    backUpBuffer.add(top);
                }
                lastTwoBuffer.add(val);
            }
            else {
                int val = Integer.parseInt(op);
                pointStack.addFirst(val);

                sum+=val;
                if(lastTwoBuffer.size()<2)
                {
                    lastTwoValid+=val;
                }
                else
                {
                    int top = lastTwoBuffer.removeFirst();
                    lastTwoValid= lastTwoValid-top+val;
                    backUpBuffer.add(top);
                }
                lastTwoBuffer.add(val);
            }
            System.out.println(op+",Val="+pointStack.peek()+", sum="+sum+", lastTwo="+lastTwoValid);
        }

        return sum;
    }
}
