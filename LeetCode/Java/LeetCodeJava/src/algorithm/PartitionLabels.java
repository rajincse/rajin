package algorithm;

import java.util.*;

/**
 * Created by rajin on 1/13/2018.
 */
public class PartitionLabels {
    public static void main(String[] args)
    {
        String S = S = "ababcbacadefegdehijhklij";
        System.out.println(new PartitionLabels().partitionLabels(S));
    }
    class Interval implements Comparable<Interval>
    {
        char c;
        int start;
        int end;

        public Interval(char c, int start, int end) {
            this.c = c;
            this.start = start;
            this.end = end;
        }

        public boolean intersects(Interval other)
        {
            if(this.start<= other.start && this.end >= other.end)
            {
                return true;
            }
            else if(this.start >= other.start && this.end <=other.end)
            {
                return true;
            }
            else if( this.end >= other.start && this.end <= other.end)
            {
                return true;
            }
            else if(this.start>= other.start && this.start <= other.end)
            {
                return true;
            }
            else
            {
                return false;
            }
        }


        @Override
        public int compareTo(Interval o) {
            return this.start -o.start;
        }

        @Override
        public String toString() {
            return "{" +
                    "c=" + c +
                    ", start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
    public List<Integer> partitionLabels(String S) {

        int[] startIndex = new int[26];
        int[] endIndex = new int[26];
        for(int i=0;i<startIndex.length;i++)
        {
            startIndex[i] = -1;
            endIndex[i] = -1;
        }
        char[] charArray = S.toCharArray();
        for(int i=0;i<charArray.length;i++)
        {
            int charIndex = charArray[i] - 'a';
            if(startIndex[charIndex] <0)
            {
                startIndex[charIndex] = i;
            }
            endIndex[charIndex] =i;
        }

        Queue<Interval> intervalList = new LinkedList<Interval>();
        for(char c ='a';c<='z';c++)
        {
            if(startIndex[c-'a']>=0)
            {
                Interval interval = new Interval(c, startIndex[c-'a'], endIndex[c-'a']);
                Queue<Interval> buffer = new LinkedList<Interval>();
                while(!intervalList.isEmpty())
                {
                    Interval i1 = intervalList.poll();
                    if(interval.intersects(i1))
                    {
                        interval = getNewInterval(i1, interval);
                    }
                    else {
                        buffer.offer(i1);
                    }
                }
                buffer.offer(interval);
                intervalList = buffer;
            }

        }
        ArrayList<Interval> sortedList = new ArrayList<Interval>(intervalList);
        Collections.sort(sortedList);
        List<Integer> result = new ArrayList<Integer>();
        for(Interval i: sortedList)
        {
            result.add(i.end - i.start+1);
        }

        return result;
    }

    public Interval getNewInterval(Interval i1, Interval i2)
    {

        int start = Math.min(i1.start, i2.start);
        int end = Math.max(i1.end, i2.end);
        return new Interval('#', start, end);
    }

}
