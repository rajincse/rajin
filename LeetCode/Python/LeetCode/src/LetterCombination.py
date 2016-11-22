class Solution(object):
    def letterCombinations(self, digits):
        """
        :type digits: str
        :rtype: List[str]
        """
        
        map={"1":"", "2":"abc", "3":"def", "4":"ghi",
              "5":"jkl", "6":"mno", "7": "pqrs", "8":"tuv", "9":"wxyz", "0":""};
        
        combinations=[];
        for i in range(len(digits)):
            elements = map[digits[i]];
            newCombinations =[];
            for j in range(len(elements)):
                if i ==0:
                    newCombinations.append(elements[j]);
                else:                    
                    for k in range(len(combinations)):
                        newCombinations.append(combinations[k]+elements[j]);
            combinations = newCombinations;           
        

        return combinations;
digits="23";
result = Solution().letterCombinations(digits);

print "%s = %s" %(digits, result);