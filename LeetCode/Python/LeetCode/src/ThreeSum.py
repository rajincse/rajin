class Solution(object):    
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        nums.sort();
        result =[];
        for i in range(len(nums)-2):
            if i==0 or nums[i] != nums[i-1]:
                left = i+1;
                right = len(nums)-1;
                while left < right:
                    if nums[left]+nums[right] == -nums[i]:
                        result.append([nums[i], nums[left], nums[right]]);
                        left+=1;
                        right-=1;
                        while left < right and nums[left] == nums[left-1]:
                            left+=1;
                        
                        while left < right and nums[right] == nums[right+1]:
                            right-=1;
                    elif nums[left]+nums[right] < -nums[i]:
                        if left < right:
                            left+=1;
                        while left < right and nums[left] == nums[left-1]:
                            left+=1;
                    else:
                        if left < right:
                            right-=1;
                        while left < right and nums[right] == nums[right+1]:
                            right-=1;
        
        return result;
    
#s = [-1, 0, 1, 2, -1, -4];
s=[8,5,12,3,-2,-13,-8,-9,-8,10,-10,-10,-14,-5,-1,-8,-7,-12,4,4,10,-8,0,-3,4,11,-9,-2,-7,-2,3,-14,-12,1,-4,-6,3,3,0,2,-9,-2,7,-8,0,14,-1,8,-13,10,-11,4,-13,-4,-14,-1,-8,-7,12,-8,6,0,-15,2,8,-4,11,-4,-15,-12,5,-9,1,-2,-10,-14,-11,4,1,13,-1,-3,3,-7,9,-4,7,8,4,4,8,-12,12,8,5,5,12,-7,9,4,-12,-1,2,5,4,7,-2,8,-12,-15,-1,2,-11];
sol = Solution();
result = str(sol.threeSum(s));

print '%s = %s'% (s,result);