package algorithm;

/**
 * Created by rajin on 12/10/2017.
 * 153. Find Minimum in Rotated Sorted Array
 */
public class FindMinRotatedSortedArray {
    public int findMin(int[] nums) {

        for(int i=1;i<nums.length;i++)
        {
            if(nums[i-1]> nums[i])
            {
                return nums[i];
            }
        }

        return nums[0];
    }
}
