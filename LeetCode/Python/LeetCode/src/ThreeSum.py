class Solution(object):
    def getSortedList(self, n1,n2, n3):
        list =[];
        
        if n1 <= n2 and n1 <= n3:
            list.append(n1);
            list.append(min(n2,n3));
            list.append(max(n2,n3));
        elif n2 <= n3 and n2 <= n1:
            list.append(n2);
            list.append(min(n3,n1));
            list.append(max(n3,n1));
        else:
            list.append(n3);
            list.append(min(n1,n2));
            list.append(max(n1,n2));
        
        #print 'sorting %d, %d, %d => %s'%(n1,n2,n3, list);
        return list;
    def twoSum(self, source, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        map= {};
        mapIndex ={};
        for i in range(len(nums)):
            n = nums[i];
            map[target-n]= n;
            mapIndex[target-n] = i;
        result =[];
        for i in range(len(nums)):
            n = nums[i];
            if n in map and i != mapIndex[n]:
                candidate = self.getSortedList(source, n, map[n]);
                
                result.append(candidate);
        
        return result;
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        result =[];
        for i in range(len(nums)):
            n = nums[i];
            target = -n;
            preList = nums[0:i];
            postList = nums[i+1:len(nums)];
            twoSum = self.twoSum(n,preList+postList, target);
            for l in twoSum:
                candidate =  l;              
                if candidate not in result:                
                    result.append(candidate);
        
        return result;
    
#s = [-1, 0, 1, 2, -1, -4];
s=[8,5,12,3,-2,-13,-8,-9,-8,10,-10,-10,-14,-5,-1,-8,-7,-12,4,4,10,-8,0,-3,4,11,-9,-2,-7,-2,3,-14,-12,1,-4,-6,3,3,0,2,-9,-2,7,-8,0,14,-1,8,-13,10,-11,4,-13,-4,-14,-1,-8,-7,12,-8,6,0,-15,2,8,-4,11,-4,-15,-12,5,-9,1,-2,-10,-14,-11,4,1,13,-1,-3,3,-7,9,-4,7,8,4,4,8,-12,12,8,5,5,12,-7,9,4,-12,-1,2,5,4,7,-2,8,-12,-15,-1,2,-11];
sol = Solution();
result = str(sol.threeSum(s));

print '%s = %s'% (s,result);