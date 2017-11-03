package algorithm;

import java.util.*;

/**
 * Created by rajin on 10/14/2017.
 * 698. Partition to K Equal Sum Subsets
 */
public class CanPartition {
    public static void main(String [] args)
    {
        int[] nums = new int[]{4, 3, 2, 3, 5, 2, 1};
        //[][4, 3, 2, 3, 5, 2, 1][2,2,10,5,2,7,2,2,13] [2,2,2,4,3,1,1]
        //3
        int k = 4;
        System.out.println(new CanPartition().canPartitionKSubsets(nums, k));
    }


    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null ||nums.length ==0)
        {
            return k==0;
        }
        Arrays.sort(nums);
        int sum = 0;
        for(int n: nums)
        {
            sum+=n;
        }
        if(sum %k != 0) return false;
        sum /= k;
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> combinations = getAllCombinations(nums,0, sum,
                new ArrayList<Integer>(), new ArrayList<List<Integer>>());
        List<Integer> numList = new ArrayList<Integer>();
        for(int n: nums)
        {
            numList.add(n);
        }
//        for(List<Integer> com: combinations)
//        {
//            System.out.println(com);
////            List<Integer> setMinus = setMinus(numList, com);
////            System.out.println("Set minus:"+setMinus+", contains:"+contains(com, setMinus));
//        }
        boolean possible =dfs(combinations, numList,0, k);
        return possible;

    }

    public boolean dfs(List<List<Integer>> combinations, List<Integer> remaining, int index, int k)
    {
        if(remaining.isEmpty() && k ==0)
        {
            return true;
        }
        else
        {
            for(int i= index;i< combinations.size();i++)
            {
                List<Integer> currentCombination = combinations.get(i);
                if(contains(remaining, currentCombination))
                {
                    List<Integer> setMinus = setMinus(remaining, currentCombination);
                    if( dfs(combinations, setMinus,i+1,k-1))
                    {
                        return true;
                    }
                }



            }
            return false;
        }
    }
    public boolean contains(List<Integer> container, List<Integer> values)
    {
        HashMap<Integer, Integer> occurrence = new HashMap<Integer, Integer>();
        for(int n: container)
        {
            if(occurrence.containsKey(n))
            {
                int count = occurrence.get(n);
                occurrence.put(n, count+1);
            }
            else
            {
                occurrence.put(n, 1);
            }
        }
        for(int n: values)
        {
            if(occurrence.containsKey(n))
            {
                int count = occurrence.get(n);
                if(count ==0)
                {
                    return false;
                }
                else
                {
                    occurrence.put(n, count-1);
                }


            }
            else
            {
                return false;
            }
        }
        return true;
    }

    public List<Integer> setMinus(List<Integer> nums, List<Integer> values)
    {
        List<Integer> result = new ArrayList<Integer>();
        HashMap<Integer, Integer> occurrence = new HashMap<Integer, Integer>();
        for(int n: nums)
        {
            if(occurrence.containsKey(n))
            {
               int count = occurrence.get(n);
               occurrence.put(n, count+1);
            }
            else
            {
                occurrence.put(n, 1);
            }
        }
        for(int n: values)
        {
            if(occurrence.containsKey(n))
            {
                int count = occurrence.get(n);
                occurrence.put(n, count-1);
            }
        }
        for(Integer key: occurrence.keySet())
        {
            int count = occurrence.get(key);
            for(int i=0;i<count;i++)
            {
                result.add(key);
            }
        }
        return result;
    }

    public List<List<Integer>> getAllCombinations(int[] nums,
                                                            int index,
                                                            int sum,
                                                  List<Integer> current,
                                                  List<List<Integer>> result
                                                            )
    {
        if(sum ==0)
        {
            List<Integer> candidate =new ArrayList<Integer>(current);
//            if(!result.contains(candidate))
//            {
                result.add(candidate);
//            }

            return result;
        }
        else
        {


            for(int i=index;i<nums.length;i++)
            {
                if(nums[i]<=sum)
                {

                    current.add(nums[i]);
                    result = getAllCombinations(nums, i+1, sum - nums[i], current, result);
                    current.remove(current.size()-1);

                }
            }

            return result;

        }
    }



}
