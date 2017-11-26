package algorithm;

/**
 * Created by rajin on 11/24/2017.
 * 200. Number of Islands
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int count =0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]=='1'){
                    count++;
                    markIsland(grid, i, j);
                }
            }
        }

        return count;
    }
    public void markIsland(char[][] grid, int row, int col)
    {
        if(grid.length ==0)
        {
            return;
        }
        if(row>=0 && row< grid.length && col >= 0 && col < grid[0].length && grid[row][col]=='1')
        {
            grid[row][col] = 'X';
            markIsland(grid, row-1, col);
            markIsland(grid, row+1, col);
            markIsland(grid, row, col-1);
            markIsland(grid, row, col+1);
        }
    }
}
