class Solution(object):
    def getMinIndex(self, nums, start, end, target):
        
        if start == end:
            if nums[start] == target:
                return start;
            else:
                return -1;
        
        mid = (start+end)/2;
        
        if nums[mid] >= target:
            return self.getMinIndex(nums, start, mid, target);
        else:
            return self.getMinIndex(nums, mid+1, end, target);
        
        return -1;
    def getMaxIndex(self, nums, start, end, target):
        
        if start == end:
            if nums[start] == target:
                return start;
            else:
                return -1;
        
        mid = (start+end)/2;
        
        if nums[mid+1] <= target:
            return self.getMaxIndex(nums, mid+1, end, target);
        else:
            return self.getMaxIndex(nums, start, mid, target);
        
        return -1;
    
    def searchRange(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        start = 0;
        end = len(nums)-1;
        
        min = self.getMinIndex(nums, start, end, target);
        max = self.getMaxIndex(nums, start, end, target);
        return [min, max];
        
        
# nums = [5, 7, 7, 8, 8, 10];
nums = [2,3,4,8,10];
target = 8;

result = Solution().searchRange(nums, target);

print '%s, %d = %s ' % (nums, target ,result);