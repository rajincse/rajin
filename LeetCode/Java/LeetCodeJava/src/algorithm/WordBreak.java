package algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by rajin on 12/3/2017.
 * 139. Word Break
 */
public class WordBreak {
    public static void main(String[] args)
    {
        String s="a";
        List<String> dict = Arrays.asList("b");
        System.out.println(new WordBreak().wordBreak(s, dict));
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s ==null || s.isEmpty() || wordDict ==null || wordDict.isEmpty())
        {
            return false;
        }
        int[][] mem = new int[s.length()][s.length()];

        HashSet<String> set = new HashSet<String>(wordDict);
        return wordBreak(s, 0, s.length()-1, set, mem);
    }

    public boolean wordBreak(String s, int start, int end, HashSet<String> set, int[][] mem)
    {
        if(start> end)
        {
            mem[start][end] = 2;
            return true;
        }
        else if(mem[start][end] > 0)
        {
            return mem[start][end]==2;
        }
        else
        {
            String substr = s.substring(start, end+1);
            if(set.contains(substr))
            {
                mem[start][end] = 2;
                return true;
            }
            else
            {
                StringBuilder sb = new StringBuilder();

                for(int i=start;i<=end;i++)
                {
                    sb.append(s.charAt(i));
                    if(set.contains(sb.toString()) && wordBreak(s, i+1, end, set, mem))
                    {
                        mem[start][end] = 2;
                        return true;
                    }
                }

                mem[start][end] = 1;
                return false;
            }
        }
    }
}

