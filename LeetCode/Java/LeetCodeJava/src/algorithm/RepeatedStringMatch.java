package algorithm;

/**
 * Created by rajin on 11/26/2017.
 * 686. Repeated String Match
 */
public class RepeatedStringMatch {
    public  static void main(String[] args)
    {
        String a ="abcd";
        String b ="cdabcdab";
        System.out.println(new RepeatedStringMatch().repeatedStringMatch(a,b));
    }
    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder();

        int count=0;
        do {
            if(sb.toString().contains(B))
            {
                return count;
            }
            else
            {
                sb.append(A);
                count++;
            }
        }
        while(sb.length()<B.length());

        if(sb.toString().contains(B))
        {
            return count;
        }
        else
        {
            sb.append(A);
            if(sb.toString().contains(B))
            {
                return count+1;
            }
        }


        return -1;
    }
}
