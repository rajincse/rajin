package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by rajin on 7/26/2017.
 */
abstract class GridPosition implements Comparable<GridPosition>{
    int row;
    int col;

    public GridPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GridPosition that = (GridPosition) o;

        if (row != that.row) return false;
        return col == that.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}
public class MinPathSum {
    public  static void main(String[] args){
        int[][] grid = new int[][]{
//                {1,2,3},
//                {1,2,3},
//                {1,2,3},
                {7,1,3,5,8,9,9,2,1,9,0,8,3,1,6,6,9,5},
                {9,5,9,4,0,4,8,8,9,5,7,3,6,6,6,9,1,6},
                {8,2,9,1,3,1,9,7,2,5,3,1,2,4,8,2,8,8},
                {6,7,9,8,4,8,3,0,4,0,9,6,6,0,0,5,1,4},
                {7,1,3,1,8,8,3,1,2,1,5,0,2,1,9,1,1,4},
                {9,5,4,3,5,6,1,3,6,4,9,7,0,8,0,3,9,9},
                {1,4,2,5,8,7,7,0,0,7,1,2,1,2,7,7,7,4},
                {3,9,7,9,5,8,9,5,6,9,8,8,0,1,4,2,8,2},
                {1,5,2,2,2,5,6,3,9,3,1,7,9,6,8,6,8,3},
                {5,7,8,3,8,8,3,9,9,8,1,9,2,5,4,7,7,7},
                {2,3,2,4,8,5,1,7,2,9,5,2,4,2,9,2,8,7},
                {0,1,6,1,1,0,0,6,5,4,3,4,3,7,9,6,1,9}
        };

        System.out.println(new MinPathSum().minPathSum(grid));
    }

    public int minPathSum(int [][] grid)
    {
        if(grid.length ==0)
        {
            return 0;
        }
        int[][] a = new int[grid.length][grid[0].length];
        a[0][0] = grid[0][0];
        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j<a[i].length;j++)
            {
                if(i==0 && j==0)
                {
                    a[i][j] = grid[i][j];
                }
                else if(i==0)
                {
                    a[i][j] = a[i][j-1]+grid[i][j];
                }
                else if(j==0)
                {
                    a[i][j] = a[i-1][j]+grid[i][j];
                }
                else
                {
                    a[i][j] = Math.min(a[i][j-1],a[i-1][j])+grid[i][j];
                }
            }
        }

        return a[a.length-1][a[0].length-1];
    }

    public int minPathSumDijkstra(int[][] grid) {
        if(grid.length ==0)
        {
            return 0;
        }
        int currentRow =0;
        int currentCol = 0;

        int targetRow = grid.length-1;
        int targetCol = grid[0].length-1;
        int [][]a = new int[targetRow+1][targetCol+1];

        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j<a[i].length;j++)
            {
                a[i][j] = Integer.MAX_VALUE;
            }
        }
        a[0][0] = grid[0][0];
        PriorityQueue<GridPosition> priorityQueue = new PriorityQueue<GridPosition>();

        GridPosition source = new GridPosition(currentRow, currentCol){

            @Override
            public int compareTo(GridPosition o) {
                return a[this.row][this.col] - a[o.row][o.col];
            }
            @Override
            public String toString() {
                return "(" +
                        "" + row +
                        "," + col +")="+a[this.row][this.col];
            }
        };
        priorityQueue.add(source);

        HashSet<GridPosition> visited = new HashSet<GridPosition>();
        while(!priorityQueue.isEmpty())
        {
//            for(int i=0;i<a.length;i++)
//            {
//                for(int j=0;j<a[i].length;j++)
//                {
//                    System.out.print((a[i][j]==Integer.MAX_VALUE? "INF":a[i][j]+"("+grid[i][j]+")")+", ");
//                }
//                System.out.println();
//            }
            GridPosition node = priorityQueue.poll();
//            System.out.println(node);
            if(node.row == targetRow && node.col== targetCol)
            {
                break;
            }
            else
            {
                if(node.col< targetCol)
                {
                    GridPosition right = new GridPosition(node.row, node.col+1){

                        @Override
                        public int compareTo(GridPosition o) {
                            return a[this.row][this.col] - a[o.row][o.col];
                        }
                        @Override
                        public String toString() {
                            return "(" +
                                    "" + row +
                                    "," + col +")="+a[this.row][this.col];
                        }
                    };
                    if(!visited.contains(right))
                    {
                        int rightScore = a[right.row][right.col];

                        if(grid[right.row][right.col]+a[node.row][node.col] < rightScore)
                        {
                            a[right.row][right.col] =grid[right.row][right.col]+a[node.row][node.col] ;
                        }

                        priorityQueue.add(right);
                    }

                }

                if(node.row< targetRow)
                {
                    GridPosition down = new GridPosition(node.row+1, node.col){

                        @Override
                        public int compareTo(GridPosition o) {
                            return a[this.row][this.col] - a[o.row][o.col];
                        }
                        @Override
                        public String toString() {
                            return "(" +
                                    "" + row +
                                    "," + col +")="+a[this.row][this.col];
                        }
                    };
                    if(!visited.contains(down))
                    {
                        int downScore = a[down.row][down.col];
                        if(grid[down.row][down.col]+a[node.row][node.col] < downScore)
                        {
                            a[down.row][down.col] = grid[down.row][down.col]+a[node.row][node.col];
                        }
                        priorityQueue.add(down);
                    }


                }
                visited.add(node);

            }
        }


        return a[targetRow][targetCol];
    }
}
