package algorithm;

/**
 * Created by rajin on 7/20/2017.
 */
public class PermutationSequence {
    public static void main(String[] args){
        int n =4;
        int k = 13;
        String result = new PermutationSequence().getPermutation(n,k);
        System.out.println("Result:"+result);
    }
    public String getPermutation(int n, int k) {
        StringBuilder initial = new StringBuilder();

        for(int i=0;i<n;i++){
            initial.append(""+(i+1));
        }
        StringBuilder result = getPermutationSequence(new StringBuilder(), initial, k-1);
        return  result.toString();
    }

    public StringBuilder getPermutationSequence(StringBuilder prefix, StringBuilder rest, int m){
        System.out.println(prefix+", "+rest+", "+m);
        int length = rest.length();
        if(length==1){
            return  prefix.append(rest.charAt(0));
        }
        else{
            int factorialLength =getFactorial(length-1);
            int prefixIndex = m/ factorialLength;
            prefix.append(rest.charAt(prefixIndex));
            StringBuilder restOfTheChar = new StringBuilder();
            restOfTheChar.append(rest.substring(0,prefixIndex));
            restOfTheChar.append(rest.substring(prefixIndex+1));

            return getPermutationSequence(prefix, restOfTheChar, m % getFactorial(length-1));
        }
    }

    public int getFactorial(int n){
        if(n==0){
            return  1;
        }
        else
        {
            return n * getFactorial(n-1);
        }
    }
}
