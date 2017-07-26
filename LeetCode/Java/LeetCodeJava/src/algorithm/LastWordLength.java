package algorithm;

/**
 * Created by rajin on 7/26/2017.
 */
public class LastWordLength {
    public static void main(String[] args)
    {
        System.out.println(new LastWordLength().lengthOfLastWord(""));
    }

    public int lengthOfLastWord(String s) {

        String[] split = s.trim().split(" ");

        return split[split.length-1].length();
    }
}
