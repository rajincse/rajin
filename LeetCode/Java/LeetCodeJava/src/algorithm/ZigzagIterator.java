package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 11/24/2017.
 * 281. Zigzag Iterator
 */
public class ZigzagIterator {

    List<Integer> nums;
    int index;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.nums = new ArrayList<Integer>();
        this.index =0;
        int i1=0;
        int i2 =0;
        boolean first = i1<v1.size();
        while(i1<v1.size() || i2< v2.size())
        {
            if(first)
            {
                nums.add(v1.get(i1));
                i1++;
            }
            else
            {
                nums.add(v2.get(i2));
                i2++;
            }

            if(i1<v1.size() && i2< v2.size()){
                first = !first;
            }
            else if(i1<v1.size())
            {
                first = true;
            }
            else
            {
                first = false;
            }
        }
    }

    public int next() {
        int val = nums.get(index);
        index++;

        return val;
    }

    public boolean hasNext() {
        return index < nums.size();
    }
}
