class Solution(object):
    def findSubstring(self, s, words):
        """
        :type s: str
        :type words: List[str]
        :rtype: List[int]
        """
        #Frequency
        frequencyMap={};
        for word in words:
            if word in frequencyMap:
                frequencyMap[word] = frequencyMap[word]+1;
            else:
                frequencyMap[word]=1;    
            
        result =[];
        wordLength = len(words[0]);
        wordCount = len(words);
        currentWord = '';
        
        
        for index in range(len(s)-wordLength * wordCount+1):
            bucket = {};
            totalCount =0;
            for j in range(index,index+wordLength* wordCount, wordLength):
                currentWord = s[j:j+wordLength];
                if currentWord not in frequencyMap:
                    break;
                elif currentWord not in bucket:
                    bucket[currentWord] =1;                    
                else:
                    bucket[currentWord] +=1;
                    
                
                                      
                if bucket[currentWord] > frequencyMap[currentWord]:
                        break;                    
                totalCount+=1;
                    
            if totalCount == wordCount:
                result.append(index);
                
                
                
        return result;


s ="barfoothefoobarman";
words = ["foo","bar"]; #6,9,12
result = Solution().findSubstring(s, words);

print ' %s, %s = %s' % (s,words, result);