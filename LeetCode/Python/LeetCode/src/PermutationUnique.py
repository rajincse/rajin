class Solution(object):
    def permuteUnique(self, nums):
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
                    newPermutation =permutation+[number]; 
                    if  newPermutation not in result:
                        result.append(newPermutation);
                   
                    for i in range(1,len(permutation)):
                        newPermutation =permutation[0:i]+[number]+permutation[i:]; 
                        if  newPermutation not in result:
                            result.append(newPermutation);
                    newPermutation =[number]+permutation; 
                    if  newPermutation not in result:
                        result.append(newPermutation);
                   
        return result;
        
nums=[1,1,2];
result=Solution().permuteUnique(nums);
print '%s = %s'%(nums,result);