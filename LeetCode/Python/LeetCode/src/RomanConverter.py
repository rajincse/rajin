from math import floor;
class Solution(object):
    
    def multiPly(self, character, times):
        result ='';
        for i in range(times):
                result = result+character;
        return result;
        
    def intToRoman(self, num):
        """
        :type num: int
        :rtype: str
        """
        result ='';
        currentNum = num;
        #1000s
        thousands =int(floor( currentNum /1000));
        if thousands > 0:
            result = result+ self.multiPly('M', thousands);
        
        currentNum  = currentNum - thousands * 1000;
        #100s
        hundreds = int(floor(currentNum/100 ));
        if hundreds == 9:
            result = result+'CM';
        elif hundreds >= 5:
            result = result +'D'+self.multiPly('C', hundreds -5);
        elif hundreds == 4:
            result = result +'CD';
        else:
            result = result + self.multiPly('C', hundreds);
            
        currentNum  = currentNum - hundreds * 100;
        #10s
        tens = int(floor(currentNum/10));
        if tens == 9:
            result = result+'XC';
        elif tens >= 5:
            result = result +'L'+self.multiPly('X', tens -5);
        elif tens == 4:
            result = result +'XL';
        else:
            result = result + self.multiPly('X', tens);
        
        currentNum  = currentNum - tens * 10;    
        
        #1s
        ones = currentNum;
        
        if ones == 9:
            result = result+'IX';
        elif ones >= 5:
            result = result +'V'+self.multiPly('I', ones -5);
        elif ones == 4:
            result = result +'IV';
        else:
            result = result + self.multiPly('I', ones);
        return result;
num =77;

sol = Solution();
result = sol.intToRoman(num);

print 'result %s'% (result);        