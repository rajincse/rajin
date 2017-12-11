package algorithm;

/**
 * Created by rajin on 12/9/2017.
 * 168. Excel Sheet Column Title
 */
public class ExcelColumnTitle {
    public static void main(String[] args)
    {
        int n = 27;
        System.out.println(new ExcelColumnTitle().convertToTitle(n));
    }

    public String convertToTitle(int n) {

        StringBuilder sb = new StringBuilder();
        n--; //0 indexed
        int radix = 26;
        while(n>= 26)
        {
            char ch = (char)('A'+n% radix);
            sb.append(ch);
            n = n/ radix -1;
        }
        char ch = (char)('A'+n);
        sb.append(ch);

        sb = sb.reverse();

        return sb.toString();
    }
}
