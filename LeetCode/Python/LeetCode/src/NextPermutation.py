class Solution(object):
    def nextPermutation(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        candidate1 =-1;
        candidate2 =-1;
        for i in range(len(nums)-1, 0,-1):
            if nums[i-1] < nums[i]:
                candidate1 = i-1;
                break;
        if candidate1 >=0:        
            for i in range(len(nums)-1, candidate1,-1):
                if nums[candidate1] < nums[i]:
                    candidate2 =i;
                    break;
        
        #swap
        if candidate1>=0 and candidate2>= 0:
            temp = nums[candidate1];
            nums[candidate1] = nums[candidate2];
            nums[candidate2] = temp;
            
        #reverse
        reverseRange = int((len(nums)- candidate1-1)/2);
        
        for i in range(reverseRange):
            temp = nums[candidate1+1+i];
            nums[candidate1+1+i] = nums[len(nums)-1-i];
            nums[len(nums)-1-i] = temp;
            
        
        
            
            
nums=[1, 4, 3, 2];
print '%s ->'% nums;
Solution().nextPermutation(nums);
print '->%s'% nums;