package algorithm;

import java.util.Arrays;

/**
 * Created by rajin on 11/3/2017.
 */
public class SurroundedRegions {
    public static  void main(String[] args)
    {
        char[][] board = new char[][]{
                {'O', 'O', },
                {'O', 'O', },
        };

        new SurroundedRegions().solve(board);
        for(char[] arr: board)
        {
            System.out.println(Arrays.toString(arr));
        }
    }
    public void solve(char[][] board) {
        if(board ==null || board.length ==0){
            return;
        }

        boolean[][] keepO = new boolean[board.length][board[0].length];
        //search edges
        //top row =0,
        for(int c=0;c<board[0].length;c++){
            if(!keepO[0][c] && board[0][c]=='O')
            {
                keepO = set(0,c, keepO, board);
            }
        }

        //bottom row = board.length-1
        for(int c=0;c<board[0].length;c++){
            if(!keepO[board.length-1][c] && board[board.length-1][c]=='O')
            {
                keepO =set(board.length-1,c, keepO, board);
            }
        }

        //left edge row = 1 to board.length, col =0
        for(int r = 1; r<board.length-1;r++)
        {
            if(!keepO[r][0] && board[r][0]=='O')
            {
                keepO =set(r,0, keepO, board);
            }
        }

        //right edge col = board[0].length-1
        for(int r = 1; r<board.length-1;r++)
        {
            if(!keepO[r][board[0].length-1] && board[r][board[0].length-1]=='O')
            {
                keepO =set(r,board[0].length-1, keepO, board);
            }
        }

        for(int i=0;i<keepO.length;i++)
        {
            for(int j=0;j<keepO[i].length;j++)
            {
                if(keepO[i][j]){
                    board[i][j] ='O';
                }
                else
                {
                    board[i][j] ='X';
                }
            }
        }

    }

    public boolean[][] set(int row, int col, boolean[][] keepO, char[][] board){
        keepO[row][col] = true;
        //up
        if(row-1>=0 && !keepO[row-1][col] && board[row-1][col]=='O')
        {
            keepO=set(row-1, col, keepO, board);
        }

        //down
        if(row+1<keepO.length && !keepO[row+1][col] && board[row+1][col]=='O')
        {
            keepO=set(row+1, col, keepO, board);
        }

        //left
        if(col-1>=0 && !keepO[row][col-1] && board[row][col-1]=='O')
        {
            keepO=set(row, col-1, keepO, board);
        }

        //right
        if(col+1<keepO[0].length && !keepO[row][col+1] && board[row][col+1]=='O')
        {
            keepO=set(row, col+1, keepO, board);
        }

        return keepO;
    }
}
