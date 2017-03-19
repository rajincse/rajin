package algorithm;

public class BSTtoGreaterTree {

	public static void main(String[] args)
	{
		TreeNode nd1 = new TreeNode(1);
		TreeNode nd0 = new TreeNode(0);
		TreeNode nd4 = new TreeNode(4);
		TreeNode ndN2 = new TreeNode(-2);
		TreeNode nd3 = new TreeNode(3);
		
		nd1.left = nd0;
		nd1.right = nd4;
		
		nd0.left = ndN2;
		
		nd4.left = nd3;
		
		System.out.println(nd1+"=>");
		
		BSTtoGreaterTree bg = new BSTtoGreaterTree();
		TreeNode converted =  bg.convertBST(nd1);
		System.out.println(converted);
	}
	
	public int process(TreeNode root, int sum)
	{
		if(root.right == null)
		{
			root.val += sum;
			int value = root.val;
			if(root.left != null)
			{
				value = process(root.left, root.val);
			}
			return value;
		}
		else
		{
			int value = process(root.right, sum);
			root.val += value;
			value = root.val;
			if(root.left != null)
			{
				value = process(root.left, root.val);
			}
			return value;
		}
	}
	public TreeNode convertBST(TreeNode root) {
		if(root != null)
		{
			process(root, 0);
		}
		
		return root;
    }
}
