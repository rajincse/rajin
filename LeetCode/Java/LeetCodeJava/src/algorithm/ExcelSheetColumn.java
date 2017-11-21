package algorithm;

/**
 * Created by rajin on 11/21/2017.
 *  171. Excel Sheet Column Number
 */
public class ExcelSheetColumn {
    public int titleToNumber(String s) {
        int val =0;
        char[] chars = s.toCharArray();
        for(char c : chars)
        {
            val *=26;
            val += c-'A'+1;
        }
        return val;
    }

    public static void main(String[] args)
    {
        String s = "AB";
        System.out.println(new ExcelSheetColumn().titleToNumber(s));
    }
}
