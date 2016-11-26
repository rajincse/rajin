class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
       
        i =0;
        for j in range(len(nums)):
            if nums[j] != val:
                nums[i] = nums[j];
                i+=1; 
        return i;
        
nums =[3,2,2,3];
val =3;
numsString = str(nums);
result = Solution().removeElement(nums, val);
print '%s = %s (%d)'% (numsString, nums, result);