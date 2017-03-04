package main

import (
	"fmt"
)
func printBoard(board [][]byte){
	for i :=0; i< len(board); i++ {
		if i %3 ==0{
			fmt.Println()
		}
			
		for j :=0; j< len(board[i]);j++ {
			if j % 3 ==0 {
				fmt.Print("   ")
			}
			fmt.Printf("%c,  ", board[i][j])
			
		}
		fmt.Println()
	}
}
func isValid(board [][]byte,  val byte, row int ,  col int) bool {
	
	boxRow :=  (row/3) * 3
	boxCol := (col/3) * 3
	 
	for i:= 0;i< len(board) ;i++ {
		if board[i][col] == val {
			return false;
		}
		if board[row][i] == val {
			return false;
		}
		if board[boxRow+i /3][boxCol + i %3] == val {
				return false;
		}
		
	}
	
	
	
	return true
}

func backTrack(board [][]byte) bool{
//	fmt.Println("-----------------------------Backtracking--------------------------")
//	printBoard(board) 
	for i:= 0 ; i< len(board) ; i++ {
    	for j:= 0; j< len(board[i]); j++ {
    		if board[i][j] == '.' {
    			for c:= byte( '1'); c<='9' ;c++ {
    				
    				if isValid(board, c, i, j) {
    	
//					    fmt.Println("---------------------------------------------------------------------------")
//    					fmt.Printf("valid %d, %d => %c\n", i, j, c)
//    					fmt.Println("---------------------------------------------------------------------------")
    					
    					board[i][j] = c;
    					success :=backTrack(board)
    					
    					
    					if  success {
    						return true
    					} else {
//    						fmt.Printf("Unsuccessful ........%d, %d => %c\n", i, j, c)
    						board[i][j] ='.'
    					}
    				}
    				
    			}
//    			fmt.Printf("Unsuccessful ........No value found ( %d, %d)!\n", i,j)
    			return false
    			
		    	
		    }
    	}
    }
	return true
}
func solveSudoku(board [][]byte)  {
    backTrack(board)
}
//func main() {
//	board := [][]byte {
//		{'5', '3', '.',   '.', '7', '.',   '.', '.', '.' },
//		{'6', '.', '.',   '1', '9', '5',   '.', '.', '.' },
//		{'.', '9', '8',   '.', '.', '.',   '.', '6', '.' },
//		
//		{'8', '.', '.',   '.', '6', '.',   '.', '.', '3' },
//		{'4', '.', '.',   '8', '.', '3',   '.', '.', '1' },
//		{'7', '.', '.',   '.', '2', '.',   '.', '.', '6' },
//		
//		{'.', '6', '.',   '.', '.', '.',   '2', '8', '.' },
//		{'.', '.', '.',   '4', '1', '9',   '.', '.', '5' },
//		{'.', '.', '.',   '.', '8', '.',   '.', '7', '9' },
//	}
//	printBoard(board)
//	fmt.Println("-------------")
//	solveSudoku(board)
//	printBoard(board)
//	
//	
//}