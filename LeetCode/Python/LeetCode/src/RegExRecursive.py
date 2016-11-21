class Solution:
    # @return a boolean
    def isMatch(self, s, p):
        print 'Matching %s, %s' %(s,p);
        if  len(s) == 0 and len(p) ==0:            
            return True;                
        elif len(s) ==0 and len(p) > 0:
            if p[len(p)-1] =='*':
                return self.isMatch(s, p[:len(p)-2]);
        elif len(s) > 0 and len(p) ==0:
            return False;
        else:
            if p[len(p)-1] == '.':
                return self.isMatch(s[:len(s)-1], p[:len(p)-1]);
            elif p[len(p)-1] == '*':
                isMatchWithoutKleeneStar = self.isMatch(s, p[:len(p)-2]);
                isMatchPreviousChar = self.isMatch(s, p[:len(p)-1]);
                previousPatternChar = p[len(p)-2];
                isMatchCurrentChar = previousPatternChar =='.' or previousPatternChar ==s[len(s)-1];
                isMatchWithoutLastChar = self.isMatch(s[:len(s)-1], p) and isMatchCurrentChar; 
                return  isMatchWithoutKleeneStar or isMatchPreviousChar or isMatchWithoutLastChar;
            else:
                return s[len(s)-1] == p[len(p)-1] and self.isMatch(s[:len(s)-1], p[:len(p)-1]);         
        return False;
        
sol = Solution();
str = 'aaaaaaaaaaaaab';
p = 'aa*a*a*a*a*a*a*a*a*a*c';
#result =sol.isMatch("aaaaaaaaaaaaab","a*a*a*a*a*a*a*a*a*a*c");
#result =sol.isMatch("aaaaaaaaaaaaab","a*c");
result = sol.isMatch(str, p);
print "%r" % (result)
