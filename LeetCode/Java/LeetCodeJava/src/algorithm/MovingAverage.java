package algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by rajin on 11/24/2017.
 * 346. Moving Average from Data Stream
 */
class MovingAverage {

    private Queue<Integer> queue;
    private int currentSum ;
    private int currentCount;
    private int size;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        this.queue = new LinkedList<Integer>();
        this.currentSum =0;
        this.currentCount =0;
    }

    public double next(int val) {
        if(this.currentCount< size)
        {
            this.currentCount++;

        }
        else
        {
            int headVal = queue.poll();
            this.currentSum -= headVal;
        }
        this.currentSum += val;
        queue.add(val);

        return 1.0 * this.currentSum / this.currentCount;
    }
}
