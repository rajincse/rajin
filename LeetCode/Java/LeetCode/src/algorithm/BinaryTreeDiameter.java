package algorithm;

public class BinaryTreeDiameter {
	
	public static void main(String[] args)
	{
		TreeNode nd1 = new TreeNode(1);
		TreeNode nd2 = new TreeNode(2);
		TreeNode nd3 = new TreeNode(3);
		TreeNode nd4 = new TreeNode(4);
		TreeNode nd5 = new TreeNode(5);
//		TreeNode nd6 = new TreeNode(5);
		
		nd1.left = nd2;
		nd1.right = nd3;
		
		
		nd2.left = nd4;
		nd2.right = nd5;
		
//		nd4.left = nd5;
//		nd3.left = nd6;
		
		BinaryTreeDiameter bd = new BinaryTreeDiameter();
		
		int diameter = new BinaryTreeDiameter().diameterOfBinaryTree(nd1);
		System.out.println(nd1+"\n=>"+diameter);
		
//		int depth = bd.maxDepth(nd1);
//		System.out.println(nd1+"\n=>"+depth);
	}
	
	public int maxDepth(TreeNode root)
	{
		int maxDepth =-1;
		
		if(root != null)
		{
			
			if(root.left == null && root.right == null)
			{
				maxDepth = 0;
			}
			else if ( root.left != null && root.right == null)
			{
				maxDepth = maxDepth(root.left)+1;
			}
			else if ( root.left == null && root.right != null)
			{
				maxDepth = maxDepth(root.right)+1;
			}
			else
			{
				int maxDepthLeft = maxDepth(root.left);
				int maxDepthRight =maxDepth(root.right) ;
			
				maxDepth = Math.max(maxDepthLeft, maxDepthRight)+1;
			}
			        	
        	
        	
		}
		
		return maxDepth;
	}
	public int diameterOfBinaryTree(TreeNode root) {
        if( root == null)
        {
        	return 0;
        }
        else {
        	int diameter = 0;
        	if(root.left == null && root.right == null)
			{
        		diameter = 0;
			}
			else if ( root.left != null && root.right == null)
			{
				diameter = Math.max( maxDepth(root.left)+1, diameterOfBinaryTree(root.left));
			}
			else if ( root.left == null && root.right != null)
			{
				diameter = Math.max(maxDepth(root.right)+1, diameterOfBinaryTree(root.right));
			}
			else
			{
				int maxDepthLeft = maxDepth(root.left);
				int maxDepthRight =maxDepth(root.right) ;
			
				
				diameter =maxDepthLeft+maxDepthRight+2;
				diameter = Math.max(diameterOfBinaryTree(root.left), diameter);
				diameter = Math.max(diameterOfBinaryTree(root.right), diameter);
			}
        	
        	return diameter;
        }
		
    }
}
