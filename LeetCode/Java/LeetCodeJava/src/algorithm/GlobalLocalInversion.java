package algorithm;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by rajin on 1/27/2018.
 * 775. Global and Local Inversions
 */
public class GlobalLocalInversion {
    public boolean isIdealPermutation(int[] A) {
        int global = getGlobalInversion(A);
        int local = getLocalInversion(A);
        return global== local;
    }
    public int getGlobalInversion(int[] A)
    {
        int[] sorted = new int[A.length];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0;i<A.length;i++)
        {
            sorted[i] = A[i];
            if(!map.containsKey(A[i]))
            {
                map.put(A[i], i);
            }
        }
        Arrays.sort(sorted);
        int count=0;
        int carry =0;
        for(int i=0;i<sorted.length;i++)
        {
            int index = map.get(sorted[i]);
            if(index > i)
            {
                count+= index-i;
                carry=0;
            }
            else if( index==i)
            {
                carry++;
            }
            else {
                count+= carry;
                carry=0;
            }
        }

        return count;
    }
    public int getLocalInversion(int[] A)
    {
        int count =0;
        for(int i =0;i<A.length-1;i++)
        {
            if(A[i] > A[i+1])
            {
                count++;
            }
        }
        return count;
    }
}
