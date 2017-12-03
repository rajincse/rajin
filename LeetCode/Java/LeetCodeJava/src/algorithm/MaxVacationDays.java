package algorithm;

/**
 * Created by rajin on 11/26/2017.
 * 568. Maximum Vacation Days
 */
public class MaxVacationDays {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int[][] mem = new int[days.length][days[0].length];
        for(int i=0;i<mem.length;i++)
        {
            for(int j=0;j<mem[0].length;j++)
            {
                mem[i][j] =-1;
            }
        }
        int max =getMaxVacation(0,0,flights, days, mem);
        for(int i=1;i<days.length;i++)
        {
            if(flights[0][i]==1)
            {
                max = Math.max(max, getMaxVacation(i,0,flights, days, mem));
            }

        }
        return max;
    }
    public int getMaxVacation(int city, int week, int[][] flights, int[][] days,int[][] mem ){
        int n = days.length;
        int k = days[0].length;

        if(week == k ){
            return 0;
        }
        if(mem[city][week]>=0)
        {
            return mem[city][week];
        }
        else if(week==k-1)
        {
            mem[city][week] = days[city][week];
            return mem[city][week];
        }
        else{
            int val = days[city][week];
            int max = 0;

            for(int i=0;i<n;i++){
                if(flights[city][i] ==1 || city==i){
                    max = Math.max(max, getMaxVacation(i, week+1, flights, days, mem));
                }
            }
            mem[city][week] = val+max;

            return mem[city][week];
        }
    }
}
