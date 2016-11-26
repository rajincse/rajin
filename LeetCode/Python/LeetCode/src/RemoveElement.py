class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        if len(nums) ==0:
            return 0;
        elif len(nums) ==1:
            if nums[0] == val:
                return 0;
            else:
                return 1;
        else:
            length =0;
            for i in range(len(nums)):
                if nums[i]== val:
                    for j in range(i+1, len(nums)):
                        if nums[j] != val:
                            nums[i] = nums[j];
                            nums[j] = val;
                            length+=1;
                            break;
                else:
                    length+=1;
            return length;    
            
        
nums =[3,2,2,3];
val =3;
numsString = str(nums);
result = Solution().removeElement(nums, val);
print '%s = %s (%d)'% (numsString, nums, result);