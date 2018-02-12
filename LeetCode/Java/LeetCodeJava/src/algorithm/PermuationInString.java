package algorithm;

/**
 * Created by rajin on 2/10/2018.
 * 567. PermuationInString
 */
public class PermuationInString {
    public static void main(String[] args)
    {
        String s1 = "adc";
        String s2 = "dcda";
        System.out.println(new PermuationInString().checkInclusion(s1, s2));
    }
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length())
        {
            return false;
        }
        int[] charMap1 = new int[26];
        int[] charMap2 = new int[26];
        for(int i=0;i<s1.length();i++)
        {
            charMap1[s1.charAt(i)-'a']++;
            charMap2[s2.charAt(i)-'a']++;
        }


        int target = 0x03FFFFFF;
        int currentState =0;
        for(int i=0;i<charMap1.length;i++)
        {
            currentState = setBitAt(currentState, i, charMap1[i]==charMap2[i]);
        }
        if(currentState == target)
        {
            return true;
        }
        int len = s1.length();

        for(int i=1;i<s2.length()-len+1;i++)
        {
            char headChar = s2.charAt(i-1);
            int headCharIndex = headChar-'a';
            charMap2[headCharIndex]--;
            currentState = setBitAt(currentState, headCharIndex, charMap1[headCharIndex]==charMap2[headCharIndex]);

            char tailChar = s2.charAt(i+len-1);
            int tailCharIndex = tailChar-'a';
            charMap2[tailCharIndex]++;
            currentState = setBitAt(currentState, tailCharIndex, charMap1[tailCharIndex]==charMap2[tailCharIndex]);

            if(currentState == target)
            {
                return true;
            }

        }
        return false;
    }

    public int setBitAt(int num, int index, boolean value)
    {
        int oneOrZero = value? 1: 0;
        int mask = ~(1 << index);
        return (num & mask) | (oneOrZero << index);
    }
}
