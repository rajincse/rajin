package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 8/12/2017.
 */
public class FindKClosestElements {
    public  static  void main(String [] args){
        List<Integer> arr = new ArrayList<Integer>();
        arr.add(0);
        arr.add(1);
        arr.add(1);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(6);
        arr.add(7);
        arr.add(8);
        arr.add(9);

        int k = 9;
        int x = 4;
        System.out.println(new FindKClosestElements().findClosestElements(arr, k,x));
    }

    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        if(k>= arr.size()) return arr;
        int searchIndex = getSearchIndex(arr,0, arr.size()-1,x);
        System.out.println("Search:"+searchIndex+"("+arr.get(searchIndex)+")");
        List<Integer> result = new ArrayList<Integer>();

        //adjustment
        if(searchIndex+1 < arr.size() && Math.abs(arr.get(searchIndex+1).intValue()-x) < Math.abs(arr.get(searchIndex).intValue()-x))
        {
            searchIndex = searchIndex+1;
        }

        System.out.println("new Search:"+searchIndex+"("+arr.get(searchIndex)+")");
        if(k>0)
        {
            result.add(arr.get(searchIndex));
            int backIndex =searchIndex-1;
            int forwardIndex =searchIndex+1;
            int backDistance =Integer.MAX_VALUE;
            int forwardDistance =Integer.MAX_VALUE;
            int currentDistance = getDistance(arr,searchIndex,x);
            while(result.size()<k)
            {
                backDistance = backIndex>=0? getDistance(arr,backIndex,x):Integer.MAX_VALUE;
                forwardDistance = forwardIndex<arr.size()? getDistance(arr,forwardIndex,x):Integer.MAX_VALUE;

                if(backDistance<=forwardDistance)
                {
                    result.add(0,arr.get(backIndex));
                    backIndex--;
                }
                else
                {
                    result.add(arr.get(forwardIndex));
                    forwardIndex++;
                }
            }


        }

        return result;
    }

    public int getDistance(List<Integer> arr, int index, int x)
    {
        return Math.abs(arr.get(index).intValue()-x);
    }


    public  int getSearchIndex(List<Integer> arr, int start, int end, int val)
    {
        System.out.println("binarySearch:("+start+", "+end+")");
        if(start > end)
        {
            return -1;
        }
        int mid = (start+end)/2;
        int diff =val -arr.get(mid) ;

        if(diff == 0)
        {
            return mid;
        }
        else if(diff > 0 )
        {
            if(mid == start )
            {
                return mid;
            }
            else
            {
                return getSearchIndex(arr,mid,end,val);
            }

        }
        else{
            if(mid == end)
            {
                return mid;
            }
            else
            {
                return getSearchIndex(arr,start, mid, val);
            }

        }

    }

}
