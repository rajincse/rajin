package algorithm;

import java.util.Arrays;

/**
 * Created by rajin on 8/19/2017.
 */
public class ImageSmoother {
    public static void main(String[] args)
    {
        int [][] M = new int[][]{{2,3,4},{5,6,7},{8,9,10},{11,12,13},{14,15,16}};
        ImageSmoother obj = new ImageSmoother();
        int[][] result = obj.imageSmoother(M);
        for(int[] r: result)
        {
            System.out.println(Arrays.toString(r));
        }

    }
    public int[][] imageSmoother(int[][] M) {
        if(M.length ==0) return M;

        int [][] result = new int[M.length][M[0].length];
        for(int i=0;i<M.length;i++)
        {
            for(int j=0;j<M[0].length;j++)
            {
                double sum =0;
                int count =0;
                for(int k=i-1;k<=i+1;k++)
                {
                    for(int l=j-1;l<=j+1;l++)
                    {
                        if(k>=0 && k<M.length && l>=0 && l<M[0].length)
                        {
                            sum+= M[k][l];
                            count++;
                        }
                    }
                }
                double avg = sum/count;
                result[i][j] = (int)Math.floor(avg);

            }
        }
        return result;
    }
}
