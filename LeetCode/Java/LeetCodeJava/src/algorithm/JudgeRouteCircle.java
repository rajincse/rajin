package algorithm;

/**
 * Created by rajin on 8/12/2017.
 */
public class JudgeRouteCircle {
    public static void main(String[] args)
    {
        String moves ="UDLR";
        System.out.println(new JudgeRouteCircle().judgeCircle(moves));
    }
    public boolean judgeCircle(String moves) {
        char[] movesArray = moves.toUpperCase().toCharArray();
        int initialX =0;
        int initialY =0;

        int x = initialX;
        int y =initialY;

        for(char move: movesArray)
        {
            if(move=='U')
            {
                y--;
            }
            else if(move =='D')
            {
                y++;
            }
            else if(move =='L')
            {
                x--;
            }
            else if(move =='R')
            {
                x++;
            }
        }
        if(x == initialX && y ==initialY)
        {
            return true;
        }
        else
        {
            return false;
        }


    }
}
