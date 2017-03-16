package algorithm;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    @Override
	public String toString() {
		// TODO Auto-generated method stub
	
   		 String msg ="{val:"+val;
   		 
   		 String left = "";
   		 if(this.left != null)
   		 {
   			left =this.left.toString();
   		 }
   		 else {
   			left = "null"; 
   		 }
   		 msg+=", left:"+left;
   		 
   		String right = "";
  		 if(this.right != null)
  		 {
  			right =this.right.toString();
  		 }
  		 else {
  			right = "null"; 
  		 }
   		 msg+=", right:"+right+"}";
   		 
   		 return msg;
   		 
   	 
	}
    
}
class State
{
	public TreeNode node;
	public String s;
	public State(TreeNode node, String s)
	{
		this.node = node;
		this.s = s;
	}
	
}
public class BinaryTreeFromString {
	
	public static String print(TreeNode node)
    {
	   	 if( node == null)
	   	 {
	   		 return "";
	   	 }
	   	 else
	   	 {
	   		 String msg ="{val:"+node.val;
	   		 String left = print(node.left);
	   		 msg+=", left:"+left;
	   		 String right = print(node.right);
	   		 msg+=", right:"+right+"}";
	   		 
	   		 return msg;
	   		 
	   	 }
   	 
    }
	public static void main(String[] args)
	{
		String s = "4(-200(3)(1))(6(50)(-1))";
		BinaryTreeFromString tree = new BinaryTreeFromString();
		String ans = print(tree.str2tree(s));
		
		System.out.println(s+"="+ans);
	}
	
	
	public State construct(String s)
	{
		if(s.contains("("))
		{
			int index = Math.min(s.indexOf('('), s.indexOf(')'));
			int val = Integer.parseInt(s.substring(0, index));
			s = s.substring(index);			
			
			TreeNode node = new TreeNode(val);
			
			if(s.charAt(0) == '(')
			{
				State state1 = construct(s.substring(1));
				node.left = state1.node;
				s = state1.s;
				
				if(s.charAt(0) == '(')
				{
					State stat2 = construct(s.substring(1));
					node.right = stat2.node;
					s = stat2.s;
				}
			}
			if(s.charAt(0) ==')')
			{
				s = s.substring(1);
			}
			
			return new State(node, s);
		}
		else if (s.contains(")")){
			int index = s.indexOf(')');
			int val = Integer.parseInt(s.substring(0, index));
			
			TreeNode node = new TreeNode(val);
			s = s.substring(index);
			return new State(node, s);
		}
		else
		{
			int val = Integer.parseInt(s);
			TreeNode node = new TreeNode(val);
			s= "";
			return new State(node, s);
		}
		
	}
	
	public TreeNode str2tree(String s) {
		if(s == null ||  s.isEmpty())
		{
			return null;
		}
		TreeNode rootNode = construct(s).node;
		
		
        return rootNode;
    }
}
