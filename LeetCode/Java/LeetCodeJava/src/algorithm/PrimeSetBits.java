package algorithm;

/**
 * Created by rajin on 1/13/2018.
 */
public class PrimeSetBits {
    public static void main(String[] args)
    {
        int L = 842;
        int R = 888;

        System.out.println(new PrimeSetBits().countPrimeSetBits(L, R));

    }
    public int countPrimeSetBits(int L, int R) {

        int count =0;
        for(int i =L;i<=R;i++)
        {
            int bit = getBitCount(i);
            if(isPrime(bit))
            {
                count++;
            }
        }
        return count;
    }
    public int getBitCount(int n)
    {
        int mask =1;
        int count =0;
        while (mask < n)
        {
            if((n & mask) > 0)
            {
                count++;
            }
            mask = mask << 1;
        }

        return count;

    }
    public boolean isPrime(int n)
    {
        if(n< 2) return false;
        else if(n ==2) return true;
        else if(n%2 ==0) return false;
        else {
            for(int i=3;i*i <=n;i+=2)
            {
                if(n%i ==0)
                {
                    return false;
                }
            }
            return true;
        }
    }
}
