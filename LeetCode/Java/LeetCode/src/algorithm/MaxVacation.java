package algorithm;

import java.util.Arrays;


public class MaxVacation {

	public static void main(String[] args){
		int [][] flights = new int[][]{{0,1,1},{1,0,1},{1,1,0}};//{{0,1,0},{0,0,0},{0,0,0}};//{{0,1,1},{1,0,1},{1,1,0}};//{{0,0,0},{0,0,0},{0,0,0}}
		int [][] days = new int[][] {{1,3,1},{6,0,3},{3,3,3}};//{{1,3,1},{6,0,3},{3,3,3}};// {{1,1,1},{7,7,7},{7,7,7}}//{{7,0,0},{0,7,0},{0,0,7}}
		
		MaxVacation obj = new MaxVacation();
		
		int vacationDays = obj.maxVacationDays(flights, days);
		
		System.out.println(print2DArray(flights)+" \n"+print2DArray(days)+"=>"+vacationDays);
				
		
	}
	public static String print2DArray(int [][] nums){
		String msg ="";
		for(int i=0;i<nums.length;i++){
			msg+="[";
			for(int j=0;j<nums[i].length;j++){
				msg+= nums[i][j]+", ";
			}
			msg+="]\n";
		}
		
		return msg;
	}
	public int maxVacationDays(int[][] flights, int[][] days) {
		int maxDays = getMaxVacationDynamic(flights, days);
		
		return maxDays;
    }
	
	public int getMaxVacationDynamic(int[][] flights, int[][] days){
		int n = days.length;
		int k = days[0].length;
		
		int[][] solutionMatrix = new int[n][k];
		
		for(int i=0;i<n;i++){
			solutionMatrix[i][k-1] = days[i][k-1];
		}
		
		for(int week=k-2;week >=0;week--){
			for(int sourceCity =0;sourceCity<n;sourceCity++){
				int max= 0;
				for(int destCity=0;destCity< n;destCity++){
					if(sourceCity == destCity || flights[sourceCity][destCity]==1){
						max = Math.max(max, solutionMatrix[destCity][week+1]);
					}
				}
				solutionMatrix[sourceCity][week] = max+days[sourceCity][week];
			}
		}
		int maxDays = 0;
		
		for(int city=0;city<n;city++){
			if(city ==0 || flights[0][city] ==1){
				maxDays = Math.max(maxDays, solutionMatrix[city][0]);
			}
			
		}
		
		
		return maxDays;
	}
	
	public int getMaxVacation(int city, int week, int[][] flights, int[][] days ){
		int n = days.length;
		int k = days[0].length;
		
		if(week == k ){
			return 0;
		}
		else{
			int val = days[city][week];
			int max = val;
			
			for(int i=0;i<n;i++){
				if(flights[city][i] ==1 || city==i){
					max = Math.max(max, val+getMaxVacation(i, week+1, flights, days));
				}
			}
			
			return max;
		}
	}
}
