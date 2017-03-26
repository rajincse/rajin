package algorithm;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeBoundary {
	public static void main(String[] args){

		BinaryTreeBoundary bb = new BinaryTreeBoundary();
//		TreeNode nd1 = new TreeNode(1);
//		TreeNode nd2 = new TreeNode(2);
//		TreeNode nd3 = new TreeNode(3);
//		TreeNode nd4 = new TreeNode(4);
//		TreeNode nd5 = new TreeNode(5);
//		TreeNode nd6 = new TreeNode(6);
//		TreeNode nd7 = new TreeNode(7);
//		TreeNode nd8 = new TreeNode(8);
//		TreeNode nd9 = new TreeNode(9);
//		TreeNode nd10 = new TreeNode(10);
//		
//		nd1.left = nd2;
//		nd1.right = nd3;
//		
//		
//		nd2.left = nd4;
//		nd2.right = nd5;
//		
//		
//		nd5.left = nd7;
//		nd5.right = nd8;
//		
//		nd3.left = nd6;
//		
//		nd6.left = nd9;
//		nd6.right = nd10;
//		
//		
//		List<Integer> result = bb.boundaryOfBinaryTree(nd1);
//		
//		
//		System.out.println(" "+nd1+"=>\n"+result);
		
		TreeNode nd1 = new TreeNode(1);
		TreeNode nd2 = new TreeNode(2);
		TreeNode nd3 = new TreeNode(3);
		TreeNode nd4 = new TreeNode(4);
		
		nd1.right = nd2;
		
		nd2.left = nd3;
		nd2.right = nd4;
		List<Integer> result = bb.boundaryOfBinaryTree(nd1);
		
		System.out.println(" "+nd1+"=>\n"+result);
	}
	
	public String listToString(List<TreeNode> list){
		String msg ="[";
		for(int i=0;i<list.size();i++){
			msg+=list.get(i).val+", ";
		}
		msg+="]";
		return msg;
	}
	
	public List<TreeNode> getLeaves(TreeNode node, List<TreeNode> leavesContainer){
		if(node.left == null && node.right == null){
			leavesContainer.add(node);
		}
		else{
			if(node.left != null){
				leavesContainer = getLeaves(node.left, leavesContainer);
			}
			
			if(node.right != null){
				leavesContainer = getLeaves(node.right, leavesContainer);
			}
			
		}
		
		
		return leavesContainer;
	}
	
	public List<TreeNode> getLeftBoundary(TreeNode node, List<TreeNode> leftBoundary){
		leftBoundary.add(node);
		if(node.left != null){
			leftBoundary = getLeftBoundary(node.left, leftBoundary);
		}
		else if(node.right != null){
			leftBoundary = getLeftBoundary(node.right, leftBoundary);
		}
		
		
		return leftBoundary;
	}
	public List<TreeNode> getRightBoundary(TreeNode node, List<TreeNode> rightBoundary){
		rightBoundary.add(node);
		if(node.right != null){
			rightBoundary = getRightBoundary(node.right, rightBoundary);
		}
		else if(node.left != null){
			rightBoundary = getRightBoundary(node.left, rightBoundary);
		}
		
		
		return rightBoundary;
	}
	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		
        List<Integer>  result = new ArrayList<>();
        
        
        if(root != null){
        	List<TreeNode>  boundary = new ArrayList<>();
        	List<TreeNode> leaves = new ArrayList<>();
            List<TreeNode> leftBoundary = new ArrayList<>();
            List<TreeNode> rightBoundary = new ArrayList<>();
        	leftBoundary = getLeftBoundary(root, leftBoundary);
            leaves = getLeaves(root, leaves);
            rightBoundary = getRightBoundary(root, rightBoundary);
            
            System.out.println("Left:"+listToString(leftBoundary));
            System.out.println("leaves:"+listToString(leaves));
            System.out.println("right:"+listToString(rightBoundary));
            
           
            if(root.left == null){
            	boundary.add(root);
            }
            else{
            	for(int i=0;i<leftBoundary.size();i++){
                	if(!boundary.contains(leftBoundary.get(i))){
                		boundary.add(leftBoundary.get(i));
                	}
                }
            }
           
            
            
            for(int i=0;i<leaves.size();i++){
            	if(!boundary.contains(leaves.get(i))){
            		boundary.add(leaves.get(i));
            	}
            }
            
            if(root.right != null){
            	for(int i=rightBoundary.size()-1;i>=0;i--){
                	if(!boundary.contains(rightBoundary.get(i))){
                		boundary.add(rightBoundary.get(i));
                	}
                }
            }
            
            for(int i=0;i<boundary.size();i++){
            	result.add(boundary.get(i).val);
            }
        }
        
		return result;
    }
}
