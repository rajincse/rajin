package main

import (
	"fmt"
)

func main() {
	matrix := [][]int{ {1,2},{3,4}}    
    printMatrix(matrix)
    rotate(matrix)
    fmt.Println("After Rotation")
    printMatrix(matrix)
}
