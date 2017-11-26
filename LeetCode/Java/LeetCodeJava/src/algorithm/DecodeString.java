package algorithm;

/**
 * Created by rajin on 11/24/2017.
 */
public class DecodeString {
    public static void main(String [] args){
        String s ="3[a]2[b]";
        System.out.println(new DecodeString().decodeString(s));
    }
    int index=0;
    public String decodeString(String s) {
        return getString(s, 1);
    }
    public String getString(String s, int multiplier)
    {
        int mul=0;
        StringBuilder sb = new StringBuilder();
        while(index < s.length() )
        {
            char ch = s.charAt(index);
            if(Character.isLetter(ch))
            {
                sb.append(ch);
                index++;
            }
            else if(Character.isDigit(ch))
            {
                mul*=10;
                mul+= ch-'0';
                index++;
            }
            else if(ch=='[')
            {
                index++;
                String str = getString(s, mul ==0? 1: mul);
                mul =0;
                sb.append(str);
            }
            else if(ch==']')
            {
                index++;
                break;
            }
        }


        String str = sb.toString();
        for(int i=0;i<multiplier-1;i++)
        {
            sb.append(str);
        }

        return sb.toString();
    }
}
