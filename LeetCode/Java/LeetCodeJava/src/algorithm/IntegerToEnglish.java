package algorithm;

/**
 * Created by rajin on 11/21/2017.
 * 273. Integer to English Words
 */
public class IntegerToEnglish {
    String[] ones = new String[]{"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] twenties = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen"
            , "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = new String[]{"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if(num ==0)
        {
            return "Zero";
        }
        if(num<10)
        {
            return ones[num-1];
        }
        else if(num >= 10 && num <20)
        {
            return twenties[num -10];
        }
        else if(num < 100)
        {
            int msb = num / 10;
            StringBuilder sb = new StringBuilder();
            sb.append(tens[msb-2]);
            if(num%10 > 0)
            {
                sb.append(" ");
                sb.append(numberToWords(num%10));
            }

            return sb.toString();
        }
        else if(num < 1000)
        {
            return getWord(num, 100, "Hundred");
        }
        else if(num < 1000000)
        {
            return getWord(num, 1000, "Thousand");
        }
        else if(num < 1000000000){
            return getWord(num, 1000000, "Million");
        }
        else
        {
            return getWord(num, 1000000000, "Billion");
        }
    }

    public String getWord(int num, int upperbound, String middleName)
    {
        int part1 = num / upperbound;
        StringBuilder sb = new StringBuilder();
        sb.append(numberToWords(part1));
        sb.append(" ");
        sb.append(middleName);
        if(num % upperbound > 0)
        {
            sb.append(" ");
            sb.append(numberToWords(num%upperbound));
        }


        return sb.toString();
    }


    public static void main(String[] args)
    {
        int num = 1000;
        System.out.println(new IntegerToEnglish().numberToWords(num));
    }
}
