package algorithm;

import java.util.HashMap;


public class TwoSum {
	public static void main(String [] args)
	{
		int input[] =new int[]{1,2,3,4,5,4,1,1,4,3};
		int target =0;
//		int[] output = new TwoSum().new Solution().twoSum(input, target);
		
//		System.out.println("index1="+output[0]+", index2="+output[1]);
		int d = new TwoSum().new Solution().countDuplicates(input);
		
		System.out.println(d);
	}
	class Solution {
	    public int[] twoSum(int[] nums, int target) {
	        int[] output = new int[2];
	        
	        HashMap<Integer, Integer> hashTable = new HashMap<Integer, Integer>();
	        for(int i=0;i<nums.length;i++)
	        {
	        	int otherValue = target - nums[i];
	        	if(hashTable.containsKey(otherValue))
	        	{
	        		output[0] = hashTable.get(otherValue);
	        		output[1] = i+1;
	        		break;
	        	}
	        	else
	        	{
	        		hashTable.put(nums[i], i+1);
	        	}
	        }
	        return output;
	    }
	    public int countDuplicates(int[] numbers)
	    {
	    	HashMap<Integer, Integer> occurrenceMap = new HashMap<Integer, Integer>();
	    	for(int i=0;i<numbers.length;i++)
	    	{
	    		if(occurrenceMap.containsKey(numbers[i]))
	    		{
	    			int occurrence = occurrenceMap.get(numbers[i]);
	    			occurrence++;
	    			occurrenceMap.put(numbers[i], occurrence);
	    		}
	    		else
	    		{
	    			occurrenceMap.put(numbers[i], 1);
	    		}
	    	}
	    	int duplicates =0;
	    	for(Integer key:occurrenceMap.keySet())
	    	{
	    		if(occurrenceMap.get(key) > 1)
	    		{
	    			duplicates++;
	    		}
	    	}
	    	return duplicates;
	    }
	    
	}
}
