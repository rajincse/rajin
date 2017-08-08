package algorithm;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by rajin on 7/28/2017.
 */
public class SortColor {

    public  static  void main(String[] args)
    {
        int [] nums = new int[]{0,0,2,0,0,1,0,2,1,1,1};
        new SortColor().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void sortColors(int[] nums) {
        int[] occurrenceMap = new int[]{0,0,0};
        for(int color:nums)
        {
            occurrenceMap[color]++;
        }
        for(int i=0;i<nums.length;i++)
        {
            if(i<occurrenceMap[0])
            {
                nums[i] = 0;
            }
            else if(i<occurrenceMap[0]+occurrenceMap[1])
            {
                nums[i] =1;
            }
            else
            {
                nums[i] =2;
            }
        }
    }
}
