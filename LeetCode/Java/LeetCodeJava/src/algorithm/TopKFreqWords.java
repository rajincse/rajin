package algorithm;

import java.util.*;

/**
 * Created by rajin on 12/3/2017.
 * 692. Top K Frequent Words
 */
public class TopKFreqWords {
    public static void main(String[] args)
    {
        String[] words = new String[]{"Rajin", "Munia", "Rajin", "Kumkum", "Munia", "Tania"};
        int k =2;
        System.out.println(new TopKFreqWords().topKFrequent(words, k));
    }
    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<String>();

        if(words ==null || words.length==0 || k==0)
        {
            return result;
        }
        HashMap<String,WordWithFrequency> map = new HashMap<String, WordWithFrequency>();
        for(String w: words)
        {
            WordWithFrequency wf = map.getOrDefault(w, new WordWithFrequency(w));
            wf.frequency++;
            map.put(w, wf);
        }
        TreeSet<WordWithFrequency> set = new TreeSet<WordWithFrequency>();
        for(WordWithFrequency wf: map.values())
        {
            if(set.size()<k)
            {
                set.add(wf);
            }
            else
            {
                WordWithFrequency tail = set.last();
                if(wf.compareTo(tail) <0)
                {
                    set.pollLast();
                    set.add(wf);
                }
            }
        }

        for(WordWithFrequency wf: set)
        {
            result.add(wf.word);
        }

        return result;
    }
    class WordWithFrequency implements Comparable<WordWithFrequency>
    {
        String word;
        int frequency;
        public WordWithFrequency(String word)
        {
            this.word = word;
            this.frequency = 0;
        }

        @Override
        public int compareTo(WordWithFrequency o) {
            WordWithFrequency otherObj = o;
            int diff = otherObj.frequency - this.frequency;
            if(diff==0)
            {
                return this.word.compareTo(otherObj.word);
            }
            else
            {
                return diff;
            }
        }

        @Override
        public String toString() {
            return "{" +
                    "word='" + word + '\'' +
                    ", frequency=" + frequency +
                    '}';
        }
    }
}
