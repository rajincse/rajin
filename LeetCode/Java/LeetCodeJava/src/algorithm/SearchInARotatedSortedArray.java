package algorithm;

/**
 * Created by rajin on 1/9/2018.
 */
public class SearchInARotatedSortedArray {
    public static void main(String[] args)
    {
        int[] nums = new int[]{6,7,0,1,2,3,4,5};
        int target = 6;
        System.out.println(new SearchInARotatedSortedArray().search(nums, target));
    }

    public int search(int[] nums, int target) {

        int pivot = getPivot(nums);
        return binarySearchPivot(nums, target, pivot);
    }
    public int binarySearchPivot(int[] nums, int target, int pivot)
    {
        int left = 0;
        int right = nums.length-1;
        while (left <= right)
        {
            int mid = (left+right)/2;
            int midRotated =getRotatedIndex(mid, pivot, nums.length);
            if(nums[midRotated]==target)
            {
                return midRotated;
            }

            if(nums[midRotated] <target)
            {
                left = mid+1;
            }
            else
            {
                right = mid-1;
            }
        }
        return -1;
    }

    public int getUnrotatedIndex(int rotatedIndex, int pivot, int length)
    {
        return (rotatedIndex-pivot+length)%length;
    }
    public int getRotatedIndex(int unrotatedIndex, int pivot, int length)
    {
        return (unrotatedIndex+pivot ) % length;
    }

    public int getPivot(int[] nums)
    {

        int left =0;
        int right =nums.length-1;

        while (left <= right)
        {
            if(left == right || nums[left]< nums[right])
            {
                return left;
            }
            else
            {
                int mid = (left+right)/2;
                if(left ==mid )
                {
                    return right;
                }
                else if(nums[left]> nums[mid] )
                {
                    right = mid;
                }
                else
                {
                    left = mid;
                }
            }

        }

        return left;
    }
}
