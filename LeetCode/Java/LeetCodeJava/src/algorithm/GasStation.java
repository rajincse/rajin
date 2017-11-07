package algorithm;

import java.util.Arrays;

/**
 * Created by rajin on 11/3/2017.
 */
public class GasStation {
    public static void main(String[] args){
        int[] gas = new int[]{5,2,3,7};
        int[] cost = new int[]{5,3,1,8};
        System.out.println( new GasStation().canCompleteCircuit(gas, cost));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas.length ==0 || gas.length != cost.length){
            return -1;
        }
        int sum =0;
        int result  =0;
        int total =0;
        for(int i=0;i<gas.length;i++)
        {
            sum+=gas[i] - cost[i];
            total+= gas[i] - cost[i];
            if(sum < 0)
            {
                sum = 0;
                result = i+1;
            }
        }
        return total<0? -1:result;
    }
}
