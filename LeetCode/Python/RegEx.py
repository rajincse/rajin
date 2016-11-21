class Solution(object):
    
    def isMatch(self, s, p):
        
        if len(p) == 0 :
            return len(s) == 0 ;

        if len(p) == 1:
            if len(s) == 0:
                return False;
            elif not(s[0] == p[0] or p[0] =='.') :
                return False;
            else:
                return self.isMatch(s[1:], p[1:]);

        if p[1] != '*':
            if len(s) < 1:
                return False;
            elif not(s[0] == p[0] or p[0] =='.') :
                return False;
            else:
                return self.isMatch(s[1:], p[1:]);
        else :
            if self.isMatch(s, p[2:]):
                
                return True;
            i=0;
            while i< len(s) and (s[i] ==p[0] or p[0] =='.'):
                if self.isMatch(s[i+1:],p[2:]):
                    return True;
                i = i+1;
        return False;
    
sol = Solution();

rajin ='Rajin'
print '%s' % (rajin[5:]);
#result =sol.isMatch("aaaaaaaaaaaaab","a*a*a*a*a*a*a*a*a*a*c");
result =sol.isMatch("aaaaaaaaaaaaab","a*a*a*a*c");

print "%r" % (result)
