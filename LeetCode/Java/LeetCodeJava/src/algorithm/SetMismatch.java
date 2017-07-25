package algorithm;

import java.util.Arrays;

/**
 * Created by rajin on 7/22/2017.
 */
public class SetMismatch {
    public static void main(String[] args)
    {
        int[] nums = new int[10000];
        for(int i=0;i<nums.length;i++)
        {
            nums[i] = i+1;
        }
        nums[4450] = 4450;
        System.out.println(Arrays.toString(new SetMismatch().findErrorNums(nums)));
    }
    public int[] findErrorNums(int[] nums) {
        int[] occurrenceMap = new int[nums.length];
        int duplicateNumber = -1;
        int missingNumber = -1;
        for(int i=0;i<nums.length;i++)
        {
            occurrenceMap[nums[i]-1]++;
        }

        for(int i=0;i<occurrenceMap.length;i++){
            if(occurrenceMap[i]>1)
            {
                duplicateNumber = i+1;
            }
            if(occurrenceMap[i]==0)
            {
                missingNumber = i+1;
            }
        }

        if(duplicateNumber != -1 && missingNumber!= -1)
        {
            return new int[]{duplicateNumber, missingNumber};
        }
        else
        {
            return null;
        }

    }
}
