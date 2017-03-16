package algorithm;

public class ReverseString {

	public static void main(String[] args)
	{
		String s = "abcdefghi";
		int k =3;
		ReverseString rev = new ReverseString();
		String ans = rev.reverseStr(s, k);
		System.out.println(s+", "+k+" = "+ans);
	}
	
	public String reverseStr(String s, int k) {
		String answer ="";
		int totalChunks = (int)Math.ceil(1.0* s.length()/(2*k));
		
		for(int i=0;i<totalChunks;i++)
		{
			int remainingChars = s.length()-i*2*k;
			if(remainingChars >= 2* k)
			{
				String reverse = s.substring(i*2*k, i*2*k+k);
				answer+= new StringBuilder(reverse).reverse();
				answer+=s.substring(i*2*k+k,(i+1)*2*k );
			}
			else if(remainingChars < k){
				String reverse = s.substring(i*2*k, i*2*k+remainingChars);
				answer+= new StringBuilder(reverse).reverse();
			}
			else if(remainingChars >= k && remainingChars < 2*k)
			{
				String reverse = s.substring(i*2*k, i*2*k+k);
				answer+= new StringBuilder(reverse).reverse();
				answer+=s.substring(i*2*k+k,i*2*k+k+(remainingChars-k) );
			}
			
		}
        return answer;
    }
}
