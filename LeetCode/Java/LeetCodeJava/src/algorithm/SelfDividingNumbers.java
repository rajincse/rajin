package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 11/18/2017.
 */
public class SelfDividingNumbers {
    public static  void main(String [] args)
    {
        int left = 1;
        int right =22;
        List<Integer> result = new SelfDividingNumbers().selfDividingNumbers(left, right);
        System.out.println(result);
        new SelfDividingNumbers().isSelfDividing(21);
    }
    public List<Integer> selfDividingNumbers(int left, int right) {

        List<Integer> result = new ArrayList<Integer>();
        for(int i =left;i<=right;i++)
        {
            if(isSelfDividing(i))
            {
                result.add(i);
            }
        }
        return result;
    }

    public boolean isSelfDividing(int num)
    {
        if(num < 10)
        {
            return true;
        }
        int currentNum = num;
        int digit = currentNum %10;


        while (currentNum > 0)
        {
            if(digit ==0)
            {
                return false;
            }
            if (num % digit != 0)
            {
                return false;
            }
            currentNum /=10;
            digit = currentNum % 10;
        }

        return true;


    }

}
