package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by rajin on 8/17/2017.
 * Leetcode #90. Subsets II 
 */
public class Subsets2 {
    public static  void main(String[] args)
    {
        int[] nums = new int[]{4,4,4,1,4};
        List<List<Integer>> result = new Subsets2().subsetsWithDup(nums);

        System.out.println("Result:");
        for(List<Integer> list: result)
        {
            System.out.println(list);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        construct(new ArrayList<Integer>(), nums, 0, result);

        return result;
    }
    public void construct(List<Integer> prefix, int[] nums, int start, List<List<Integer>> result)
    {
        System.out.print("Construct: "+prefix+",{");
        for(int i=start;i<nums.length;i++)
        {
            System.out.print(nums[i]+",");
        }
        System.out.println("}");
        result.add(new ArrayList<Integer>(prefix));
        if(start == nums.length)
        {

            return;
        }

        HashSet<Integer> usedSet = new HashSet<Integer>();

        for(int i=start;i<nums.length;i++)
        {
            if(!usedSet.contains(new Integer(nums[i])))
            {
                usedSet.add(new Integer(nums[i]));
                prefix.add(nums[i]);

                construct(prefix,nums, i+1, result );
                prefix.remove(new Integer(nums[i]));

            }

        }
    }
}
