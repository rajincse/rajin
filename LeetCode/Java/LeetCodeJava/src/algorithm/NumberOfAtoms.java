package algorithm;

import java.util.*;

/**
 * Created by rajin on 11/11/2017.
 */
public class NumberOfAtoms {
    public static  void main(String[] args){
        String formula ="(O)2(N)2";
        System.out.println(new NumberOfAtoms().countOfAtoms(formula));
    }
    public String countOfAtoms(String formula) {
        HashMap<String, Integer> result = getCount(formula);
        List keySet = new ArrayList(result.keySet());
        Collections.sort(keySet);
        StringBuilder output = new StringBuilder();
        for(int i=0;i<keySet.size();i++)
        {
            String key = (String) keySet.get(i);
            output.append(key);
            int count = result.get(key);
            if(count > 1)
            {
                output.append(count);
            }
        }

        return output.toString();

    }

    int i=0;


    public HashMap<String, Integer> getCount(String formula){

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        while(i < formula.length() && formula.charAt(i) !=')')
        {
            if(formula.charAt(i) =='(')
            {
                i++;
                HashMap<String, Integer> nestedMap = getCount(formula);
                mergeMaps(nestedMap, map);
            }
            else
            {
                int start = i;
                i++;
                while(i < formula.length() && Character.isLowerCase(formula.charAt(i))) i++;

                String element = formula.substring(start, i);
                start = i;
                while(i < formula.length() && Character.isDigit(formula.charAt(i))) i++;
                int count = start < i? Integer.parseInt(formula.substring(start, i)):1;
                map.put(element, map.getOrDefault(element, 0)+count);
            }
        }
        i++;
        int start = i;
        while(i< formula.length() && Character.isDigit(formula.charAt(i))) i++;
        int multiple = start < i? Integer.parseInt(formula.substring(start, i)):1;
        if(multiple > 1)
        {
            for(String key: map.keySet())
            {
                map.put(key, map.get(key) * multiple);
            }
        }

        return map;

    }

    public void mergeMaps(HashMap<String, Integer> source, HashMap<String, Integer> destination){
        for(String key: source.keySet())
        {
            if(destination.containsKey(key))
            {
                destination.put(key, destination.get(key)+source.get(key));
            }
            else
            {
                destination.put(key, source.get(key));
            }
        }
    }
}
