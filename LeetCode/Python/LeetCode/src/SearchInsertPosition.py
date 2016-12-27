class Solution(object):
    def searchInsert(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        numsLength  = len(nums);
        for i in range(numsLength):
            if nums[i] >= target:
                return i;
        return numsLength;
nums = [1];
target = 2;
result = Solution().searchInsert(nums, target);

print '%s, %d = %d ' % ( nums, target, result);