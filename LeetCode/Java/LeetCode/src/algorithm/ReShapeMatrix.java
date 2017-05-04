package algorithm;



public class ReShapeMatrix {

	public static void main(String[] args){
		int [][] nums =new int[][]{ {1,2},
						{3,4} };
		int r = 2;
		int c = 4;
		
		ReShapeMatrix obj = new ReShapeMatrix();
		int[][] result = obj.matrixReshape(nums, r, c);
		
		System.out.println(print2DArray(nums)+", "+r+", "+c +"=>\n"+print2DArray(result));
	}
	
	public static String print2DArray(int [][] nums){
		String msg ="";
		for(int i=0;i<nums.length;i++){
			msg+="[";
			for(int j=0;j<nums[i].length;j++){
				msg+= nums[i][j]+", ";
			}
			msg+="]\n";
		}
		
		return msg;
	}
	
	public int[][] matrixReshape(int[][] nums, int r, int c) {
		int originalR = nums.length;
		int originalC = nums[0].length;
		if(r*c == originalR * originalC){
			int[] oneDimensionalArray = new int[originalR* originalC];
			for(int i=0;i<nums.length;i++){
				for(int j=0;j<nums[i].length;j++){
					oneDimensionalArray[i*originalC+j] = nums[i][j];
				}
			}
			
			int[][] reshapedMatrix = new int[r][c];
			for(int i=0;i<oneDimensionalArray.length;i++){
				int rowIndex = i/c;
				int colIndex = i%c;
				reshapedMatrix[rowIndex][colIndex] = oneDimensionalArray[i];
			}
			
	        return reshapedMatrix;
		}
		else{
			return nums;
		}
		
    }
}
