package datastructure;

public class TreePutla {
	public TreeNode root;
	
	public TreePutla(TreeNode node)
	{
		this.root = node;
	}
	
	public void inorderTraverse(TreeNode node)
	{
		if(node.left != null)
		{
			inorderTraverse(node.left);
		}
		System.out.println(node.name);
		if(node.right != null)
		{
			inorderTraverse(node.right);
		}
	}
	public void preorderTraverse(TreeNode node)
	{
		System.out.println(node.name);
		if(node.left != null)
		{
			preorderTraverse(node.left);
		}
		
		if(node.right != null)
		{
			preorderTraverse(node.right);
		}
	}
	public void postTraverse(TreeNode node)
	{
		if(node.left != null)
		{
			postTraverse(node.left);
		}
		
		if(node.right != null)
		{
			postTraverse(node.right);
		}
		System.out.println(node.name);
	}
	
	
	
	public static void main(String[] args)
	{
		TreeNode tania = new TreeNode("Tania");
		TreeNode shume = new TreeNode("Shume");
		
		TreeNode mary = new TreeNode("Mary");
		mary.left = tania;
		mary.right = shume;
		
		TreeNode mim = new TreeNode("Mim");
		mim.left = new TreeNode("Wajna");
		TreeNode arefa = new TreeNode("Arefa");
		arefa.left = mim;
		
		TreeNode kulsum = new TreeNode("Kulsum");
		kulsum.left = arefa;
		kulsum.right = mary;
		
		TreePutla putla = new TreePutla(kulsum);
		
//		putla.inorderTraverse(putla.root);
		System.out.println("---------------");
//		putla.preorderTraverse(putla.root);
		System.out.println("---------------");
		putla.postTraverse(putla.root);
	}
	
}
class TreeNode
{
	public String name;
	public TreeNode left;
	public TreeNode right;
	public TreeNode(String name)
	{
		this.name = name;
		this.left = null;
		this.right = null;
	}
}
