package algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rajin on 11/24/2017.
 */
public class BombEnemy {
    public static void main(String[] args)
    {
        char[][] grid = new char[][]
                {
                        {'0', 'E', '0', '0'},
                        {'E', '0', 'W', 'E'},
                        {'0', 'E', '0', '0'},
                };

        System.out.println(new BombEnemy().maxKilledEnemies(grid));
    }
    class Cell{
        int up;
        int left;
        int bottom;
        int right;
        public Cell()
        {
            up=0;
            left=0;
            bottom=0;
            right=0;
        }
        public int getTotal()
        {
            return up+left+bottom+right;
        }

        @Override
        public String toString() {
            return ""+getTotal();
        }
    }
    public int maxKilledEnemies(char[][] grid) {
        if(grid.length ==0)
        {
            return 0;
        }

        Cell[][] cells = new Cell[grid.length][grid[0].length];
        int count =0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                cells[i][j] = new Cell();
                if(grid[i][j]=='W')
                {
                    continue;
                }
                int up =0;
                if(i>0)
                {
                    up += cells[i-1][j].up;
                }


                int left =0;
                if(j>0)
                {
                    left+=cells[i][j-1].left;
                }
                if(grid[i][j]=='E')
                {
                    up++;
                    left++;
                }
                cells[i][j].up = up;
                cells[i][j].left = left;
            }
        }

        for(int i=grid.length-1;i>=0;i--)
        {
            for(int j=grid[0].length-1;j>=0;j--)
            {
                if(grid[i][j]=='W')
                {
                    continue;
                }
                int bottom =0;
                if(i<grid.length-1)
                {
                    bottom+= cells[i+1][j].bottom;
                }


                int right =0;
                if(j<grid[0].length-1)
                {
                    right+=cells[i][j+1].right;
                }
                if(grid[i][j]=='E')
                {
                    bottom++;
                    right++;
                }
                cells[i][j].bottom = bottom;
                cells[i][j].right = right;

                if(grid[i][j] =='0')
                {
                    count = Math.max(count, cells[i][j].getTotal());
                }

            }
        }



        return count;

    }
}
