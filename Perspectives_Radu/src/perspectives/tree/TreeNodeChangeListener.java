package perspectives.tree;

public interface TreeNodeChangeListener {
	
	public void childAdded(TreeNode parentNode, TreeNode childNode);
	public void childRemoved(TreeNode parentNode, TreeNode childNode);

}
