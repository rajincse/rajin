package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rajin on 8/19/2017.
 */
public class Utility {
    public static List<Integer> getIntList(int[] list){
        List<Integer> result = new ArrayList<Integer>();
        for(int elem: list)
        {
            result.add(elem);
        }
        return result;
    }
}
