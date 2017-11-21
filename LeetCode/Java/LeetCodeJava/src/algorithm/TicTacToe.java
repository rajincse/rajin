package algorithm;

/**
 * Created by rajin on 11/20/2017.
 * 348. Design Tic-Tac-Toe
 */
class TicTacToe {

    /** Initialize your data structure here. */
    private int[][] matrix;

    public TicTacToe(int n) {
        this.matrix = new int[n][n];
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        this.matrix[row][col] = player;

        //check row win
        boolean win = true;
        for(int c=0;c<matrix.length;c++)
        {
            win = win && matrix[row][c] ==player;
        }

        if(win)
        {
            return player;
        }

        //check col win
        win = true;
        for(int r = 0;r < matrix.length;r++)
        {
            win = win && matrix[r][col]==player;
        }
        if(win)
        {
            return player;
        }


        //check diag win
        if(row == col)
        {
            win = true;
            for(int rc =0;rc < matrix.length;rc++)
            {
                win = win && matrix[rc][rc] ==player;
            }
            if(win)
            {
                return player;
            }
        }

        if(row+col == matrix.length-1)
        {
            win = true;
            for(int i=0;i< matrix.length;i++)
            {
                win = win && matrix[i][matrix.length-i-1] ==player;
            }
            if(win)
            {
                return player;
            }
        }

        return 0;
    }
    public static  void main(String[] args){
        TicTacToe t =new TicTacToe(2);
        t.move(0,0,2);
        t.move(1,1,1);
        t.move(0,1,2);
    }
}
