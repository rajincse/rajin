package algorithm;

import java.util.ArrayList;
import java.util.Arrays;


public class ArrayPairSum {
	public static void main(String[] args){
//		int[] nums = new int[]{4,1,3,2,5,6};
		int[] nums = new int[]{3381,443,6918,-4429,1531,4315,862,-2062,-8358,-5806};
		ArrayPairSum obj = new ArrayPairSum();
		int sum = obj.arrayPairSum(nums);
		System.out.println(Arrays.toString(nums)+"\n=>\n"+sum);
	}
	
	public int arrayPairSum(int[] nums) {
		
		Arrays.sort(nums);
		int maxPairSum = 0;
		for(int i=0;i<nums.length;i+=2){
			maxPairSum+= nums[i];
		}
		return maxPairSum;
    }
	
	
	
	
}
