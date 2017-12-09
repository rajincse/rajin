package algorithm;

/**
 * Created by rajin on 12/9/2017.
 * 268. Missing Number
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for(int num: nums)
        {
            sum+=num;
        }
        int n= nums.length;

        return n*(n+1)/2 - sum;
    }
}
