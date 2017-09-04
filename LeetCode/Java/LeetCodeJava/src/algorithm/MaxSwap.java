package algorithm;

import java.util.ArrayList;

/**
 * Created by rajin on 9/2/2017.
 */
public class MaxSwap {
    public static void main(String [] args)
    {
        int num = 12;
        System.out.println(new MaxSwap().maximumSwap(num));
    }

    public int maximumSwap(int num) {
        if(num < 10)
        {
            return num;
        }
        int radix =10;
        int currentVal = num;

        ArrayList<Integer> digits = new ArrayList<Integer>();
        while (currentVal> 0)
        {
            int digit = currentVal% radix;
            digits.add(digit);
            currentVal = currentVal / radix;
        }
        int end = digits.size()-1;
        int start =0;
        while (end>0){
            int maxIndex = getMaxDigitIndex(digits,start,end-1);
            if(digits.get(maxIndex) > digits.get(end))
            {
                int temp =digits.get(maxIndex);
                digits.set(maxIndex, digits.get(end));
                digits.set(end, temp);
                break;
            }
            else
            {
                end--;
            }

        }

        int maxVal = getVal(digits);
        return maxVal;
    }

    public int getVal(ArrayList<Integer> digits)
    {
        int val =0;
        int radixMultiplier = 1;
        for(Integer digit: digits)
        {
            val = val+ radixMultiplier*digit;
            radixMultiplier= radixMultiplier*10;
        }
        return val;
    }

    public int getMaxDigitIndex(ArrayList<Integer> digits, int start, int end){
        int max = Integer.MIN_VALUE;
        int maxIndex =-1;
        for(int i=start;i<=end;i++)
        {
            if(digits.get(i) > max)
            {
                maxIndex = i;
                max = digits.get(i);
            }
        }
        return maxIndex;
    }

}
