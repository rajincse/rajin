package algorithm;

import java.util.*;

/**
 * Created by rajin on 11/18/2017.
 */

class MyCalendar {

    private TreeMap<Integer, Integer> map;
    public MyCalendar() {

        this.map = new TreeMap<Integer, Integer>();
    }

    public boolean book(int start, int end) {
        for(Integer entryStart: map.keySet())
        {
            int entryEnd = entryStart+ map.get(entryStart)-1;
            if(start >= entryStart &&  start <=entryEnd )
            {
                return false;
            }
            if(end-1>= entryStart && end-1 <= entryEnd)
            {
                return false;
            }
            if(start< entryStart && end-1 > entryEnd)
            {
                return false;
            }
        }

        map.put(start, end- start);
        return true;
    }
}

class MyCalendarTwo {
    class Entry{
        int start;
        int end;
        int count;

        public Entry(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public List<Entry> list;
    public MyCalendarTwo() {

        this.list = new ArrayList<Entry>();
    }


    public boolean book(int start, int end) {
        MyCalendar c = new MyCalendar();
        for (Entry e : list) {
            if (Math.max(e.start, start) < Math.min(e.end, end)) //overlap exist
            {
                if(!c.book(Math.max(e.start, start), Math.min(e.end, end)))
                {
                    return false;
                }
            }
        }
        list.add(new Entry(start, end));
        return true;
    }

}
public class MyCalendarI {
    public static  void main(String[] args)
    {
        MyCalendarTwo calc = new MyCalendarTwo();
//        int[] start = new int[]{ 5,42,4,33,2,16, 7, 6,13,38,49, 6, 5,35};
//        int[] end = new int[]{  12,50,9,41,7,25,16,11,18,43,50,15,13,42};

        int[] start = new int[]{42,33,38,35};
        int[] end = new int[]{  50,41,43,42};

        for(int i=0;i<start.length;i++)
        {
            System.out.println(start[i]+", "+end[i]+"=>"+calc.book(start[i], end[i]));
        }

    }

}
