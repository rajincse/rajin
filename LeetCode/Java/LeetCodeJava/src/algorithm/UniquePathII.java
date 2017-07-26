package algorithm;

import java.util.Arrays;

/**
 * Created by rajin on 7/26/2017.
 */
public class UniquePathII {
    public  static void main(String[] args){
        int[][] obstacleGrid = new int[][]{
                {1}
        };

        System.out.println("res:"+new UniquePathII().uniquePathsWithObstacles(obstacleGrid));
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if(obstacleGrid.length ==0)
        {
            return 0;
        }

        int[][] a = new int[obstacleGrid.length][obstacleGrid[0].length];
        a[0][0] = 1;
        for(int i=0;i<obstacleGrid.length;i++)
        {
            for(int j=0;j<obstacleGrid[i].length;j++)
            {
                int up = 0;
                int left = 0;
                if (i>0)
                {
                    up = a[i-1][j];
                }
                if(j>0)
                {
                    left = a[i][j-1];
                }
                if(i==0 && j==0)
                {
                    a[i][j] = obstacleGrid[i][j]==1? 0:1;
                }
                else
                {
                    a[i][j] = obstacleGrid[i][j]==1? 0:(up+left);
                }

            }
        }

//        for(int i=0;i<a.length;i++)
//        {
//            for(int j=0;j<a[i].length;j++)
//            {
//                System.out.print(a[i][j]+", ");
//            }
//            System.out.println();
//        }
        return a[a.length-1][a[0].length-1];
    }
}
