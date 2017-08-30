package algorithm;

/**
 * Created by rajin on 8/30/2017.
 * 96. Unique Binary Search Trees
 */
public class UniqueBST {
    public static void main(String[] args)
    {
        int n =5;
        System.out.println(new UniqueBST().numTrees(n));
    }

    public int numTrees(int n) {

        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1] =1;

        for(int i=2;i<n+1;i++)
        {
            int totalTrees =0;
            for(int j=0;j<i;j++)
            {
                totalTrees+= dp[j]* dp[i-1-j];
            }
            dp[i] = totalTrees;
        }

        return dp[n];
    }
}
