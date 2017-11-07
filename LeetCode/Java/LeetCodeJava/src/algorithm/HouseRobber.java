package algorithm;

/**
 * Created by rajin on 11/6/2017.
 */
public class HouseRobber {
    public static void main(String[] args)
    {
        int[] nums = new int[]{7,9,5,4,11,6};
        System.out.println(new HouseRobber().rob(nums));
    }
    public int rob(int[] nums) {
        if(nums ==null || nums.length ==0)
        {
            return 0;
        }
        int[] mem = new int[nums.length];
        for(int i=0;i<mem.length;i++)
        {
            mem[i] = -1;
        }
        return max(nums, 0, mem);
    }

    public int max(int[] nums, int startIndex, int[] mem){
        if(startIndex>= nums.length)
        {
            return 0;
        }
        else if(mem[startIndex] !=-1){
//            System.out.println("Recall:"+startIndex);
            return mem[startIndex];
        }
        else if(startIndex == nums.length-1)
        {
            mem[startIndex] =nums[startIndex];
            return mem[startIndex];
        }
        else
        {
            int max = Math.max(
                    nums[startIndex]+max(nums, startIndex+2, mem),
                    max(nums, startIndex+1, mem)
            );

            mem[startIndex] = max;
            return mem[startIndex];
        }
    }

}
