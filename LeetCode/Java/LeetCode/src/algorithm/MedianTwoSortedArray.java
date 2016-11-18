package algorithm;


public class MedianTwoSortedArray {
	public static void main(String [] args)
	{
		int input1[] =new int[]{1,2};
		int input2[] = new int[]{1,1};
		double output = new MedianTwoSortedArray().new Solution().findMedianSortedArrays(input1, input2);
		
		System.out.println(output);
	}
	class Solution {
	    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        int totalLength = nums1.length+nums2.length;
	        int[] overAllSortedArray = new int[totalLength];
	        
        	int index1=0;
        	int index2=0;
        	
        	int currentIndex=0;
        	while( index1 < nums1.length || index2 < nums2.length)
        	{	
        		if(index1 < nums1.length && index2 < nums2.length && nums1[index1] < nums2[index2])
        		{
        			overAllSortedArray[currentIndex] = nums1[index1];
        			index1++;
        			
        		}
        		else if(index1 < nums1.length && index2 < nums2.length )
        		{
        			overAllSortedArray[currentIndex] = nums2[index2];
        			index2++;
        		}
        		else if(index1 < nums1.length ){
        			overAllSortedArray[currentIndex] = nums1[index1];
        			index1++;
        		}	
        		else if(index2< nums2.length )
        		{
        			overAllSortedArray[currentIndex] = nums2[index2];
        			index2++;
        		}
        		currentIndex++;
        		
        	}
        	double median = 1.0*(overAllSortedArray[totalLength/2]+overAllSortedArray[(totalLength-1)/2])/2;
        	return  median;
	    }
	}
}
