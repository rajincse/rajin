package algorithm;

import java.util.*;

/**
 * Created by rajin on 1/20/2018.
 */
public class MaxChunkSortedArray {
    public static void main(String[] args)
    {
        int[] arr = new int[]{1,3,5,9};
        System.out.println(new MaxChunkSortedArray().maxChunksToSorted(arr));
    }
    public int maxChunksToSorted(int[] arr) {
        if(arr ==null || arr.length ==0)
        {
            return 0;
        }

        int count =0;
        int maxVal =-1;
        for(int i=0;i<arr.length;i++)
        {
            maxVal = Math.max(maxVal, arr[i]);
            if(maxVal==i)
            {
                count++;
            }
        }

        return count;
    }
    
}
