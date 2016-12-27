class Solution(object):
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        
        currentItem = '1';        
        
        for index in range(n-1):
            currentNumber =currentItem[0];
            currentCount =1;
            newItem = '';
            for characterIndex in range(1,len(currentItem)):
                if currentItem[characterIndex] == currentNumber:
                    currentCount+=1;
                else:
                    newItem += str(currentCount)+currentNumber;
                    currentNumber =currentItem[characterIndex];
                    currentCount =1;
            currentItem = newItem +str(currentCount)+currentNumber;
            
        return currentItem;
    
n = 7;
result = Solution().countAndSay(n);

print '%d = %s' % ( n, result);