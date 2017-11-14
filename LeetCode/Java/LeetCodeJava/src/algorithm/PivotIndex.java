package algorithm;

/**
 * Created by rajin on 11/11/2017.
 */
public class PivotIndex {
    public static void main(String[] args){
        int[] nums = new int[]{1, 7, 3, 6, 5, 6};
        System.out.println(new PivotIndex().pivotIndex(nums));
    }
    public int pivotIndex(int[] nums) {

        int[][] sumArray = new int[nums.length][2];

        //forward
        int sum =0;
        for(int i=0;i<nums.length;i++)
        {
            sumArray[i][0] = sum;
            sum+= nums[i];
        }
        sum =0;
        for(int i=nums.length-1;i>=0;i--){
            sumArray[i][1] = sum;
            sum+= nums[i];
        }

        for(int i=0;i<sumArray.length;i++)
        {
            if(sumArray[i][0] == sumArray[i][1])
            {
                return i;
            }
        }

        return -1;
    }
}
