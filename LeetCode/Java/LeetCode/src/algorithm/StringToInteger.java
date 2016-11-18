package algorithm;

public class StringToInteger {
	public static void main(String [] args)
	{
		String input ="-2147483648";
		int output = new StringToInteger().new Solution().myAtoi(input);
		System.out.println(output);
		
	}
	class Solution {
	    public int myAtoi(String str) {
	    	long result =0;
	    	str = str.trim();
	    	boolean isNegative = false;
	    	boolean signSaved = false;
	    	for(int i=0;i<str.length();i++)
	    	{
	    		char ch = str.charAt(i);
	    		if(ch == '-' && !signSaved)
	    		{
	    			isNegative = true;
	    			signSaved = true;
	    		}
	    		else if(ch == '+' && !signSaved)
	    		{
	    			isNegative = false;
	    			signSaved = true;
	    		}
	    		else if(ch >='0' && ch <='9')
	    		{
	    			result = result*10+ (ch-'0');	
	    			if(result > Integer.MAX_VALUE && !isNegative)
	    	    	{
	    	    		return Integer.MAX_VALUE;
	    	    	}
	    	    	else if(result > Integer.MAX_VALUE && isNegative)
	    	    	{
	    	    		return Integer.MIN_VALUE;
	    	    	}
	    		}
	    		else
	    		{
	    			break;
	    		}
	    	}
	    	if(isNegative)
	    	{
	    		result *= -1 ;
	    	}
	    	
	    	
	    		
	        return (int)result;
	    }
	}
}
