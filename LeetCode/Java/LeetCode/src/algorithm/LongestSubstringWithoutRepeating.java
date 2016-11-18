package algorithm;

import java.util.LinkedList;

public class LongestSubstringWithoutRepeating {

	public static void main(String []args)
	{
		LongestSubstringWithoutRepeating obj = new LongestSubstringWithoutRepeating();
		String input ="abcabcbb";
		int output = obj.lengthOfLongestSubstring(input);
		System.out.println(input+"=>"+output);
	}
	public int lengthOfLongestSubstring(String s) {
		
		LinkedList<Character> queue = new LinkedList<Character>();
		int maxLength = 0;
		char[] charArray = s.toCharArray();
		
		for(char ch: charArray)
		{
			if(queue.contains(ch))
			{
				if(queue.size() > maxLength)
				{
					maxLength = queue.size();
				}
				
				if(!queue.isEmpty())
				{
					char deleteCandidate = queue.getFirst();
					while(deleteCandidate != ch)
					{
						queue.remove();
						deleteCandidate = queue.getFirst();
					}
					queue.remove();
				}
				
			}
			
			queue.add(ch);
			
		}
		if(queue.size() > maxLength)
		{
			maxLength = queue.size();
		}
        return maxLength;
    }
	
}
