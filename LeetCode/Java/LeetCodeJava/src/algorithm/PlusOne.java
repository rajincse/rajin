package algorithm;

import java.util.Arrays;

/**
 * Created by rajin on 7/27/2017.
 */
public class PlusOne {
    public static void main(String[] args)
    {
        int[] digits = new int[]{9,9,9};
        System.out.println(Arrays.toString(new PlusOne().plusOne(digits)));
    }

    public int[] plusOne(int[] digits) {

        int carry =1;
        for(int i=digits.length-1;i>=0;i--)
        {
            int d = digits[i];
            d = d+carry;
            carry = d/10;
            d = d%10;
            digits[i] = d;
        }

        if(carry ==1)
        {
            int[] newDigits = new int[digits.length+1];
            newDigits[0] = carry;
            for(int i=0;i<digits.length;i++)
            {
                newDigits[i+1] = digits[i];
            }
            return newDigits;
        }
        else
        {
            return  digits;
        }

    }
}
