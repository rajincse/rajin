package algorithm;

public class LongestPalindromicSubstring {
	public static void main(String []args)
	{
		LongestPalindromicSubstring obj = new LongestPalindromicSubstring();
		String input ="ccc";
		String output = obj.longestPalindrome(input);
		System.out.println(input+"=>"+output);
	}
	 public String longestPalindrome(String s) {
	     char[] charArray = s.toCharArray();
	     int index1 =0;
	     int index2 =0;
	     for(int i=1;i<charArray.length;i++)
	     {
	    	  if(i< charArray.length-1 && charArray[i-1] == charArray[i+1])
	    	 {
	    		 int leftIndex =i-1;
	    		 int rightIndex =i+1;
	    		 while(leftIndex >=0 && rightIndex < charArray.length 
	    				 && charArray[leftIndex] == charArray[rightIndex])
	    		 {
	    			 leftIndex--;
	    			 rightIndex++;
	    		 }
	    		 
	    		 if(rightIndex-1-leftIndex-1+1 > index2-index1+1 )
	    		 {
	    			 index1 = leftIndex+1;
	    			 index2 = rightIndex-1;
	    		 }
	    	 }
	    	 if(charArray[i-1] == charArray[i])
	    	 {
	    		 int leftIndex =i-1;
	    		 int rightIndex =i;
	    		 while(leftIndex >=0 && rightIndex < charArray.length 
	    				 && charArray[leftIndex] == charArray[rightIndex])
	    		 {
	    			 leftIndex--;
	    			 rightIndex++;
	    		 }
	    		 
	    		 if(rightIndex-1-leftIndex-1+1 > index2-index1+1 )
	    		 {
	    			 index1 = leftIndex+1;
	    			 index2 = rightIndex-1;
	    		 }
	    	 }
	     }
		 return s.substring(index1, index2+1);
	 }
	 
	 public boolean isPalindrome(String s)
	 {
		 return s.equals(new StringBuilder(s).reverse().toString());
	 }
}
