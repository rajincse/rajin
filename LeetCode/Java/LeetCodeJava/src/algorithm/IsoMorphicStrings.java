package algorithm;

import java.util.*;

/**
 * Created by rajin on 11/6/2017.
 */
public class IsoMorphicStrings {
    public static void main(String[] args)
    {

        String s = "ab";
        String t = "aa";
        System.out.println(new IsoMorphicStrings().isIsomorphic(s,t));
    }
    public boolean isIsomorphic(String s, String t) {

        HashMap<Character, Character> map = new HashMap<Character, Character>();
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        for(int i=0;i<sChars.length;i++){
            if(map.containsKey(sChars[i])){
                if(!map.get(sChars[i]).equals(tChars[i])){
                    return false;
                }
            }
            else{
                if(!map.containsValue(tChars[i]))
                {
                    map.put(sChars[i], tChars[i]);
                }
                else
                {
                    return false;
                }

            }
        }

        return true;
    }
}
