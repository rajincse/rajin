package algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by rajin on 10/21/2017.
 */
public class SubarrayLessK {
    public static void main(String[] args)
    {
        int[] num = new int[]{1,1,1};
        int k =1;
        System.out.println(new SubarrayLessK().numSubarrayProductLessThanK(num, k));
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {

        if(nums ==null || nums.length ==0)
        {
            return 0;
        }

        if(k==0) return 0;
        int count=0;
        int product =1;
        int left =0;
        for(int right =0;right<nums.length;right++)
        {
            product *= nums[right];
            while (product>=k && left<=right)
            {
                product/= nums[left];
                left++;
            }
            count+= (right -left+1);
        }

        return count;
    }





}
