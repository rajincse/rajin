import sys
class Solution(object):
    def threeSumClosest(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        
        distance = sys.maxint;
        closestTarget =-1; 
        nums.sort();
        
        for i in range(len(nums)-2):
            if i== 0 or nums[i] != nums[i-1]:
                left = i+1;
                right = len(nums)-1;
                
                while left < right:
                    currentDistance =nums[i]+ nums[left]+nums[right] -target;
                    if abs(currentDistance) < distance:
                        distance = abs(currentDistance);
                        closestTarget = nums[i]+ nums[left]+nums[right];
                    
                    if  currentDistance== 0:
                        return closestTarget;
                    elif currentDistance <0:
                         
                        if left < right:
                            left+=1;
                            
                        while left < right and nums[left-1] == nums[left]:
                            left+=1;
                    else:
                        if left < right:
                            right-=1;
                            
                        while left < right and nums[right] == nums[right+1]:
                            right-=1;
        
        return closestTarget;                    
                        
nums =[0,2,1,-3];
target = 1;
result= Solution().threeSumClosest( nums, target);

print "%s, %d = %d" % (nums, target, result);