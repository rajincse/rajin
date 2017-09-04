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
    public static <T> String getListPrintString(List<List<T>> list){
        StringBuilder msg =new StringBuilder();
        msg.append("[");
        for(List<T> subList: list)
        {
            msg.append("\r\n");
            msg.append(subList.toString());

        }
        msg.append("\r\n]");
        return msg.toString();
    }
}
