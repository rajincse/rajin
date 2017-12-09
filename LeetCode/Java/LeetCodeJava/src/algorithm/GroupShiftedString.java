package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rajin on 12/4/2017.
 * 249. Group Shifted Strings
 */
public class GroupShiftedString {
    public static  void main(String[] args)
    {
        String[] strings = new String[]{"eqdf", "qcpr"};
        List<List<String>> result = new GroupShiftedString().groupStrings(strings);
        for(List<String> list: result)
        {
            System.out.println(list);
        }
    }
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        if(strings==null || strings.length==0)
        {
            return result;
        }
        HashMap<String,List<String>> map = new HashMap<String, List<String>>();
        for(String str: strings)
        {
            String matchingKey ="";
            for(String key: map.keySet())
            {
                if(isSameSequence(key, str))
                {
                    matchingKey = key;
                    break;
                }
            }
            if(matchingKey.isEmpty())
            {
                List<String> list = new ArrayList<String>();
                list.add(str);
                map.put(str, list);
            }
            else
            {
                List<String> list = map.get(matchingKey);
                list.add(str);
                map.put(matchingKey, list);
            }
        }
        for(List<String> list: map.values())
        {
            result.add(list);
        }

        return result;

    }
    public int getDiff(char ch1, char ch2)
    {
       if(Math.abs(ch1 - ch2) == 13)
       {
           return 13;
       }
       else if(Math.abs(ch1 - ch2) > 13)
       {
           if(ch1 > ch2)
           {
               return ch1-ch2-26;
           }
           else
           {
               return ch1+26 - ch2;
           }

       }
       else
       {
           return ch1- ch2;
       }
    }
    public boolean isSameSequence(String s1, String s2)
    {
        if(s1 ==null && s2 ==null)
        {
            return true;
        }
        else if(s1==null || s2==null )
        {
            return false;
        }
        else
        {

            if(s1.length() == s2.length())
            {
                if(s1.length()<2)
                {
                    return true;
                }

                for(int i=0;i<s1.length()-1;i++)
                {
                    int diff1 = getDiff(s1.charAt(i),s1.charAt(i+1));
                    int diff2 = getDiff(s2.charAt(i),s2.charAt(i+1));
                    if(diff1 != diff2)
                    {
                        return false;
                    }
                }

                return true;

            }
            else
            {
                return false;
            }
        }
    }
}
