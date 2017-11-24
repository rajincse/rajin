package algorithm;

/**
 * Created by rajin on 11/21/2017.
 * 186. Reverse Words in a String II
 */
public class ReverseWordinStringII {
    public static void main(String[] args)
    {
        String s = "The Sky is Blue";
        char[] str = s.toCharArray();
        new ReverseWordinStringII().reverseWords(str);
        System.out.println(new String(str));
    }

    public void reverseWords(char[] str) {
        if(str ==null || str.length ==0)
        {
            return;
        }

        reverseWordsIndex(str, 0, str.length-1);
        int start =0;
        int end = start+1;

        while(end < str.length)
        {
            if(str[end] ==' ')
            {
                reverseWordsIndex(str, start, end-1);
                start = end+1;
                end = end+2;
            }
            else
            {
                end++;
            }
        }
        reverseWordsIndex(str, start, end-1);
    }
    public void reverseWordsIndex(char[] str, int start, int end)
    {
        if(start >=0 && end < str.length)
        {
            while(start < end)
            {
                char temp = str[start];
                str[start] = str[end];
                str[end] = temp;
                start++;
                end--;
            }
        }
    }
}
