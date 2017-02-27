package main



func checkSubarraySum(nums []int, k int) bool {    
    
    n:= len(nums)
    for left:= 0; left < n ; left++{
    	sum:= nums[left]
    	for right:= left+1; right < n ; right ++{
    		sum += nums[right]
    		
    		if k ==0 {
    			return (sum == 0)
    		} else if (sum % k) == 0{
    			return true
    		} 
    		
    	}
    }
    return false
}

