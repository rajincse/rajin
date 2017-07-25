package algorithm;

/**
 * Created by rajin on 7/22/2017.
 */
public class PalindromicSubstring {
    public static void main(String[] args) {
        String s ="abac";
        System.out.println(new PalindromicSubstring().countSubstrings(s));
    }
    public  boolean isPalindrom(String str)
    {
        StringBuilder sb = new StringBuilder(str);
        String rev = sb.reverse().toString();
        return str.equals(rev);
    }

    public int countSubstrings(String s) {
        int len = s.length();
        int sum =0;
        for(int i=0;i<len;i++)
        {
            for(int j=i;j<len;j++)
            {
                String substr = s.substring(i,j+1);
                if(isPalindrom(substr))
                {
                    sum++;
                }
            }
        }

        return  sum;
    }
}
