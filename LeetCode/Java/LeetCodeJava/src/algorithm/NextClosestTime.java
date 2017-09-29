package algorithm;

import java.util.*;

/**
 * Created by rajin on 9/23/2017.
 * 681. Next Closest Time 
 */
public class NextClosestTime {
    class ValArray
    {
        int val;
        int[] array;
        public ValArray(int val, int[] array)
        {
            this.val = val;
            this.array = array;
        }

        @Override
        public String toString() {
            return ""+ val +
                    ", " + Arrays.toString(array) ;
        }
    }

    public  static  void main(String[] args){
        String time ="23:59";
        int[] timeArray = new int[]{4,4,8,1};

        NextClosestTime obj = new NextClosestTime();
        System.out.println(obj.nextClosestTime(time));
        boolean isAllowed =obj.isAllowed(timeArray);
        System.out.println(isAllowed);
    }

    public String nextClosestTime(String time) {

        String[] parts = time.split(":");
        Set<Integer> digitSet = new HashSet<Integer>();
        char[] hourChars = parts[0].toCharArray();
        char[] minChars = parts[1].toCharArray();

        int[] timeArray = new int[4];


        switch (minChars.length)
        {
            case 0:
                timeArray[0] = 0;
                timeArray[1] = 0;
                break;
            case 1:
                int digit = minChars[0]-'0';
                if(!digitSet.contains(digit))
                {
                    digitSet.add(digit);
                }
                timeArray[0] = digit;
                timeArray[1] =0;
                break;
            case 2:
                for(int i=0;i<minChars.length;i++)
                {
                    digit = minChars[i]-'0';
                    timeArray[1-i] = digit;
                    if(!digitSet.contains(digit))
                    {
                        digitSet.add(digit);
                    }
                }
                break;
        }
        switch (hourChars.length)
        {
            case 0:
                timeArray[2] = 0;
                timeArray[3] = 0;
                break;
            case 1:
                int digit = hourChars[0]-'0';
                timeArray[2] = digit;
                if(!digitSet.contains(digit))
                {
                    digitSet.add(digit);
                }
                timeArray[3] =0;
                break;
            case 2:
                for(int i=0;i<hourChars.length;i++)
                {
                    digit = hourChars[i]-'0';
                    timeArray[3-i] = digit;
                    if(!digitSet.contains(digit))
                    {
                        digitSet.add(digit);
                    }
                }
                break;
        }

        int[] digitArray = new int[digitSet.size()];
        int index=0;
        for(Integer d: digitSet)
        {
            digitArray[index]=d.intValue();
            index++;
        }
        Arrays.sort(digitArray);


        int twentyFourHours = 60*24;
        int currentTime = getValue(timeArray);
        int minDistance = twentyFourHours;

        ValArray valArray = new ValArray(minDistance, timeArray);
        valArray =getMinTime(timeArray,0, currentTime,valArray, digitArray);
        String timeString = getTimeString(valArray.array);
        return timeString;
    }

    public boolean isAllowed(int[] timeArray)
    {
        if(timeArray[1] > 5)
        {
            return false;
        }

        if(timeArray[3]==2 && timeArray[2]>3)
        {
            return false;
        }

        if(timeArray[3] > 2)
        {
            return false;
        }

        return true;
    }
    public String getTimeString(int[] timeArray)
    {
        StringBuilder timeString =new StringBuilder();
        timeString.append(timeArray[3]);
        timeString.append(timeArray[2]);
        timeString.append(':');
        timeString.append(timeArray[1]);
        timeString.append(timeArray[0]);
        return timeString.toString();
    }

    public ValArray getMinTime(int[] timeArray, int nextIndex, int currentTime, ValArray minTimeDistance, int[] digitArray)
    {

        if(nextIndex == 4)
        {
            int val = getValue(timeArray);
            int distance = getDistance(currentTime, val);

            if(distance < minTimeDistance.val )
            {
                //System.out.println(getTimeString(timeArray)+", dist:"+distance);
                int[] arr = new int[timeArray.length];
                System.arraycopy(timeArray, 0, arr, 0, timeArray.length);
                return new ValArray(distance, arr);
            }
        }
        else
        {

            ValArray minDistance = minTimeDistance;
            for(int i=0;i<digitArray.length;i++)
            {
                int puttingDigit = digitArray[i];
                int digit = timeArray[nextIndex];
                timeArray[nextIndex] = puttingDigit;
                if(isAllowed(timeArray))
                {

                    timeArray[nextIndex] = digitArray[i];
                    minDistance = getMinTime(timeArray, nextIndex+1, currentTime, minDistance, digitArray);
                }
                timeArray[nextIndex] = digit;
            }
            return minDistance;
        }


        return minTimeDistance;

    }
    public int getDistance(int current, int next)
    {
        if(next> current)
        {
            return next-current;
        }
        else
        {
            return next-current+60*24;
        }
    }
    public  int getValue(int[] timeArray)
    {
        int[] timeCoeff = new int[]{1,10,60,600};
        int val =0;
        for(int i=0;i<timeArray.length;i++)
        {
            val = val + timeArray[i] * timeCoeff[i];
        }
        return val;
    }
}
