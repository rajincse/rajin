package algorithm;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by rajin on 11/25/2017.
 * 734. Sentence Similarity
 */
public class SentenceSimilarity {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if(words1.length != words2.length)
        {
            return false;
        }
        HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
        for(String[] pair: pairs)
        {
            if(map.containsKey(pair[0]))
            {
                HashSet<String> set = map.get(pair[0]);
                set.add(pair[1]);
                map.put(pair[0], set);
            }
            else
            {
                HashSet<String> set = new HashSet<String>();
                set.add(pair[1]);
                map.put(pair[0], set);
            }

            if(map.containsKey(pair[1]))
            {
                HashSet<String> set = map.get(pair[1]);
                set.add(pair[0]);
                map.put(pair[1], set);
            }
            else
            {
                HashSet<String> set = new HashSet<String>();
                set.add(pair[0]);
                map.put(pair[1], set);
            }
        }

        for(int i=0;i<words1.length;i++)
        {
            String a = words1[i];
            String b = words2[i];
            if(
                    !a.equals(b)
                            && !(matchesMap(map,a,b))
                    )
            {
                return false;
            }
        }

        return true;
    }

    public boolean matchesMap(HashMap<String, HashSet<String>> map, String a, String b)
    {

        return (map.containsKey(a) && map.get(a).contains(b) )
                || (map.containsKey(b) && map.get(b).contains(a));

    }
}
