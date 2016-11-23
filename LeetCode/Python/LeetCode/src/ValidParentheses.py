class Solution(object):
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        
        stack =[];
        for i in range(len(s)):
            character = s[i];
            if len(stack) ==0 or character=='(' or character =='{' or character=='[':
                stack.append(character);
            else:
                stackTop = stack.pop();
                if stackTop =='(' and character != ')':
                    return False;
                elif stackTop =='{' and character != '}':
                    return False;
                elif stackTop =='[' and character != ']':
                    return False;                
            
            
        return len(stack) ==0;
s="[";
result = Solution().isValid(s);

print '%s = %s'%(s,result);