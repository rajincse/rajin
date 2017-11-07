package algorithm;

/**
 * Created by rajin on 11/5/2017.
 * 152. Maximum Product Subarray
 */
public class MaxProductSubArray {
    public static void  main(String[] args){
        int[] nums = new int[]{2,3,-2,4};
        System.out.println(new MaxProductSubArray().maxProduct(nums));
    }

    public int maxProduct(int[] nums) {

        int max = nums[0];
        int min = nums[0];
        int maxProduct = nums[0];
        for(int i=1;i<nums.length;i++)
        {
            int prevMax = max;
            max = Math.max(Math.max(prevMax * nums[i], min*nums[i]), nums[i]);
            min = Math.min(Math.min(prevMax*nums[i], min* nums[i]), nums[i]);

            maxProduct = Math.max(maxProduct, max);
        }

        return maxProduct;
    }
}
