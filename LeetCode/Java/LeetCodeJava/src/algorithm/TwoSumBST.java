package algorithm;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by rajin on 8/7/2017.
 */
public class TwoSumBST {
    public static void main(String[] args)
    {
//        String data ="[5,3,6,2,4,null,7]";
        String data ="[1,1]";
        TreeNode node = TreeNode.getTreeNode(data);
        int k = 2;
        System.out.println("Result:"+new TwoSumBST().findTarget(node, k));
    }

    public boolean findTarget(TreeNode root, int k) {

        HashMap<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
        populateFrequencyMap(root, frequencyMap);

        for(Integer elem: frequencyMap.keySet())
        {
            Integer currentFrequency = frequencyMap.get(elem);
            currentFrequency--;
            frequencyMap.put(elem, currentFrequency);
            Integer otherVal = new Integer( k - elem.intValue());

            if(frequencyMap.containsKey(otherVal) && frequencyMap.get(otherVal) > 0)
            {
                return true;
            }
            currentFrequency++;
            frequencyMap.put(elem, currentFrequency);
        }

        return false;
    }

    public void populateFrequencyMap(TreeNode node, HashMap<Integer, Integer> frequencyMap)
    {
        if(node != null) {
            if(frequencyMap.containsKey(node.val))
            {
                int freuqency = frequencyMap.get(node.val);
                freuqency++;
                frequencyMap.put(node.val, freuqency);

            }
            else
            {
                frequencyMap.put(node.val,1);
            }

            populateFrequencyMap(node.left, frequencyMap);
            populateFrequencyMap(node.right, frequencyMap);

        }
    }
}
