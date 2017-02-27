package algorithm;

public class MineSweeper {

	//[['B', '1', 'E', '1', 'B'],
//	 ['B', '1', 'M', '1', 'B'],
//	 ['B', '1', '1', '1', 'B'],
//	 ['B', 'B', 'B', 'B', 'B']]
	
	public static void main(String[] args)
	{
		char [][] board = {
				{'E', 'E', 'E', 'E', 'E'},
				{'E', 'E', 'M', 'E', 'E'},
				{'E', 'E', 'E', 'E', 'E'},
				{'E', 'E', 'E', 'E', 'E'}
		};
		int []click ={ 3,0};
		
		MineSweeper ms = new MineSweeper();
		ms.printBoard(board);
		board = ms.updateBoard(board, click);
		ms.printBoard(board);
	}
	
	public void printBoard(char[][] board)
	{
		System.out.println("-------------------");
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j]+"|");
			}
			System.out.println();
		}
	}
	public char[][] reveal(char[][] board, int r, int c)
	{
		//System.out.println("("+r+", "+c+")");
		int height = board.length;
		int width = board[0].length;
		
		
		if(r < board.length && r >=0 && c < board[0].length && c >=0)
		{
			if( board[r][c] != 'E' )
			{
				return board;
			}
			int mineCount =0;
			if(r-1 >= 0  && c-1 >=0 && board[r-1][c-1] =='M')
			{
				mineCount++;
			}
			if(r-1 >= 0  && board[r-1][c] =='M')
			{
				mineCount++;
			}
			if(r-1 >= 0  && c+1 < width &&  board[r-1][c+1] =='M')
			{
				mineCount++;
			}
			
			if(c-1 >=0 && board[r][c-1] == 'M')
			{
				mineCount++;
			}
			if( c+1 < width && board[r][c+1] =='M')
			{
				mineCount++;
			}
			
			if(r+1 < height && c-1 >=0 && board[r+1][c-1] =='M')
			{
				mineCount++;
			}
			if(r+1 < height && board[r+1][c] =='M')
			{
				mineCount++;
			}
			if(r+1 < height && c+1 < width && board[r+1][c+1] =='M')
			{
				mineCount++;
			}
			
			
			
			
			
			if(mineCount ==0)
			{
				board[r][c] ='B';
			}
			else
			{
				board[r][c] = (char) ('0'+mineCount);
				return board;
			}
			
			
			
			if(r-1 >= 0  && c-1 >=0 )
			{
				board = reveal(board, r-1, c-1);
			}
			if(r-1 >= 0  )
			{
				board = reveal(board, r-1, c);
			}
			if(r-1 >= 0  && c+1 < width )
			{
				board = reveal(board, r-1, c+1);
			}
			
			if(c-1 >=0 )
			{
				board = reveal(board, r, c-1);
			}
			if( c+1 < width )
			{
				board = reveal(board, r, c+1);
			}
			
			if(r+1 < height && c-1 >=0 )
			{
				board = reveal(board, r+1, c-1);
			}
			if(r+1 < height )
			{
				board = reveal(board, r+1, c);
			}
			if(r+1 < height && c+1 < width )
			{
				board = reveal(board, r+1, c+1);
			}
			
				
		}
		return board;
	}
	public char[][] updateBoard(char[][] board, int[] click) {
		int r = click[0];
		int c = click[1];
        if(board[r][c] == 'M')
        {
        	board[r][c] = 'X';        	
        }
        else
        {
        	board = reveal(board, r, c);
        }
        	
		return board;
    }
}
