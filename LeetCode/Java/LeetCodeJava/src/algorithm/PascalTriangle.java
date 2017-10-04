package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 10/2/2017.
 * 118. Pascal's Triangle
 * 119. Pascal's Triangle II
 */
public class PascalTriangle {
    public static void main(String[] args)
    {
        int numRows = 5;
        List<List<Integer>> res = new PascalTriangle().generate(numRows);

        for(List<Integer> item: res)
        {
            System.out.println(item);
        }

        int k =2;
        System.out.println(k+"=>"+new PascalTriangle().getRow(k));

    }
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(numRows ==0)
        {
            return result;
        }
        ArrayList<Integer> item = new ArrayList<Integer>();
        item.add(1);
        result.add( new ArrayList<Integer>(item));
        if(numRows ==1)
        {
            return result;
        }

        item.add(1);
        result.add( new ArrayList<Integer>(item));
        if(numRows ==2)
        {
            return result;
        }

        for(int i=2;i<numRows;i++)
        {
            ArrayList<Integer> newItem = new ArrayList<Integer>();
            newItem.add(1);
            for(int j=0;j<item.size()-1;j++)
            {
                newItem.add(item.get(j)+item.get(j+1));
            }
            newItem.add(1);
            result.add(newItem);
            item = newItem;
        }



        return result;
    }
    public List<Integer> getRow(int rowIndex) {

        ArrayList<Integer>  result = new ArrayList<Integer>();
        if(rowIndex <0)
        {
            return result;
        }
        if(rowIndex ==0)
        {

            result.add(1);
            return result;
        }

        ArrayList<Integer> lastHalfRow = new ArrayList<Integer>();
        lastHalfRow.add(1);
        for(int k=1;k<rowIndex;k++)
        {
            int halfLength = (k+1)/2;
            ArrayList<Integer> halfRow = new ArrayList<Integer>(halfLength);
            halfRow.add(1);
            for(int i=0;i<lastHalfRow.size()-1;i++)
            {
                halfRow.add(lastHalfRow.get(i)+lastHalfRow.get(i+1));
            }
            if(k%2 ==0)
            {
                halfRow.add(lastHalfRow.get(lastHalfRow.size()-1)*2);
            }

            lastHalfRow = halfRow ;
        }
        result = new ArrayList<Integer>();
        result.add(1);
        for(int i=0;i<lastHalfRow.size()-1;i++)
        {
            result.add(lastHalfRow.get(i)+lastHalfRow.get(i+1));
        }
        if(rowIndex%2 ==0)
        {
            result.add(lastHalfRow.get(lastHalfRow.size()-1)*2);
        }
        for(int i=lastHalfRow.size()-1;i>0;i--)
        {
            result.add(lastHalfRow.get(i)+lastHalfRow.get(i-1));
        }
        result.add(1);


        return result;
    }
}
