package algorithm;

/**
 * Created by rajin on 10/7/2017.
 */
public class BinaryNumberAlternatingBits {
    public static  void main(String[] args)
    {
        int n =1;
        System.out.println(new BinaryNumberAlternatingBits().hasAlternatingBits(n));
    }

    public boolean hasAlternatingBits(int n) {

        boolean firstBit=true;
        int lastBit =0;
        while (n> 0)
        {
            int bit = n%2;
            n = n/2;
            if(firstBit)
            {
                firstBit = false;
                lastBit =bit;
                continue;
            }
            if(bit == lastBit)
            {
                return false;
            }
            lastBit = bit;

        }
        return true;
    }
}
