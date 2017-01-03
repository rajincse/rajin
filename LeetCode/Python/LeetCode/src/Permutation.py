class Solution(object):
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        result =[];
        
        if len(nums)==0:
            return result;
        
        for number in nums:
            itemCount = len(result);
            if itemCount ==0:
                result.append([number]);                
            else:                
                for p in range(itemCount):
                    permutation = result.pop(0);
                    result.append(permutation+[number]);
                    for i in range(1,len(permutation)):
                        result.append(permutation[0:i]+[number]+permutation[i:]);
                    
                    result.append([number]+permutation);
        
        return result;
        
nums=[1,2,3];
result=Solution().permute(nums);
print '%s = %s'%(nums,result);