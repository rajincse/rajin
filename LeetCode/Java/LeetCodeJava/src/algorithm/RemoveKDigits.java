package algorithm;

/**
 * Created by rajin on 1/9/2018.
 */
public class RemoveKDigits {
    public static void main(String[] args)
    {
        int a = 80;
        int b = 50;

        System.out.print(a+", "+b+", ");
        while(b>0)
        {
            int c = a-b;
            System.out.print(c+", ");
            a = b;
            b= c;
        }

//        String num ="1432219";
//        int k =3;
       // System.out.println(new RemoveKDigits().removeKdigits(num, k));

    }
    public String removeKdigits(String num, int k) {
        if(num.length() ==k)
        {
            return "0";
        }

        StringBuilder sb = new StringBuilder(num);
        for(int j=0;j<k;j++)
        {
            int i=0;
            while(i<sb.length()-1 && sb.charAt(i) <= sb.charAt(i+1))
            {
                i++;
            }
            sb.delete(i,i+1);
        }
        while (sb.length() > 1 && sb.charAt(0) =='0')
        {
            sb.delete(0,1);
        }
        return  sb.length() ==0? "0":sb.toString();
    }
}
