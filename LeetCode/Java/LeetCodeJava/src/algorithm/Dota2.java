package algorithm;

import java.util.ArrayList;

/**
 * Created by rajin on 7/29/2017.
 */
public class Dota2 {
    public static void main(String[] args)
    {
        System.out.println(new Dota2().predictPartyVictory("RRDDDRRRDRDRDDDDDDDDD"));
    }
    public String predictPartyVictory(String senate) {

        char[] senateChars = senate.toCharArray();

        int radiantCount =0;
        int direCount = 0;
        ArrayList<Character> senateQueue = new ArrayList<Character>();
        for(char c: senateChars)
        {
            senateQueue.add(c);
            if(c=='R'){
                radiantCount++;
            }
            else {
                direCount++;
            }
        }
        int radiantSemaphore =0;
        int direSemaphore =0;

        while(radiantCount >0 && direCount >0)
        {
            Character c = senateQueue.remove(0);
            if(c=='R')
            {
                if(radiantSemaphore==0)
                {
                    direSemaphore++;
                    senateQueue.add(c);
                }
                else
                {
                    radiantSemaphore--;
                    radiantCount--;
                }

            }
            else{
                if(direSemaphore==0)
                {
                    radiantSemaphore++;
                    senateQueue.add(c);
                }
                else
                {
                    direSemaphore--;
                    direCount--;
                }
            }
        }

        return radiantCount>0? "Radiant":"Dire";


    }
}
