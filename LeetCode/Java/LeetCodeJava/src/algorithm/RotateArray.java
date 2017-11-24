package algorithm;

import java.util.Arrays;

/**
 * Created by rajin on 11/23/2017.
 * 189. Rotate Array
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {

        if(k==0 || nums== null )
        {
            return;
        }
        k = k % nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }

    public void reverse(int[] nums, int start, int end)
    {
        while(start < end)
        {
            int temp= nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


    public static void main(String[] args)
    {
        int[] nums = new int[]{1,2,3,4,5,6};
        int k = 2;
        new RotateArray().rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
