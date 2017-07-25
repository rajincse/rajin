package algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rajin on 7/22/2017.
 */
public class ReplaceWords {
    public static void main(String[] args) {
        String[] dict = new String[]{"cat", "bat", "rat"};
        String sentence = "the cattle was rattled by the battery";
        System.out.println(new ReplaceWords().replaceWords(Arrays.asList(dict),sentence ));
    }
    public String replaceWords(List<String> dict, String sentence) {

        String[] split = sentence.split(" ");
        String [] words = new String[split.length];
        int index =0;
        for(String word : split)
        {
            String minRoot ="";
            int minRootSize = Integer.MAX_VALUE;
            for(String root: dict)
            {
                if(word.startsWith(root) && root.length() < minRootSize)
                {
                    minRoot = root;
                    minRootSize = root.length();
                }
            }
            if(!minRoot.isEmpty())
            {
                words[index] = minRoot;
            }
            else
            {
                words[index] = word;
            }
            index++;
        }
        return  String.join(" ", words);
    }
}
