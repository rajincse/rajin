package algorithm;

/**
 * Created by rajin on 12/3/2017.
 * 387. First Unique Character in a String
 */
public class FirstUniqueChar {
    public int firstUniqChar(String s) {
        if(s ==null || s.isEmpty() )
        {
            return -1;
        }
        int[] occurrenceMap = new int[26];
        for(char ch: s.toCharArray())
        {
            int index = ch-'a';
            occurrenceMap[index]++;
        }


        for(int i=0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            int mapIndex = ch-'a';
            if(occurrenceMap[mapIndex] ==1)
            {
                return i;
            }
        }

        return -1;
    }
}
