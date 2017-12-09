package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by rajin on 12/3/2017.
 * 380. Insert Delete GetRandom O(1)
 */
public class InsertDeleteRandomO1 {
    public static void main(String[] args)
    {
        InsertDeleteRandomO1 obj = new InsertDeleteRandomO1();
        obj.test();
    }

    public  void test()
    {
        RandomizedSet set = new RandomizedSet();
        boolean result = set.insert(1);
        result =set.remove(2);
        result =set.insert(1);
        int val = set.getRandom();
        result =set.remove(1);
        result =set.insert(2);
        val = set.getRandom();
    }
    class RandomizedSet {

        /** Initialize your data structure here. */
        HashMap<Integer, Integer> indexMap;
        ArrayList<Integer> items;
        Random rand;
        public RandomizedSet() {
            indexMap = new HashMap<Integer, Integer>();
            items = new ArrayList<Integer>();
            rand = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(indexMap.containsKey(val))
            {
                return false;
            }
            else {
                items.add(val);
                indexMap.put(val, items.size()-1);

                return true;
            }
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(items.isEmpty())
            {
                return false;
            }
            else if(!indexMap.containsKey(val))
            {
                return false;
            }
            else {
                int index = indexMap.get(val);
                if(index < items.size()-1) //not last index swap
                {
                    int lastItem = items.get(items.size()-1);
                    items.set(index, lastItem);
                    items.set(items.size()-1, val);
                    indexMap.put(lastItem, index);
                }
                indexMap.remove(val);
                items.remove(items.size()-1);

                return true;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int randomIndex = rand.nextInt(items.size()) ;

            return items.get(randomIndex);
        }
    }
}
