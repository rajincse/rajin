package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rajin on 7/29/2017.
 */
public class Combinations {
    public static void main(String[] args){
        List<List<Integer>> result = new Combinations().combine(20,16);
        for(List l: result)
        {
            System.out.println(l);
        }
    }
    public List<List<Integer>> combine(int n, int k) {

        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        combination(new ArrayList<Integer>(),1,n,k,result);

        return result;
    }



    public void combination(List<Integer> prefixList, int next, int n, int k, List<List<Integer>> result){
        if(prefixList.size()==k)
        {
            result.add(new ArrayList<Integer>(prefixList));
        }
        else
        {
            for(int i=next;i<=n;i++)
            {
                prefixList.add(i);
                combination(prefixList,i+1,n,k,result);
                prefixList.remove(prefixList.size()-1);
            }
        }
    }

}
