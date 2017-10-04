package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rajin on 10/4/2017.
 *  120. Triangle
 */
public class Triangle {
    public static void main(String[] args){
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        List<Integer> row = new ArrayList<Integer>();
        row.add(2);
        triangle.add(new ArrayList<Integer>(row));
        row.clear();

        row.add(3);
        row.add(4);
        triangle.add(new ArrayList<Integer>(row));
        row.clear();

        row.add(6);
        row.add(5);
        row.add(7);
        triangle.add(new ArrayList<Integer>(row));
        row.clear();

        row.add(4);
        row.add(1);
        row.add(8);
        row.add(-11);
        triangle.add(new ArrayList<Integer>(row));
        row.clear();

        System.out.println(new Triangle().minimumTotal(triangle));
    }
    public int minimumTotal(List<List<Integer>> triangle) {

        if(triangle == null || triangle.isEmpty())
        {
            return 0;
        }

        int[] mem = new int[triangle.size()];
        for(int row = triangle.size()-1;row>=0;row--)
        {
            for(int col =0;col < triangle.get(row).size();col++)
            {
                int val = triangle.get(row).get(col);
                if(row == triangle.size()-1)
                {
                    mem[col] = val;
                }
                else
                {
                    int min = val+ Math.min(mem[col], mem[col+1]);
                    mem[col] = min;
                }

            }

        }

        return mem[0];
    }


}
