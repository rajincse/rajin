package algorithm;

import com.sun.org.apache.regexp.internal.CharacterArrayCharacterIterator;

import java.util.ArrayList;

/**
 * Created by rajin on 6/10/2017.
 */
class CharNode{
    char c;
    int occurrence;
    public CharNode(char c, int occurrence){
        this.c = c;
        this.occurrence = occurrence;
    }

}
public class StringIterator {

    private ArrayList<CharNode> characterList = null;
    private int currentIndex =0;
    private  int subIndex =0;
    public StringIterator(String compressedString) {
        characterList = new ArrayList<CharNode>();
        int i=0;
        while(i<compressedString.length()){
            char c = compressedString.charAt(i);
            i++;
            String occurrenceString ="";
            char occurrenceChar = compressedString.charAt(i);
            while (Character.isDigit(occurrenceChar)){
                occurrenceString+=occurrenceChar;
                i++;
                if(i<compressedString.length())
                {
                    occurrenceChar = compressedString.charAt(i);
                }
                else
                {
                    break;
                }

            }
            int occurrence = Integer.parseInt(occurrenceString);
            CharNode node = new CharNode(c, occurrence);
            characterList.add(node);
        }
    }

    public char next() {
        if(hasNext())
        {
            char nextCharacter =characterList.get(currentIndex).c;
            subIndex++;
            if(subIndex == characterList.get(currentIndex).occurrence){
                currentIndex++;
                subIndex =0;
            }
            return nextCharacter;
        }
        else{
            return ' ';
        }


    }

    public boolean hasNext() {

        return currentIndex < characterList.size() && subIndex <characterList.get(currentIndex).occurrence;
    }
    public  static  void main(String[] args){
        String compressed = "a1234567890b1234567890";
        StringIterator si = new StringIterator(compressed);
        while(si.hasNext())
        {
            char c = si.next();
            System.out.print(c);
        }
        System.out.println();
    }
}
