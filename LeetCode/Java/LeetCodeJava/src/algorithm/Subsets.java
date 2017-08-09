package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by rajin on 8/9/2017.
 */
public class Subsets {
    public static  void main(String[] args)
    {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = new Subsets().subsets(nums);

        System.out.println("Result:");
        for(List<Integer> list: result)
        {
            System.out.println(list);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        construct(new ArrayList<Integer>(), nums, 0, result);

        return result;
    }
    public void construct(List<Integer> prefix, int[] nums, int start, List<List<Integer>> result)
    {
        result.add(new ArrayList<Integer>(prefix));
        if(start == nums.length)
        {

            return;
        }


        for(int i=start;i<nums.length;i++)
        {
            prefix.add(nums[i]);

            construct(prefix,nums, i+1, result );
            prefix.remove(new Integer(nums[i]));
        }

    }

}
