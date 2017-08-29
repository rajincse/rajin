package algorithm;

import java.util.Arrays;

/**
 * Created by rajin on 8/17/2017.
 * Leetcode #91. Decode Ways
 */
public class DecodeWays {
    public static void main(String[] args){
        String s = "8191696279628294183756885248898921692742862115932382341649566834953948315139424749676974497353527219";

        System.out.println(s+"->"+new DecodeWays().numDecodings(s));
    }
    public int numDecodings(String s) {
        if(s == null)
            return 0;
        if(s.length() ==0)
            return 0;

        char[] str = s.toCharArray();

        int [] dp = new int[str.length];
        for(int i=0;i<dp.length;i++)
        {
            dp[i] = -1;
        }
        return getDecodeWays(str,0, str.length-1,dp);
    }

    public int getDecodeWays(char[] str, int start, int end,int [] dp){

        if(dp[start] <0)
        {
            int n = end-start+1;
            if(n==1)
            {
                if(str[start] >'0' && str[start]<='9')
                {
                    dp[start] =1;
                }
                else
                {
                    dp[start] = 0;
                }
            }
            else if(n==2)
            {

                if(str[start] =='1' && str[end]>='0' && str[end]<='9')
                {
                    if(str[end]=='0')
                    {
                        dp[start] = 1;
                    }
                    else
                    {
                        dp[start] = 2;
                    }
                }
                else if(str[start] =='2' && str[end]>='0' && str[end]<='9')
                {
                    if(str[end]=='0' || (str[end]>='7'))
                    {
                        dp[start] = 1;
                    }
                    else
                    {
                        dp[start] = 2;
                    }
                }
                else if(str[start]>='3' && str[start]<='9'){
                    if(str[end]=='0')
                    {
                        dp[start] = 0;
                    }
                    else
                    {
                        dp[start] = 1;
                    }
                }
                else {
                    return 0;
                }
            }
            else
            {
                int ways = 0;
                if(isACode(str,start,start))
                {
                    ways = getDecodeWays(str,start+1,end, dp);
                }

                if(isACode(str,start,start+1))
                {
                    ways = ways + getDecodeWays(str,start+2,end,dp);

                }
                dp[start] =ways;
            }
        }

        return dp[start];

    }

    public  boolean isACode(char[] str, int start, int end)
    {
        int n= end- start+1;
        if(n>0)
        {
            if(n==1 && str[start]>'0' && str[start]<='9')
            {
                return true;
            }
            else if(n==2)
            {
                if(str[start] =='1' && str[end]>='0' && str[end]<='9')
                {
                    return true;
                }
                else if(str[start] =='2' && str[end]>='0' && str[end]<='6')
                {
                    return true;
                }
                else return false;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

}
