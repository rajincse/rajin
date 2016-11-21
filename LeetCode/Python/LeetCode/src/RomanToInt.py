class Solution(object):
    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        map ={"M":1000, "D":500, "C":100, "L": 50, "X": 10, "V": 5, "I": 1};
        result =0;
        
        previous = -1;
        for i in range(len(s)-1, -1, -1):
            current = map[s[i]];
            if current < previous:
                result = result -current;
            else:
                result = result + current;
            previous = current;    
                
        
        
        return result;
        

s = "MMMCMXCIX";
sol = Solution();
result = sol.romanToInt(s);

print '%s = %d'% (s,result);