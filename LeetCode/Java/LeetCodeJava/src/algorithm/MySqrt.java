package algorithm;

/**
 * Created by rajin on 8/3/2017.
 */
public class MySqrt {

    public static void main(String []args)
    {
        int x = 2147395600;
//        int x = 125;
        System.out.println(new MySqrt().mySqrt(x)+", "+Math.sqrt(x));
    }

    public int mySqrt(int x) {

        if(x < 1)
        {
            return 0;
        }


        return getSqrtNewton(x);
    }

    public int getMySqrtBinarySearch(int x, int start, int end)
    {
        System.out.println(" start="+start+", end="+end);
        if(start+1 >= end )
            return start;
        else
        {
            int mid = (int)(1.0*start+1.0*end)/2;

            double mul = 1.0 *mid * mid ;
            if(mul ==x)
            {
                return mid;
            }
            else if(mul > x)
            {
                return  getMySqrtBinarySearch(x, start, mid);
            }
            else {
                return  getMySqrtBinarySearch(x, mid, end);
            }
        }
    }

    public int getSqrtNewton(int x)
    {
        long res = 10; // intial guess;
        long prevRes = -1;
        while(Math.abs(res - prevRes) >1)
        {
            System.out.println("Res:"+res);
            prevRes = res;
            res = (prevRes* prevRes + x) / ( 2* prevRes);
        }

        return (int)res;
    }


}
