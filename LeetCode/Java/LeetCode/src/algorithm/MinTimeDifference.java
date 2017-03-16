package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinTimeDifference {

	public static void main(String[] args)
	{
		ArrayList<String> timePoints = new ArrayList<String>();
		timePoints.add("22:00");
		timePoints.add("23:45");
		timePoints.add("00:30");
		int diff = new MinTimeDifference().findMinDifference(timePoints);
		System.out.println("Time diff:"+diff);
	}
	public int getMinutes(String time)
	{
		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(3));
		
		return hour *60+minute;
	}
	public int minTime(String time1, String time2)
	{
		int time1Val = getMinutes(time1);
		int time2Val = getMinutes(time2);
		int minDiff = Math.abs(time1Val - time2Val);
		minDiff = Math.min(minDiff, Math.abs(time1Val + 24 * 60 - time2Val));
		minDiff = Math.min(minDiff, Math.abs(time2Val + 24 * 60 - time1Val));
				
		return minDiff;
	}
	
	public int findMinDifference(List<String> timePoints) {
		
		int minDiff = Integer.MAX_VALUE;
		Collections.sort(timePoints);
		
		String prevTime = ""; 
		String firstTime ="";
		for(String time: timePoints)			
		{
			if(prevTime.equals(""))
			{
				prevTime = time;
				firstTime=time;
			}
			else
			{
				minDiff = Math.min(minDiff, minTime(prevTime, time));
				prevTime = time;
			}
			
		}
		
		minDiff = Math.min(minDiff, minTime(prevTime, firstTime));
        return minDiff;
    }
}
