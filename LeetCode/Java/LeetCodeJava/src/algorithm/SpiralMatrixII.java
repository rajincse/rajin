package algorithm;

/**
 * Created by rajin on 7/20/2017.
 */
public class SpiralMatrixII {
    public static void main(String[] args){
        int n = 6;
        int[][] res = new SpiralMatrixII().generateMatrix(n);
        for(int i=0;i<res.length;i++){
            for(int j=0;j<res[i].length;j++){
                System.out.print(res[i][j]+", ");
            }
            System.out.println();
        }
    }
    public int[][] generateMatrix(int n) {

        int[][] a = new int[n][n];
        int maxLayer = (int) Math.ceil(1.0 * n/2);
        int val = 1;
        for(int l =0;l< maxLayer;l++){
            //top
            for(int i=0;i<n-2*l;i++){
                a[l][l+i] = val++;
            }

            //right
            for(int i=1;i<n-1-2*l;i++){
                a[l+i][n-1-l] =val++;
            }

            //bottom
            if(l != n-1-l)
            {
                for(int i=0;i<n-2*l;i++){
                    a[n-1-l][n-1-l-i] = val++;
                }
            }
            //left
            if(l != n-1-l)
            {

                for(int i=1;i < n-1-2*l;i++){
                    a[n-1-l-i][l] = val++;
                }
            }


        }

        return a;
    }
}
