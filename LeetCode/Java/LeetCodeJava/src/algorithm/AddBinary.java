package algorithm;

/**
 * Created by rajin on 7/27/2017.
 */
public class AddBinary {
    public static  void main(String[] args)
    {
        String a ="0";
        String b ="1";
        System.out.println(new AddBinary().addBinary(a,b));
    }
    public String addBinary(String a, String b) {
        char[] longNumber = a.length() > b.length()? a.toCharArray():b.toCharArray();
        char[] shortNumber = a.length()> b.length()? b.toCharArray(): a.toCharArray();
        StringBuilder result = new StringBuilder();
        int carry =0;
        int radix =2;
        for(int i=longNumber.length-1;i>=0;i--)
        {
            int longNumberDigit = longNumber[i]=='1'?1:0;
            int shortNumberDigit =  i - (longNumber.length-shortNumber.length) >=0 && shortNumber[i - (longNumber.length-shortNumber.length)]=='1'?1:0;

            int val = longNumberDigit+shortNumberDigit+carry;
            carry  = val / radix;
            val = val % radix;
            result.append(val);

        }

        if(carry >0)
        {
            result.append(carry);
        }
        return result.reverse().toString();
    }
}
