class Solution(object):
    def combinationSum2(self, candidates, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        
        candidates.sort(); # [1, 1, 2, 5, 6, 7, 10];
        
        return self.combinationSum2Recursive(candidates,0, target);
    def combinationSum2Recursive(self, candidates,start,  target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        
        if len(candidates) ==0 or target < candidates[0]:
            return [];
        
        result =[];

        
        candidateIndex =start;
        
        while candidateIndex < len(candidates) and  target - candidates[candidateIndex] > 0:
            newCandidate =[];
            if candidateIndex == 0:
                newCandidate = candidates[candidateIndex+1:len(candidates)];
            elif candidateIndex == len(candidates)-1:
                newCandidate = candidates[0:len(candidates)-1];
            else:
                newCandidate = candidates[0:candidateIndex] + candidates[candidateIndex+1:len(candidates)];
                
            result1 = self.combinationSum2Recursive(newCandidate,candidateIndex, target - candidates[candidateIndex]);
           
            if len(result1) > 0 :
                newResult =[];
                for numbers in result1:                    
                    numbers.append(candidates[candidateIndex]);
                    numbers.sort();
                    newResult.append(numbers);
                for numbers in newResult:
                    if not (numbers in result) :
                        result.append(numbers);                    
            
            candidateIndex+=1;
        if target in candidates:
            result.append([target]);
        
        return result;


# candidates = [4,4,2,1,4,2,2,1,3];
# target = 6;
candidates = [1];
target = 2;
result = Solution().combinationSum2(candidates, target);
print '%s, %d = %s' %( candidates, target, result);