package main 

import "fmt"

func rotate(matrix [][]int)  {
	n := len(matrix)
	for i:=0; i < n/2 ;i++{
	
			
	    for j:=0; j< n-1-2*i;j++{
	    	p0X := i
	    	p0Y := j+i
	    	
	    	p1X := n-1-j-i
	    	p1Y := i 
	    	
	    	
	    	
	    	p2X := n-1 -i
	    	p2Y := n-1-j -i
	    	
	    	
	    	
	    	p3X := j +i
	    	p3Y := n-1 -i
	    	
	    	temp:= matrix[p0X][p0Y]
	    	matrix[p0X][p0Y] =matrix[p1X][p1Y]
	    	matrix[p1X][p1Y] =matrix[p2X][p2Y]
	    	matrix[p2X][p2Y] = matrix[p3X][p3Y]
	    	matrix[p3X][p3Y] = temp
	    }
	}
}

func printMatrix(matrix [][]int)  {
	for i:=0; i <len(matrix); i++{
		for j:=0;j< len(matrix[i]);j++{
			fmt.Printf("%v , ", matrix[i][j])	
		}
		fmt.Println();
		
	}
}