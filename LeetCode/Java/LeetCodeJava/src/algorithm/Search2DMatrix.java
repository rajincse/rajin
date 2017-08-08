package algorithm;

/**
 * Created by rajin on 7/28/2017.
 */
public class Search2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {-9,-9,-9,-7,-5,-3,-3,-3},
                {-2,-2,0,1,2,3,3,4},
                {5,5,5,7,9,11,11,12},
                {14,14,15,16,18,18,19,20},
                {21,23,24,25,27,29,30,31},
                {34,35,37,38,38,38,40,40},
                {42,44,44,45,47,47,47,48},
                {50,51,51,52,53,54,56,56},
                {58,59,60,62,64,64,64,66}
//                {1, 3, 5, 7},
//                {10, 11, 16, 20},
//                {23, 30, 34, 50}
        };
        int target =3;
        System.out.println(new Search2DMatrix().searchMatrix(matrix,target));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length ==0 || matrix[0].length==0)
        {
            return false;
        }
        int row = getRowIndex(matrix,target, 0, matrix.length-1);
        System.out.println("Row:"+row);
        if(row >=0)
        {
            int column = getColumnIndex(matrix,target,row, 0, matrix[row].length-1);
            return column>=0;
        }

        return  false;
    }
    public int getColumnIndex(int[][] matrix, int target,int row, int start, int end){

        if(start > end)
        {
            return -1;
        }
        else
        {
            int mid = (start+end)/2;
            if(target == matrix[row][mid]){
                return mid;
            }
            else if(target>matrix[row][mid]){
                return getColumnIndex(matrix,target,row,mid+1,end);
            }
            else
            {
                return getColumnIndex(matrix,target,row,start,mid-1);
            }

        }
    }
    public int getRowIndex(int[][] matrix, int target, int start, int end)
    {
        System.out.println("Row search: start:"+start+", end:"+end);
        if(start > end)
        {
            return -1;
        }
        else
        {
            int mid = (start+end)/2;
            if(target >= matrix[mid][0] && target<= matrix[mid][matrix[mid].length-1]){
                return mid;
            }
            else if(target>matrix[mid][matrix[start].length-1]){
                return getRowIndex(matrix,target,mid+1,end);
            }
            else
            {
                return getRowIndex(matrix,target,start,mid-1);
            }

        }

    }

}
