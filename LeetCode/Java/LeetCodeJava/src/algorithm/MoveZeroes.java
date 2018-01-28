package algorithm;

/**
 * Created by rajin on 12/21/2017.
 * 283. Move Zeroes
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        if(nums ==null || nums.length==0)
        {
            return;
        }

        int left =0;
        int right = left+1;
        while(left< nums.length && right < nums.length)
        {
            if(nums[left] != 0)
            {
                left++;
                right++;
            }
            else
            {
                if(nums[right] ==0)
                {
                    right++;
                }
                else
                {
                    //swap
                    nums[left] = nums[right];
                    nums[right] =0;
                    left++;
                    right = left+1;
                }
            }
        }
    }
}
