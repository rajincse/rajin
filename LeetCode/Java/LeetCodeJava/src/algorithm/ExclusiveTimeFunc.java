package algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by rajin on 12/3/2017.
 * 636. Exclusive Time of Functions
 */
public class ExclusiveTimeFunc {
    public static void main(String[] args)
    {
        int n =2;
        List logs = Arrays.asList("0:start:0","1:start:1","0:start:2", "0:end:3", "1:end:4", "0:end:5");
        int[] result = new ExclusiveTimeFunc().exclusiveTime(n, logs);
        System.out.println(Arrays.toString(result));
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        if(logs==null || logs.isEmpty() )
        {
            return result;
        }
        Stack<Integer> stack = new Stack<Integer>();

        int time =0;


        for(int i=0;i<logs.size();i++)
        {
            LogEntry e = new LogEntry(logs.get(i));
            if(stack.isEmpty())
            {
                if(e.isStart)
                {
                    stack.push(e.id);
                }
                time = e.time;
            }
            else
            {
                if(e.isStart)
                {
                    result[stack.peek()] += e.time-1-time+1;
                    stack.push(e.id);
                    time = e.time;
                }
                else
                {
                    result[e.id] += e.time - time+1;
                    stack.pop();
                    time = e.time+1;
                }
            }

        }

        return result;
    }
    class LogEntry{
        int id;
        boolean isStart;
        int time;
        public LogEntry(String s)
        {
            int index1 = s.indexOf(":");
            int index2 = s.indexOf(":", index1+1);

            id = Integer.parseInt(s.substring(0, index1));
            isStart = s.charAt(index1+1) =='s';
            time = Integer.parseInt(s.substring( index2+1));
        }

        @Override
        public String toString() {
            return "{" +
                    "id=" + id +
                    ", isStart=" + isStart +
                    ", time=" + time +
                    '}';
        }
    }
}
