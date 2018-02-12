package algorithm;

/**
 * Created by rajin on 2/11/2018.
 * 162. Find Peak Element
 */
public class FindPeakElement {
    public static void main(String[] args)
    {
        int[] nums = new int[]{1};
        System.out.println(new FindPeakElement().findPeakElement(nums));
    }

    public int findPeakElement(int[] nums) {
        if(nums ==null || nums.length ==0)
        {
            return -1;
        }
        if(nums.length ==1)
        {
            return 0;
        }
        int i=0;
        int j = nums.length-1;
        while(i<=j)
        {
            int m = (i+j)/2;

            if(m==0 )
            {
                if(nums[m]> nums[m+1])
                {
                    return m;
                }
                else
                {
                    i = m+1;
                }
            }
            else if(m==nums.length-1)
            {
                if(nums[m]> nums[m-1])
                {
                    return m;
                }
                else
                {
                    j = m-1;
                }

            }
            else if( nums[m] > nums[m-1] && nums[m] > nums[m+1])
            {
                return m;
            }
            else if(nums[m] > nums[m-1])
            {
                i = m+1;
            }
            else
            {
                j= m-1;
            }
        }

        return -1;
    }
}
