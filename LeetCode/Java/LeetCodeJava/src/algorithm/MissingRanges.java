package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 11/26/2017.
 * 163. Missing Ranges
 */
public class MissingRanges {
    public static void main(String[] args)
    {
        int[] nums = new int[]{-1};
        int low = -2;
        int up =-1;
        System.out.println(new MissingRanges().findMissingRanges(nums,low,up));
    }
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {

        List<String> result = new ArrayList<String>();
        if(nums.length==0)
        {
            addRange(lower, upper, result);
            return result;
        }
        if(lower<nums[0])
        {
            addRange(lower, nums[0]-1, result);    
        }
        
        for(int i=0;i< nums.length-1;i++)
        {
            if(nums[i] < nums[i+1])
            {
                addRange(nums[i]+1, nums[i+1]-1, result);
            }
            
        }
        if(nums[nums.length-1]<upper)
        {
            addRange(nums[nums.length-1]+1, upper, result);    
        }
        
        

        return result;
    }

    public void addRange(int left, int right, List<String> result)
    {
        if(left<= right)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(left);
            if(left < right)
            {
                sb.append("->");
                sb.append(right);
            }
            
            result.add(sb.toString());
        }
    }
}
