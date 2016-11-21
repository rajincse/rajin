class Solution(object):
    def getCommonPrefix(self, str1, str2):       
        shortestLength = min(len(str1), len(str2));
        prefix ="";
        for i in range(shortestLength):
            if str1[i] == str2[i]:
                prefix = prefix + str1[i];
            else:
                break;
    
        return prefix;
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """

        if len(strs) ==0:
            return "";
        
        prefix = strs[0];
        
        for i in range(1, len(strs)):
            prefix = self.getCommonPrefix(prefix, strs[i]);
            
        return prefix;
    
strs = ["Argentina", "Armenia", "America"];
sol = Solution();
result = sol.longestCommonPrefix(strs);

print '%s = %s'% (strs,result);