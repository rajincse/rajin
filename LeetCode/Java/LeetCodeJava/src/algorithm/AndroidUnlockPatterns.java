package algorithm;

import java.util.*;

/**
 * Created by rajin on 11/27/2017.
 * 351. Android Unlock Patterns
 */
public class AndroidUnlockPatterns {
    public static void main(String[] args)
    {
        int m =3;
        int n =3;
        System.out.println(new AndroidUnlockPatterns().numberOfPatterns(m,n));
    }

    public int numberOfPatterns(int m, int n) {
        int sum = 0;
        for(int j=m;j<=n;j++)
        {
            List<List<Integer>> patterns = new ArrayList<List<Integer>>();
            getPattern(new ArrayList<Integer>(), patterns, j);
            sum+= patterns.size();
            patterns.clear();
        }
        return sum;
    }

    public void getPattern(List<Integer> prefix, List<List<Integer>> patterns, int n)
    {
        if(prefix.size()==n)
        {
            patterns.add(new ArrayList<Integer>(prefix));
            return;
        }
        else
        {
            for(int key=1;key<=9;key++)
            {
                if(isValid(key, prefix))
                {
                    prefix.add(key);
                    getPattern(prefix, patterns, n);
                    prefix.remove(prefix.indexOf(key));
                }
            }
        }

    }

    public boolean isValid(int dest, List<Integer> prefix)
    {
        if(prefix.isEmpty())
        {
            return true;
        }
        else if(prefix.contains(dest))
        {
            return false;
        }
        else
        {
            int source = prefix.get(prefix.size()-1);
            int sr= getRow(source);
            int sc = getColumn(source);
            int dr = getRow(dest);
            int dc = getColumn(dest);
            if(Math.abs(sr-dr)==1
                    || Math.abs(sc-dc)==1)
            {
                return true;
            }
            else
            {
                int midpointR = (sr+dr)/2;
                int midpointC = (sc+dc)/2;
                int midVal = midpointR*3+ midpointC+1;
                return prefix.contains(midVal);
            }

        }

    }
    public int getRow(int num)
    {
        return (num-1)/3;
    }
    public int getColumn(int num)
    {
        return (num-1)%3;
    }
}
