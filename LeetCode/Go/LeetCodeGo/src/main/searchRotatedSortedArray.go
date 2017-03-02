package main

import (

)


func getMinIndex(nums[] int, start int, end int) int{
    //fmt.Printf(" val =%d, %d", start, end)
    	if start > end {
	    	return -1
    	}	else if start == end {
        	return start
    	} else {
        	mid := (start+end) /2
       		val1 := getMinIndex(nums, start , mid)
	        val2 := getMinIndex(nums, mid+1, end)
        	if nums[val1] < nums[val2] {
	            return val1
        	} else {
	            return val2
        	}
	}
}
func binarySearch(nums []int, target int, start int, end int) int {
	mid := (start+end) /2

	if start == end {
		if nums[start] == target {
			return start
		} else {
			return -1
		}
		
		
	} else if nums[mid] < target {
		return binarySearch(nums, target,mid+1, end)		
	} else {
		return binarySearch(nums, target,start, mid)
	}
	
}
func search(nums []int, target int) int {
    minValueIndex := getMinIndex(nums, 0 , len(nums)-1)
    //fmt.Printf(" search =%d, %d", minValueIndex,  len(nums))
	if minValueIndex < 0 {
		return -1
	} else if minValueIndex ==0{
		return binarySearch(nums, target,minValueIndex , len(nums)-1) 	
		
	} else {
		search1 := binarySearch(nums, target,0 , minValueIndex-1) 	
		search2 := binarySearch(nums, target,minValueIndex , len(nums)-1)
		
		if search1 > search2 {
			return search1
		} else {
			return search2
		}	
	}
}

