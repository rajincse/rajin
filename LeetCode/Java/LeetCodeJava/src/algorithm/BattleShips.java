package algorithm;

/**
 * Created by rajin on 11/21/2017.
 * 419. Battleships in a Board
 */
public class BattleShips {
    public static void main(String[] args){
        char[][] board = new char[][]
                {
                        {'X', '.', '.', 'X'},
                        {'.', 'X', 'X', '.'},
                        {'X', '.', '.', '.'},

                };
        System.out.println(new BattleShips().countBattleships(board));

    }
    public int countBattleships(char[][] board) {
        int count =0;

        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                if(board[i][j] =='X')
                {
                    if((i>=1  && board[i-1][j] =='X') || (j >=1 && board[i][j-1] == 'X'))
                    {
                        continue;
                    }
                    else
                    {
                        count++;
                    }
                }
            }
        }

        return count;

    }
}
