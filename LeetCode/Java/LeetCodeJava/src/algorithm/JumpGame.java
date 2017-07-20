package algorithm;

/**
 * Created by rajin on 7/19/2017.
 */
public class JumpGame {
    public static void main(String[] args){
        int[] nums = new int[]{3,2,1,1,4};
        System.out.println("result="+new JumpGame().canJump(nums));
    }
    public boolean canJump(int[] nums) {
        int lastPosition = nums.length -1;
        for(int i=lastPosition-1;i>=0;i--){
            if(i+nums[i] >= lastPosition){
                lastPosition = i;
            }
        }

        return  lastPosition ==0;
    }
}
