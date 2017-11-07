package algorithm;

import java.util.Arrays;

/**
 * Created by rajin on 11/5/2017.
 * 238. Product of Array Except Self
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args)
    {
        int[] nums = new int[]{1,2,3,4};
        System.out.println(Arrays.toString(new ProductOfArrayExceptSelf().productExceptSelf(nums)));
    }

    public int[] productExceptSelf(int[] nums) {

        int[] result = new int[nums.length];
        //forward
        int temp =1;
        for(int i=0;i<nums.length;i++)
        {
            result[i] = temp;
            temp*= nums[i];
        }
        //backward
        temp =1;
        for(int i=nums.length-1;i>=0;i--){
            result[i] = result[i] * temp;
            temp *= nums[i];
        }
        return result;
    }
}
