package algorithm;

import java.util.Arrays;

/**
 * Created by rajin on 7/28/2017.
 */
public class ZeroMatrix {
    public static void main(String[] args){
        int[][] matrix  = new int[][]{
//                {1,2,1},
//                {1,0,1},
//                {1,2,3}
                {0,0,0,5},
                {4,3,1,4},
                {0,1,1,4},
                {1,2,1,3},
                {0,0,1,1}
        };
        ZeroMatrix obj = new ZeroMatrix();
        obj.setZeroes(matrix);
        System.out.println("Result:");
        obj.printMatrix(matrix);


    }
    public void printMatrix(int [][] matrix){
        System.out.println("------------");
        for(int i=0;i<matrix.length;i++)
        {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println("------------");
    }
    public void setZeroes(int[][] matrix) {

        if(matrix.length ==0)
        {
            return;
        }

        boolean rowHasZero = false;
        boolean columnHasZero = false;

        for(int i=0;i<matrix[0].length;i++)
        {
            if(matrix[0][i]==0)
            {
                rowHasZero = true;
            }
        }
        for(int i=0;i<matrix.length;i++)
        {
            if(matrix[i][0]==0)
            {
                columnHasZero = true;
            }
        }

//        this.printMatrix(matrix);
        for(int i=1;i<matrix.length;i++)
        {
            for(int j=1;j<matrix[i].length;j++)
            {
                if(matrix[i][j]==0)
                {
                    matrix[i][0] =0;
                    matrix[0][j] =0;
                }
            }
        }

//        this.printMatrix(matrix);

        for(int i=1;i<matrix.length;i++)
        {
            if(matrix[i][0]==0)
            {
                nullifyRow(matrix,i);
            }
        }
//        this.printMatrix(matrix);
        for(int i=1;i<matrix[0].length;i++)
        {
            if(matrix[0][i]==0)
            {
                nullifyColumn(matrix,i);
            }
        }

        this.printMatrix(matrix);
        if(rowHasZero)
        {
            nullifyRow(matrix,0);
        }
        if(columnHasZero)
        {
            nullifyColumn(matrix,0);
        }

    }

    public  void nullifyRow(int[][] matrix, int row)
    {
        for(int i=0;i<matrix[row].length;i++)
        {
            matrix[row][i] =0;
        }
    }
    public  void nullifyColumn(int[][] matrix, int column)
    {
        for(int i=0;i<matrix.length;i++)
        {
            matrix[i][column] =0;
        }
    }

}
