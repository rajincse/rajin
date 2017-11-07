package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rajin on 11/6/2017.
 */
public class RepeatedDNA {
    public static void main(String[] args){
        String s ="AAAAAAAAAAA";
        System.out.println(new RepeatedDNA().findRepeatedDnaSequences(s));
    }
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<String, Integer> occurrenceMap = new HashMap<String, Integer>();
        List<String> result = new ArrayList<String>();
        if(s.length()<10){
            return result;
        }

        for(int i=0;i<=s.length()-10;i++){
            String sequence = s.substring(i,i+10);
            if(!occurrenceMap.containsKey(sequence)){
                occurrenceMap.put(sequence, 1);
            }
            else
            {
                int occurrence = occurrenceMap.get(sequence);
                occurrence++;
                occurrenceMap.put(sequence, occurrence);
            }
        }
        for(String seq: occurrenceMap.keySet()){
            if(occurrenceMap.get(seq) > 1){
                result.add(seq);
            }
        }

        return result;
    }
}
