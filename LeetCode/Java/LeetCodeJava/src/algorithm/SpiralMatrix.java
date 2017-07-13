package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rajin on 7/12/2017.
 */
public class SpiralMatrix {
    public static void main(String []args){
//        int[][] matrix = new int[][]{
//                {1,2,3,4},
//                {4,5,6,5},
//                {3,0,7,6},
//                {2,9,8,7},
//                {1,0,9,8}
//        };
//        int[][] matrix = new int[][]{{2},{3}};
        int[][] matrix = new int[][]{
                {1,2,3},
                {4,5,6},
                {3,0,7},
//                {2,9,8,7},
//                {1,0,9,8}
        };
        SpiralMatrix obj = new SpiralMatrix();
        List<Integer> result = obj.spiralOrder(matrix);
        System.out.println(result);

    }
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length ==0){
            return new ArrayList<Integer>();
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int minDimension =Math.min(m,n);
        int maxLayer =(int)Math.ceil(1.0* minDimension/2);
//        System.out.println("MaxLayer :"+maxLayer);
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int l=0;l<maxLayer;l++){
            //top
//            System.out.print("Layer:"+l+", top:");
            for(int i=0;i<n-2*l;i++){
//                System.out.print(matrix[l][l+i]+",");
                result.add(matrix[l][l+i]);
            }

//            System.out.println();
            //right
//            System.out.print("Layer:"+l+", right:");
            for(int i=1;i<m-2*l-1;i++){
//                System.out.print(matrix[l+i][n-l-1]+",");
                result.add(matrix[l+i][n-l-1]);
            }
//            System.out.println();
            //bottom
//            System.out.print("Layer:"+l+", bottom:");
            for(int i=0;i<n-2*l && m-2*l != 1;i++){
//                System.out.print(matrix[m-l-1][n-l-1-i]+",");
                result.add(matrix[m-l-1][n-l-1-i]);
            }
//            System.out.println();
            //left
//            System.out.print("Layer:"+l+", left:");
            for(int i=1;i<m-2*l-1 && n-2*l != 1;i++){
//                System.out.print(matrix[m-l-1-i][l]+",");
                result.add(matrix[m-l-1-i][l]);
            }
//            System.out.println();
        }

        return  result;
    }
}
