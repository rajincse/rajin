package algorithm;

public class NumMatrix {
	private int[][] cumulativeMatrix;
	public NumMatrix(int[][] matrix) {
        this.cumulativeMatrix = new int [matrix.length][];
        
        for(int i=0;i<matrix.length;i++)
        {
        	this.cumulativeMatrix[i] = new int[matrix[i].length];
        }
        
        for(int i=0;i<cumulativeMatrix.length;i++)
        {
        	for(int j=0;j<cumulativeMatrix[i].length;j++)
        	{
        		if(i==0 && j==0)
        		{
        			cumulativeMatrix[i][j] = matrix[i][j];
        		}
        		else if(i==0)
        		{
        			cumulativeMatrix[i][j] = matrix[i][j]+cumulativeMatrix[i][j-1];
        		}
        		else if(j==0)
        		{
        			cumulativeMatrix[i][j] = matrix[i][j]+cumulativeMatrix[i-1][j];
        		}
        		else
        		{
        			cumulativeMatrix[i][j] = matrix[i][j]+cumulativeMatrix[i-1][j]+cumulativeMatrix[i][j-1] - cumulativeMatrix[i-1][j-1];
        		}
        	}
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
    	int sum =0;
    	if(row1 == 0 && col1 ==0)
    	{
    		sum=cumulativeMatrix[row2][col2];
    	}
    	else if(row1== 0)
    	{
    		sum =cumulativeMatrix[row2][col2]- cumulativeMatrix[row2][col1-1];
    	}
    	else if(col1 ==0)
    	{
    		sum =cumulativeMatrix[row2][col2]- cumulativeMatrix[row1-1][col2];
    	}
    	else 
    	{
    		sum =cumulativeMatrix[row2][col2]- cumulativeMatrix[row1-1][col2]- cumulativeMatrix[row2][col1-1]+ cumulativeMatrix[row1-1][col1-1];
    	}
    	
        return sum;
    }
    
    public static void main(String[] args)
    {
    	int [][] matrix = {
    			  {3, 0, 1, 4, 2},
    			  {5, 6, 3, 2, 1},
    			  {1, 2, 0, 1, 5},
    			  {4, 1, 0, 1, 7},
    			  {1, 0, 3, 0, 5}
    			};
    	NumMatrix numMatrix = new NumMatrix(matrix);
    	System.out.println(numMatrix.sumRegion(0, 1, 2, 3));
    	System.out.println(numMatrix.sumRegion(1, 2, 3, 4));
    }
}
