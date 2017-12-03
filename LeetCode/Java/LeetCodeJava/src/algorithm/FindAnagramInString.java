package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rajin on 11/29/2017.
 * 438. Find All Anagrams in a String
 */
public class FindAnagramInString {
    public static void main(String[] args)
    {
        String s ="cbaebabacd";
        String p="abc";
        System.out.println(new FindAnagramInString().findAnagrams(s, p));
    }
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();

        if (s == null || p == null || s.length() < p.length())
            return result;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (char c : p.toCharArray())
        {
            map.put(c, map.getOrDefault(c, 0)+1);
        }


        int left = 0;
        int right = 0;
        int matchCount = 0;

        while (right < s.length()) {

            if (right - left == p.length())
            {
                char lc = s.charAt(left);
                if(map.containsKey(lc))
                {
                    if(map.get(lc) >=0)
                    {
                        matchCount--;
                    }
                    map.put(lc, map.get(lc)+1);

                }
                left++;
            }

            char rc = s.charAt(right);
            if(map.containsKey(rc))
            {
                if(map.get(rc)>0)
                {
                    matchCount++;
                }
                map.put(rc, map.get(rc)-1);

            }
            right++;

            if (matchCount == p.length())
                result.add(left);
        }

        return result;
    }
}
