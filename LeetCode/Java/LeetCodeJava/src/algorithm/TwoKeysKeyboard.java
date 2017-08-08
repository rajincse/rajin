package algorithm;

/**
 * Created by rajin on 7/29/2017.
 */
public class TwoKeysKeyboard {
    public static void main(String[] args)
    {
        System.out.println(new TwoKeysKeyboard().minSteps(36));
    }

    public int minSteps(int n) {

        if(n==1)
        {
            return 0;
        }

        int minStepNumber = n;
        for(int i=2;i<=n/2;i++)
        {
            if(n%i==0)
            {
                System.out.println("Factor of "+n+"="+i);
                minStepNumber = Math.min(minStepNumber, (i -1)+1+ minSteps(n/i));
            }
        }
        System.out.println("Minsteps("+n+")="+minStepNumber);
        return minStepNumber;
    }
}
