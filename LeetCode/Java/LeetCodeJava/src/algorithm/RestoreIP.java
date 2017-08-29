package algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajin on 8/24/2017.
 */
public class RestoreIP {
    public static void main(String[] args)
    {
        String s ="25525511135";
        System.out.println(new RestoreIP().restoreIpAddresses(s));
    }
    public List<String> restoreIpAddresses(String s) {

        List<String> result = new ArrayList<String>();
        getResults(new ArrayList<String>(),s,0,result);
        return result;
    }

    public void getResults(ArrayList<String> tokens, String str, int index, List<String> result )
    {
//        System.out.println(tokens+", i:"+index+","+result);
        if(tokens.size()>4)
        {
            return;
        }
        if(tokens.size()==4 && index ==str.length())
        {
            StringBuilder ip = new StringBuilder();
            ip.append(tokens.get(0));
            for(int i=1;i<tokens.size();i++)
            {
                ip.append('.');
                ip.append(tokens.get(i));
            }
            result.add(ip.toString());
            return;
        }
        if(tokens.size()<4)
        {
            int[] minVals = new int[]{-1,9,99};
            for(int i=index;i<index+3 && i<str.length();i++)
            {
                String tokenString = str.substring(index,i+1);
                int len = i+1-index;
                int ipChunk = Integer.valueOf(tokenString);
                if(ipChunk > minVals[len-1] && ipChunk <256)
                {
                    tokens.add(tokenString);
                    getResults(tokens,str,i+1,result);
                    tokens.remove(tokens.size()-1);
                }
            }
        }

    }


}
