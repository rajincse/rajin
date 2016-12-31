class Solution(object):
    def combinationSum(self, candidates, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        candidates.sort();
        
        return self.combinationSumRecursive(candidates,0, target);
            
    def combinationSumRecursive(self, candidates,start,  target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        
        if target < candidates[0]:
            return [];
        
        result =[];

        
        candidateIndex =start;
        
        while candidateIndex < len(candidates) and  target - candidates[candidateIndex] > 0:
            result1 = self.combinationSumRecursive(candidates,candidateIndex, target - candidates[candidateIndex]);
            
            result2 = self.combinationSumRecursive(candidates,candidateIndex, candidates[candidateIndex]);
            
            if len(result1) > 0 and len(result2)>0:
                newResult =[];
                for numbers1 in result1:
                    for numbers2 in result2:
                        numbers = numbers1+numbers2;
                        numbers.sort();
                        newResult.append(numbers);
                for numbers in newResult:
                    if not (numbers in result) :
                        result.append(numbers);                    
            
            candidateIndex+=1;
        if target in candidates:
            result.append([target]);
        
        return result;


candidates = [8,6,4,12,5,7,3,11];
target = 28;
result = Solution().combinationSum(candidates, target);
print '%s, %d = %s' %( candidates, target, result);