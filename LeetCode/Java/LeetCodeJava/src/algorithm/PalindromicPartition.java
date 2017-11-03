package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rajin on 11/3/2017.
 */
public class PalindromicPartition {
    public static void main(String[] args)
    {
        String s ="aab";
        List<List<String>> res = new PalindromicPartition().partition(s);
        for(List<String> a: res)
        {
            System.out.println(a);
        }
    }
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        if(s==null || s.isEmpty()) {
            return result;
        }

        dfs(s,result, new ArrayList<String>());


        return result;

    }
    public void dfs(String s, List<List<String>> result, List<String> current){
        if(s.isEmpty()){
            result.add(new ArrayList<String>(current));
        }

        for(int i =0;i<s.length();i++){
            String prefix = s.substring(0, i+1);
            if(isPalindrom(prefix)){
                current.add(prefix);
                String suffix = s.substring(i+1);
                dfs(suffix,result,current);
                current.remove(current.size()-1);
            }
        }
    }
    public boolean isPalindrom(String s){
        if(s == null || s.length() ==0)
        {
            return false;
        }
        char[] charArr = s.toCharArray();
        int left = 0;
        int right = s.length()-1;
        while(left < right)
        {
            if(charArr[left] != charArr[right]){
                return false;
            }
            left++;
            right --;
        }

        return true;
    }
}
