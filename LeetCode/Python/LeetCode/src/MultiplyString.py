class Solution(object):
    def multiply(self, num1, num2):
        """
        :type num1: str
        :type num2: str
        :rtype: str
        """
        len1 = len(num1);
        len2 = len(num2);
        intermediate = [];
        zero='';
        for j in range(len2):
            intermediate.append(zero);
            zero+='0';
        maxDigits =0;
        for j in range(len2):
            digit2 = ord(num2[len2-1-j])-48; # Ascii '0' = 48
            inHand =0;
            for i in range(len1):
                digit1 = ord(num1[len1-1-i])-48;
                mul = digit1 * digit2 + inHand;                
                onesPosition = mul%10;
                inHand = int(mul/10);
                intermediate[j] = str(onesPosition)+intermediate[j];
            if inHand > 0:
                intermediate[j] = str(inHand)+intermediate[j];
            maxDigits = max(maxDigits, len(intermediate[j]));
            
        #print '%s' % (intermediate);
        #add all intermediates
        result ='';
        inHand =0;
        for i in range(maxDigits):            
            currentSum =0;
            for j in range(len(intermediate)):
                digit =0;
                if i < len(intermediate[j]):
                    digit = ord(intermediate[j][len(intermediate[j])-1-i]) - 48;
                currentSum = currentSum+ digit;
            currentSum+=  inHand;            
            onePosition =currentSum%10;
            inHand = int(currentSum/10);             
            result = str(onePosition)+result;
        if inHand > 0:
            result = str(inHand)+result;
        
        #check if all zeros
        allZero = True;
        for i in range(len(result)):
            if result[i]!='0':                
                allZero = False;
                break;
        if allZero == True:
            return '0';
        else:
            return result; 
        
num1 = "140";
num2 = "721"; #"100940"
result = Solution().multiply(num1, num2);
print '%s X %s = %s'%(num1, num2, result);