package algorithm;

import java.util.Arrays;

/**
 * Created by rajin on 8/10/2017.
 */
public class MergeSortedArray {
    public  static  void main(String[] args)
    {
        int[] nums1 = new int[]{4,0,0,0,0,0};
        int m = 1;
        int[] nums2 = new int[] {1,2,3,5,6};
        int n = 5;
        new MergeSortedArray().merge(nums1,m,nums2,n);
        System.out.println(Arrays.toString(nums1));

    }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int resultIndex = m+n-1;

        int index1= m-1;
        int index2 = n-1;

        while(resultIndex>=0)
        {
            if(index2 <0 || (index1 >=0 && nums1[index1] > nums2[index2]))
            {
                nums1[resultIndex] = nums1[index1];
                index1--;
            }
            else
            {
                nums1[resultIndex] = nums2[index2];
                index2--;
            }
            resultIndex--;
        }


    }


}
