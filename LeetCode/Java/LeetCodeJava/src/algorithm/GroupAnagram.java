package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rajin on 7/12/2017.
 */
public class GroupAnagram {
    public static void main(String[] args){
        String [] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagram obj = new GroupAnagram();
        obj.groupAnagrams(strs);
    }
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, ArrayList<String>> groupMap = new HashMap<String, ArrayList<String>> ();


        for(String str: strs){
            String hashKey = getHashKey(str);
            if(!groupMap.containsKey(hashKey)){
                ArrayList<String> groupList = new ArrayList<String>();
                groupList.add(str);
                groupMap.put(hashKey,groupList);
            }
            else{

                ArrayList<String> group = groupMap.get(hashKey);
                group.add(str);
            }
        }
        ArrayList<List<String>> groupList = new ArrayList<List<String>>();
        for(String key: groupMap.keySet()){
            System.out.println(key+"=>"+groupMap.get(key));
            groupList.add(groupMap.get(key));
        }

        return groupList;

    }
    public String getHashKey(String str){
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);

        return new String(charArray);
    }
    public boolean isPermutation(String str1, String str2){
        if(str1.length() != str2.length()){
            return  false;
        }
        HashMap<Character,Integer> characterMap = new HashMap<Character,Integer>();

        char[] charArray1  = str1.toCharArray();
        for(char ch : charArray1){
            Character character = new Character(ch);
            if(!characterMap.containsKey(character)){
                characterMap.put(character,1);
            }
            else{
                Integer occurrence = characterMap.get(character);
                occurrence++;
                characterMap.put(character, occurrence);
            }
        }

        for(char ch: str2.toCharArray()){
            Character character = new Character(ch);
            if(!characterMap.containsKey(character)){
                return  false;
            }
            else{
                Integer occurrence = characterMap.get(character);
                occurrence--;
                if(occurrence >0){
                    characterMap.put(character, occurrence);
                }
                else{
                    characterMap.remove(character);
                }

            }
        }
        if(characterMap.keySet().isEmpty()){
            return true;
        }
        else{
            return false;
        }


    }
}
