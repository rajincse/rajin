package algorithm;

import java.util.HashMap;

/**
 * Created by rajin on 10/14/2017.
 */
public class DegreeOfAnArray {
    public static void main(String[] args){
        int[] nums = new int[]{1,2,2,3,1,4,2};
        System.out.println(new DegreeOfAnArray().findShortestSubArray(nums));
    }
    class CandidateVal{
        int val;
        int degree;
        int left;
        int right;
        public CandidateVal(int val)
        {
            this.val = val;
        }
    }
    public int findShortestSubArray(int[] nums) {
        if(nums == null || nums.length==0)
        {
            return 0;
        }

        HashMap<Integer, CandidateVal> occurrence = new HashMap<Integer, CandidateVal>();

        int degree =0;
        int shortestLength =Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++)
        {
            int n = nums[i];
            if(!occurrence.containsKey(n))
            {
                CandidateVal cd = new CandidateVal(n);
                cd.degree = 1;
                cd.left = i;
                cd.right = i;
                occurrence.put(n, cd);

                if(degree < 1)
                {
                    degree = cd.degree;
                    shortestLength = cd.right - cd.left+1;
                }
                else if(degree == 1 && shortestLength> cd.right - cd.left+1)
                {
                    shortestLength = cd.right-cd.left+1;
                }
            }
            else
            {
                CandidateVal cd  = occurrence.get(n);
                cd.degree++;
                cd.right = i;
                occurrence.put(n,cd);

                if(degree < cd.degree)
                {
                    degree = cd.degree;
                    shortestLength = cd.right - cd.left+1;
                }
                else if(degree == cd.degree && shortestLength> cd.right - cd.left+1)
                {
                    shortestLength = cd.right-cd.left+1;
                }
            }
        }



        return shortestLength;
    }
}
