package algorithm;

public class RegularExpressionMatching {

	public static void main(String[] args)
	{
		String s ="aab";
		String pattern ="a*b";	
		
		
		System.out.println(s+", "+pattern+", match:"+isMatch(s, pattern));
	}
	public static final char INVALID ='\0'; 
	public static boolean isMatch(String s, String p) {
		
		
		//base case
		if(p.length() ==0)
		{
			return s.length()==0;
		}
		
		//length =1 || p[1] ='*'
		if(p.length() == 1 || p.charAt(1) != '*')
		{
			if(s.length() ==0)
			{
				return false;				
			}
			else if(!(p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') )
			{
				return false;
			}
			else
			{
				return isMatch(s.substring(1), p.substring(1));
			}
		}
		
		//case -2 : p[1] = '*'
		if (p.charAt(1) != '*')
		{
			if(s.length() ==0)
			{
				return false;				
			}
			else if(!(p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') )
			{
				return false;
			}
			else
			{
				return isMatch(s.substring(1), p.substring(1));
			}
		}
		else
		{
			//case-2.1: a* can have 0 elements
			if(isMatch(s, p.substring(2)))
			{
				return true;
			}
			
			//case-2.2: check with every substrings
			int i=0;
			while(i< s.length() && ( s.charAt(i) == p.charAt(0) || p.charAt(0)== '.'))
			{
				if(isMatch(s.substring(i+1), p.substring(2)))
				{
					return true;
				}
				i++;
			}
			
		}
		return false;
		
    }
}

