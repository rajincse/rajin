package algorithm;

import java.util.Arrays;

/**
 * Created by rajin on 11/27/2017.
 */
public class GameOfLife {
    public static void main(String[] args)
    {
        int[][] board = new int[][]
                {
                        {1,1},
                        {1,0}
                };

        new GameOfLife().gameOfLife(board);
        for(int[] row: board)
        {
            System.out.println(Arrays.toString(row));
        }
    }
    public void gameOfLife(int[][] board) {
        if(board==null | board.length ==0)
        {
            return;
        }
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                int alive =0;
                for(int dr=-1;dr<=1;dr++)
                {
                    for(int dc=-1;dc<=1;dc++)
                    {
                        if(
                                (dr!=0 || dc!= 0)  && dr+i>=0 && dr+i < board.length
                                         && dc+j>=0 && dc+j < board[i].length )
                        {
                            int neighbor = board[i+dr][j+dc];
                            if(neighbor ==1 || neighbor ==2)
                            {
                                alive++;
                            }
                        }

                    }
                }
                if(board[i][j]==1 && (alive < 2 || alive >3))
                {
                    board[i][j] =2;
                }
                else if(board[i][j]==0 &&  alive ==3)
                {
                    board[i][j] =3;
                }
            }
        }

        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                board[i][j] = board[i][j] %2;
            }
        }

    }
}
