package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by rajin on 11/6/2017.
 */
class WordDistance {

    private HashMap<String, Integer> wordToIndex = new HashMap<String, Integer>();
    private HashMap<String, List<Integer>> indexMap;
    public WordDistance(String[] words) {

        calculateAll(words);
    }

    public void calculateAll(String[] words){
        this.indexMap = new HashMap<String, List<Integer>>();

        int index=0;
        for(int i=0;i<words.length;i++){
            if(indexMap.containsKey(words[i])){
                indexMap.get(words[i]).add(i);
            }
            else
            {
                List<Integer> indices = new ArrayList<Integer>();
                indices.add(i);
                indexMap.put(words[i], indices);
                wordToIndex.put(words[i], index);
                index++;
            }
        }


    }
    private int getMinDistance(HashMap<String, List<Integer>> indexMap, String word1, String word2){
        int minDistance = Integer.MAX_VALUE;
        List<Integer> indices1 = indexMap.get(word1);
        List<Integer> indices2 = indexMap.get(word2);
        int i=0;
        int j=0;
        while (i<indices1.size() && j< indices2.size())
        {
            int index1 = indices1.get(i);
            int index2 = indices2.get(j);
            if(index1 < index2){
                minDistance = Math.min(minDistance, index2-index1);
                i++;
            }
            else
            {
                minDistance = Math.min(minDistance, index1-index2);
                j++;
            }
        }
        return minDistance;
    }

    public int shortest(String word1, String word2) {

        return getMinDistance(this.indexMap, word1,word2);
    }
}
public class ShortestWordDistance {
    public static void main(String[] args){
        String[] words= new String[]{"practice", "makes", "perfect", "coding", "makes"};
        WordDistance wd = new WordDistance(words);

        System.out.println(wd.shortest("coding","practice"));
        System.out.println(wd.shortest("makes","coding"));
    }

    public int shortestDistance(String[] words, String word1, String word2) {
        HashMap<String, List<Integer>> indexMap = new HashMap<String, List<Integer>>();

        for(int i=0;i<words.length;i++){
            if(indexMap.containsKey(words[i])){
                indexMap.get(words[i]).add(i);
            }
            else
            {
                List<Integer> indices = new ArrayList<Integer>();
                indices.add(i);
                indexMap.put(words[i], indices);
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for(Integer pos1: indexMap.get(word1)){
            for(Integer pos2: indexMap.get(word2)){
                minDistance = Math.min(minDistance, Math.abs(pos1-pos2));
            }
        }

        return minDistance;
    }
}
