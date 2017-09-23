package algorithm;

import java.util.ArrayList;

/**
 * Created by rajin on 9/16/2017.
 */
class MapSum {

    /** Initialize your data structure here. */
    private ArrayList<String> keyList;
    private ArrayList<Integer> valueList;
    public MapSum() {
        this.keyList = new ArrayList<String>();
        this.valueList = new ArrayList<Integer>();

    }

    public void insert(String key, int val) {
        if(keyList.contains(key))
        {
            int index = keyList.indexOf(key);
            valueList.set(index,val);
        }
        else
        {
            this.keyList.add(key);
            this.valueList.add(val);
        }

    }

    public int sum(String prefix) {
        int sum =0;
        for(int i=0;i<keyList.size();i++)
        {
            if(keyList.get(i).startsWith(prefix))
            {
                sum+= valueList.get(i);
            }
        }
        return sum;

    }
}
public class MapSumPairs {
    public static void main(String[] args)
    {
        MapSum obj = new MapSum();
        obj.insert("aa", 3);
        System.out.println(obj.sum("a"));
        obj.insert("aa",2);
        System.out.println(obj.sum("a"));
    }

}
