package algorithm;

import java.util.ArrayList;

public class MinAbsDiffBST {

	
	  class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	 }
	public static void main(String []args)
	{
		MinAbsDiffBST df = new MinAbsDiffBST();
		TreeNode n1 = df.new TreeNode(2);
		TreeNode n2 = df.new TreeNode(6);
		TreeNode n3 = df.new TreeNode(4);
		
		n3.right = n2;
		n3.left = null;
		
		n2.left = n1 ;
		n2.right = null;
		
		n1.left = null;
		n1.right = null;
		
		int min = df.getMinimumDifference(n3);
		System.out.println("Min diff:"+min);
	}
	public ArrayList<Integer>   addItem(TreeNode node, ArrayList<Integer> valueList)
	{
		if(node == null) return valueList;
		valueList.add(node.val);
		valueList = addItem(node.left, valueList);
		valueList = addItem(node.right, valueList);
		
		return valueList;
				
	}
	public int getMinimumDifference(TreeNode root) {
        ArrayList<Integer> valueList = new ArrayList<Integer>();
        valueList = addItem(root, valueList);
        int minDiff = Integer.MAX_VALUE;
        for(int i=0;i<valueList.size();i++)
        {
        	for(int j=i+1; j< valueList.size();j++)
        	{
        		minDiff = Math.min(minDiff, Math.abs(valueList.get(i) - valueList.get(j)));
        	}
        }
		return minDiff;
    }
}
