package algorithm;

/**
 * Created by rajin on 9/16/2017.
 */
public class ValidPalindrome2 {
    public static void main(String[] args)
    {

        String s ="cuppucu";
        ValidPalindrome2 obj = new ValidPalindrome2();
        System.out.println(obj.validPalindrome(s));
    }
    public boolean validPalindrome(String s) {
        if(s ==null || s.isEmpty() || s.length()<3)
        {
            return true;
        }
        char[] charArray = s.toCharArray();

        int i=0;
        int j = charArray.length-1;
        int lifeLine =1;
        while(i<j)
        {
            if(charArray[i] == charArray[j])
            {
                i++;
                j--;
            }
            else {
                if(charArray[i+1]==charArray[j])
                {
                    i++;
                    lifeLine--;
                }
                else if(charArray[i] ==charArray[j-1])
                {
                    j--;
                    lifeLine--;
                }
                else
                {
                    return false;
                }
            }
            if(lifeLine<0)
            {
                return false;
            }
        }

        return true;
    }

}
