import sys
class Solution(object):
    def divide(self, dividend, divisor):
        """
        :type dividend: int
        :type divisor: int
        :rtype: int
        """
        if divisor ==0:
            return sys.maxint;
        elif dividend ==0:
            return 0;
        else:
            result =0;
            absDividend = abs(dividend);
            absDivisor = abs(divisor);
            if absDivisor ==1:
                if  divisor < 0:
                    return min(2147483647, -dividend);
                else:            
                    return  dividend;
                
            else:
                while absDividend >= absDivisor:
                    shift =0;
                    while absDividend >= (absDivisor << shift):
                        shift+=1;
                    result+= ( 1<<(shift-1));
                    absDividend -= ( absDivisor <<(shift-1));
                    
                if (divisor > 0 and dividend >0 ) or (divisor < 0 and dividend < 0):
                    return result;
                else:
                    return -result;

dividend = -2147483648;
divisor = -1;
result = Solution().divide(dividend, divisor);

print '%d / %d = %d '% ( dividend, divisor, result); 