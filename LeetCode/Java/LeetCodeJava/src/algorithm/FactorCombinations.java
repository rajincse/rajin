package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by rajin on 12/3/2017.
 * 254. Factor Combinations
 */
public class FactorCombinations {
    public static void main(String[] args)
    {
        int n = 32;
        List<List<Integer>> result = new FactorCombinations().getFactors(n);
        for(List<Integer> list: result)
        {
            System.out.println(list);
        }
    }
    public List<List<Integer>> getFactors(int n) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(n < 2)
        {
            return result;
        }
        else
        {
            result = dfs(new ArrayList<Integer>(), result, new HashSet<Integer>(), n,2);

            return result;
        }
    }

    public List<List<Integer>> dfs(List<Integer> factors, List<List<Integer>> result, HashSet<Integer> usedSet, int n, int start)
    {
        if(n<2)
        {
            if(factors.size() > 1)
            {
                result.add(new ArrayList<Integer>(factors));
            }

            return result;
        }
        else{

            for(int f=start;f<=n;f++)
            {
                if(n%f==0 )
                {
                    factors.add(f);
                    result = dfs(factors, result,usedSet, n/f, f);
                    factors.remove(factors.size()-1);
                }
            }


            return result;
        }
    }
}
