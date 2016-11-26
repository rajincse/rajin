class Solution(object):
    def strStr(self, haystack, needle):
        """
        :type haystack: str
        :type needle: str
        :rtype: int
        """
    
        if len(needle) ==0 :
            return 0;
        for i in range(len(haystack)- len(needle)+1):
            if haystack[i] == needle[0]:
                isMatch = True;
                for j in range(len(needle)):
                    if i+j >= len(haystack) or haystack[i+j] != needle[j]:
                        isMatch = False;
                        break;
                if isMatch :
                    return i;
        return -1;
            
haystack = 'a';
needle = 'a';
result = Solution().strStr(haystack, needle);

print '%s, %s = %d' % ( haystack, needle, result);