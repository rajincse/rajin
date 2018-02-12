package algorithm;

import java.util.HashSet;

/**
 * Created by rajin on 1/27/2018.
 * 771. Jewels and Stones
 */
public class JewelStones {
    public int numJewelsInStones(String J, String S) {
        if( J==null || S==null || J.isEmpty() || S.isEmpty())
        {
            return 0;
        }
        HashSet<Character> jewelSet = new HashSet<Character>();
        for(char c: J.toCharArray())
        {
            jewelSet.add(c);
        }

        int count =0;

        for(char c: S.toCharArray())
        {
            if(jewelSet.contains(c))
            {
                count++;
            }
        }

        return count;
    }
}
