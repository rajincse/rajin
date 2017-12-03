package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rajin on 11/27/2017.
 * 271. Encode and Decode Strings
 */
public class EncodeDecodeString {
    public static void main(String[] args)
    {
        EncodeDecodeString obj = new EncodeDecodeString();
        obj.runTests();
    }
    public void runTests()
    {
        List<String> strs = new ArrayList<String>();
        strs.add("C/C++");
        strs.add("C\\");
        strs.add("");
        Codec codec = new Codec();
        String encoded = codec.encode(strs);
        List<String> decode = codec.decode(encoded);

        System.out.println(strs+"=>'"+encoded+"'=>"+decode+"("+decode.size()+")");

    }
    class Codec {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            if(strs==null || strs.isEmpty())
            {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strs.size(); i++) {
                for (char ch : strs.get(i).toCharArray()) {
                    if (ch == '\\') {
                        sb.append("\\\\");
                    }
                    else if (ch == '+') {
                        sb.append("\\+");
                    }
                    else
                    {
                        sb.append(ch);
                    }
                }
                sb.append('+');

            }

            return sb.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {

            List<String> result = new ArrayList<String>();
            if(s==null || s.length()==0)
            {
                return result;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '+') {
                    result.add(sb.toString());
                    sb.setLength(0);
                } else if (ch == '\\') {
                    i++;
                    sb.append(s.charAt(i));
                } else {
                    sb.append(ch);
                }
            }
            return result;
        }
    }
}
