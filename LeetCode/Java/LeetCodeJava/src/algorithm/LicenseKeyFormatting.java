package algorithm;

import java.util.Stack;

/**
 * Created by rajin on 11/24/2017.
 * 482. License Key Formatting
 */
public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String S, int K) {
        char[] charArr = S.toCharArray();
        Stack<Character> charStack = new Stack<Character>();
        for(char ch: charArr)
        {
            if(ch != '-')
            {
                charStack.push(Character.toUpperCase(ch));
            }
        }

        StringBuilder sb = new StringBuilder();
        int count =0;
        while(!charStack.isEmpty())
        {
            if(count > 0 && count % K==0)
            {
                sb.append('-');
            }
            Character ch = charStack.pop();
            sb.append(ch);
            count++;

        }
        sb = sb.reverse();

        return sb.toString();
    }
}
