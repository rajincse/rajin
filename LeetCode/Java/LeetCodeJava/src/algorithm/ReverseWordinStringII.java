package algorithm;

import java.util.Stack;

/**
 * Created by rajin on 11/21/2017.
 * 151. Reverse Words in a String
 * 186. Reverse Words in a String II
 *
 */
public class ReverseWordinStringII {
    public static void main(String[] args)
    {
        String s = "  aa    bb  ";

        String res = new ReverseWordinStringII().reverseWords(s);
        System.out.println("\""+res+"\"");
    }
    public String reverseWords(String s) {
        if(s== null || s.length()==0)
        {
            return s;
        }
        else
        {
            Stack<String> stack = new Stack<String>();
            StringBuilder sb = new StringBuilder();
            for(char ch : s.toCharArray())
            {
                if(ch ==' ')
                {
                    if(sb.length()>0)
                    {
                        stack.push(sb.toString());
                        sb.setLength(0);
                    }
                }
                else
                {
                    sb.append(ch);
                }
            }
            if(sb.length()>0)
            {
                stack.push(sb.toString());
                sb.setLength(0);
            }

            if(!stack.isEmpty()){
                sb.append(stack.pop());
            }

            while(!stack.isEmpty())
            {
                sb.append(' ');
                sb.append(stack.pop());
            }


            return sb.toString();
        }

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
