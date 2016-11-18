package algorithm;
public class ReverseInteger {
	public static void main(String[] args)
	{
//		int input = -2147483648;
		
		int input = 1000000003;
		
		int output = new Solution().reverse(input);
		
		System.out.println(input+" -> "+output+" max,min"+Integer.MAX_VALUE+", "+Integer.MIN_VALUE);
	}
}

class Solution {
    public int reverse(int x) {
    	int radix =10;
    	int input=x;
    	int output =0;
    	while(input !=0)
    	{
    		
    		if(output > 214748364 || output < -214748364) // Integer.MAX_VALUE/10 = 214748364
    		{
    			return 0;
    		}
    		output = output*radix+ (input %radix);
    		input = input / radix;	
    	}
    	
        return output;
    }
}