package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 8/16/2017.
 */
public class GrayCode {
    public static void main(String[] args){
        int n = 3;
        List<Integer> result = new GrayCode().grayCode(n);
        System.out.println(result);
    }
    public List<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(0);
        if(n ==0)
        {
            return result;
        }


        result.add(1);

        for(int i=1;i<n;i++)
        {
            int offset = 1 <<i;

            for(int j=offset-1;j>=0;j--)
            {
                int val  = result.get(j);
                result.add(val+offset);
            }
        }
        return result;

    }
}
