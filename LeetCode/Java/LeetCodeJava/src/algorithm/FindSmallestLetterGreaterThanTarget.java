package algorithm;

/**
 * Created by rajin on 12/9/2017.
 */
public class FindSmallestLetterGreaterThanTarget {
    public static void main(String[] args)
    {
        char[] letters = new char[]{'c', 'f', 'j'};
        char target ='c';
        System.out.println(new FindSmallestLetterGreaterThanTarget().nextGreatestLetter(letters, target));
    }
    public char nextGreatestLetter(char[] letters, char target) {
        char smallest = letters[0];
        int smallestDistance = 27;
        for(char ch: letters)
        {
            int distance = getDistance(ch, target);
            if(distance> 0 && distance < smallestDistance)
            {
                smallestDistance = distance;
                smallest = ch;
            }
        }
        return smallest;
    }

    public int getDistance(char ch, char target)
    {
        int distance = ch - target;
        if(distance > 0)
        {

            return distance ;
        }
        else
        {
            return ch+26- target;
        }

    }
}
