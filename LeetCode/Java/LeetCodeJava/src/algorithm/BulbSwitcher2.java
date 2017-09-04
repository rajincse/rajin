package algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rajin on 9/2/2017.
 */
public class BulbSwitcher2 {
    public static void main(String[] args){
        int n =3;
        int m =2;
        System.out.println(new BulbSwitcher2().flipLights(n,m));
    }
    public int flipLights(int n, int m) {


        int status = (1 << n)-1;
        Set<Integer> uniqueStatus = new HashSet<Integer>();
        uniqueStatus.add(status);

        Set<Integer> tempStatus = new HashSet<Integer>();
        for(int i=0;i<m;i++)
        {

            for(Integer stat: uniqueStatus)
            {
                for(int s=0;s<4;s++)
                {
                    int newStatus = getStatus(stat,n,s);
                    if( !tempStatus.contains(newStatus))
                    {
                        tempStatus.add(newStatus);
                    }
                }

            }
            uniqueStatus.clear();
            uniqueStatus.addAll(tempStatus);
            tempStatus.clear();

//            System.out.print(i+"=>");
//            for(Integer e: uniqueStatus)
//            {
//                System.out.print(Integer.toBinaryString(e)+",");
//            }
//            System.out.println();
        }
        return uniqueStatus.size();
    }
    public int getStatus(int previousStatus,int n, int switchNumber)
    {
        int mask =0;
        if(switchNumber==0)
        {
            mask = (1<<n)-1;

        }
        else if(switchNumber==1){ //even bulbs -> odd indices

            int adder=1;
            for(int i=0;i<n;i++)
            {
                if(i%2!=0)
                {
                    mask=mask +adder;
                }
                adder<<=1;
            }

        }
        else if(switchNumber==2){ // odd bulbs -> even indices

            int adder=1;
            for(int i=0;i<n;i++)
            {
                if(i%2==0)
                {
                    mask=mask +adder;
                }
                adder<<=1;
            }
        }
        else // indices divisible 3
        {
            int adder=1;
            for(int i=0;i<n;i++)
            {
                if(i%3==0)
                {
                    mask=mask +adder;
                }
                adder<<=1;
            }

        }
        //System.out.println("Switch:"+switchNumber+", mask:"+Integer.toBinaryString(mask));
        return previousStatus^mask;

    }

}
