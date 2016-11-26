class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
                
        
        if len(nums) ==0:
            return 0;
        
        i=0;
        for j in range(1, len(nums)):
            if nums[j] != nums[i]:
                i+=1;
                nums[i] = nums[j];
        return i+1;
       
       
        
                
# public int removeDuplicates(int[] nums) {
#     if (nums.length == 0) return 0;
#     int i = 0;
#     for (int j = 1; j < nums.length; j++) {
#         if (nums[j] != nums[i]) {
#             i++;
#             nums[i] = nums[j];
#         }
#     }
#     return i + 1;
# }
nums =[1,1,2,2,3];
numsString = str(nums);
result = Solution().removeDuplicates(nums);
print '%s = %s '% (numsString, result);