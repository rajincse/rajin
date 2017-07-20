package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by rajin on 7/19/2017.
 */
class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString() {
        return "{" +
                 start +
                "," + end +
                '}';
    }
}
class SortedInterval implements  Comparable<SortedInterval>{
    Interval interval;
    public SortedInterval(Interval interval){
        this.interval = interval;
    }

    @Override
    public int compareTo(SortedInterval o) {
        return this.interval.start - o.interval.start;
    }
    @Override
    public String toString() {
        return "{" +
                this.interval.start +
                "," + this.interval.end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SortedInterval that = (SortedInterval) o;

        return interval != null ? (interval.start == that.interval.start && interval.end ==that.interval.end) : that.interval == null;
    }

    @Override
    public int hashCode() {
        return interval != null ? interval.hashCode() : 0;
    }

    public  static  List<SortedInterval> getSortedInterval(List<Interval> intervals){
        ArrayList<SortedInterval> sortedIntervals = new ArrayList<>();
        for(Interval i: intervals){
            sortedIntervals.add(new SortedInterval(i));
        }
        Collections.sort(sortedIntervals);
        return  sortedIntervals;
    }
    public static List<Interval> getIntervalList(List<SortedInterval> sortedIntervals){
        ArrayList<Interval> intervals = new ArrayList<>();
        for(SortedInterval s: sortedIntervals){
            intervals.add(s.interval);
        }
        return  intervals;
    }
}
public class MergeIntervals {

    public static void main(String[] args){
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(7,12));
        intervals.add(new Interval(11,13));
        intervals.add(new Interval(15,18));
        intervals.add(new Interval(16,17));
        List<Interval> result = new MergeIntervals().merge(intervals);
        System.out.println(intervals+" =>"+result);
    }

    public List<Interval> merge(List<Interval> intervals) {

        List<SortedInterval> result = getMergedList(SortedInterval.getSortedInterval(intervals));

        return  SortedInterval.getIntervalList(result);
    }
    public List<SortedInterval> getMergedList(List<SortedInterval> intervals) {
        if(intervals.size()<2){
            return  intervals;
        }
        else
        {
            ListIterator<SortedInterval> iterator = intervals.listIterator();
            ArrayList<SortedInterval> result = new ArrayList<>();

            SortedInterval i1 = iterator.next();
            while (iterator.hasNext()){
                SortedInterval i2 = iterator.next();
                if(i1.interval.end>= i2.interval.start){
                    i1 = new SortedInterval(new Interval(i1.interval.start, Math.max(i1.interval.end,i2.interval.end)));
                }
                else
                {
                    result.add(i1);
                    i1 = i2;
                }
            }
            result.add(i1);

            return  result;
        }

    }

}
