package algorithm;

public class TreeTilt {
	public static void main(String[] args){
		TreeNode nd1 = new TreeNode(1);
		TreeNode nd2 = new TreeNode(2);
		TreeNode nd3 = new TreeNode(3);
		
		nd1.left = nd2;
		nd1.right = nd3;
		
		TreeTilt t = new TreeTilt();
		int tilt = t.findTilt(nd1);
		System.out.println(nd1+"\n=>\n"+tilt);
	}
	public int findTilt(TreeNode root) {
		
		if(root ==null){
			return 0;
		}
		else
		{
			int left = findTilt(root.left);
			int current = findTiltNode(root);
			int right = findTilt(root.right);
			
			int tilt = left+current+right;
			return tilt;
		}
    }
	
	public int findTiltNode(TreeNode node){
		int left = treeSum(node.left);
		int right = treeSum(node.right);
		int tilt = Math.abs(left-right);
        return tilt;
	}
	
	public int treeSum(TreeNode node){
		if(node == null){
			return 0;
		}
		else
		{
			int left = treeSum(node.left);
			int right = treeSum(node.right);
			int sum =left+right+node.val; 
			return sum;
		}
	}
}
