package algorithm;

import java.util.ArrayList;

/**
 * Created by rajin on 10/14/2017.
 */
public class CountBinaryStrings {
    public static void main(String[] args)
    {
        String s = "10101";
        System.out.println(new CountBinaryStrings().countBinarySubstrings(s));
    }
    public int countBinarySubstrings(String s) {
        if(s == null || s.isEmpty()) return 0;
        char[] binary = s.toCharArray();
        ArrayList<Integer> chunks = new ArrayList<Integer>();
        int currentLength =0;
        char lastChar ='0';
        for(char c: binary)
        {
            if(currentLength==0)
            {
                currentLength++;
                lastChar = c;
            }
            else if(c == lastChar)
            {
                currentLength++;
            }
            else
            {
                chunks.add(currentLength);
                currentLength = 1;
                lastChar = c;
            }
        }
        chunks.add(currentLength);
        int count =0;
        for(int i=0;i<chunks.size()-1;i++)
        {
            int left = chunks.get(i);
            int right = chunks.get(i+1);
            count = count+ Math.min(left, right);
        }
        return count;
    }

}
