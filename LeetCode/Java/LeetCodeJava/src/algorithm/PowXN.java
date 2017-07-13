package algorithm;

/**
 * Created by rajin on 7/12/2017.
 */
public class PowXN {
    public  static void main(String [] args){
        System.out.println(new PowXN().myPow(5,-3));
    }
    public double myPow(double x, int n) {
        if(x ==1){
            return 1;

        }
        if(x ==-1){
            return n%2==0? 1: -1;
        }
        if(n== Integer.MIN_VALUE){
            return 0;
        }
        if(n==0){
            return  1;
        }

        if(n<0){
            x = 1.0/x;
            n = -n;
        }
        double result = 1;
        while( n > 0){
            if(n%2 != 0){
                result = result*x;
            }
            x=x*x;
            n=n/2;
        }
        return result;
    }
}
