package algorithm;

public class TreeNode {
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