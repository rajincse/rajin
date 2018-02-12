package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rajin on 1/27/2018.
 * 15. 3Sum
 */
public class ThreeSum {
    public static void main(String[] args)
    {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = new ThreeSum().threeSum(nums);
        for(List<Integer> l: result)
        {
            System.out.println(l);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {

        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums.length==0 || nums.length<3)return result;

        Arrays.sort(nums);

        for(int i=0;i<nums.length-2;i++)
        {
            if(i==0 || nums[i] != nums[i-1])
            {
                int left = i+1;
                int right = nums.length-1;
                while(left < right)
                {
                    if(nums[i]+nums[left]+nums[right]==0)
                    {
                        ArrayList<Integer> buffer = new ArrayList<Integer>();
                        buffer.add(nums[i]);
                        buffer.add(nums[left]);
                        buffer.add(nums[right]);

                        result.add(buffer);
                        left++;
                        right--;
                        while( left < right && nums[left] == nums[left-1])
                        {
                            left++;
                        }


                        while( left < right && nums[right] == nums[right+1])
                        {
                            right--;
                        }
                    }
                    else if(nums[i]+nums[left]+nums[right]<0)
                    {
                        left++;
                        while( left < right && nums[left] == nums[left-1])
                        {
                            left++;
                        }
                    }
                    else
                    {
                        right--;
                        while( left < right && nums[right] == nums[right+1])
                        {
                            right--;
                        }
                    }
                }
            }
        }
        return result;
    }

    public String getListString(List<List<Integer>> result)
    {
        StringBuilder sb = new StringBuilder();
        for(List<Integer> list: result)
        {
            sb.append(list.toString()+", ");
        }
        return sb.toString();
    }

    public void dfs(List<List<Integer>> result, List<Integer> buffer, int currentSum, int currentIndex, int[] nums)
    {
        System.out.println("{"+getListString(result)+"}, b="+buffer+", sum="+currentSum+", i="+currentIndex+", "+Arrays.toString(nums));
        if(currentIndex==nums.length)
        {
            return;
        }
        else if(buffer.size() ==3 )
        {
            if(currentSum==0)
            {
                result.add(new ArrayList<Integer>(buffer));
            }

        }
        else
        {
            for(int i=currentIndex;i<nums.length;i++)
            {
                buffer.add(nums[i]);
                dfs(result, buffer, currentSum+nums[i], i+1, nums);
                buffer.remove(buffer.size()-1);
            }
        }
    }

}
