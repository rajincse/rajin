package algorithm;

/**
 * Created by rajin on 9/14/2017.
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 */
public class ConstructBTFromInPost {
    public static void main(String[] args){
        int[] post= new int[]{4,5,2,6,3,1};
        int[] in = new int[]{4,2,5,1,6,3};

        TreeNode node = new ConstructBTFromInPost().buildTree(in, post);
        System.out.println(node.getPrintString());
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(postorder.length ==0 || postorder.length != inorder.length){
            return null;
        }

        return getNode(postorder,inorder,0, postorder.length, 0, inorder.length);
    }
    public TreeNode getNode(int[] postorder, int[] inorder, int postStart, int postEnd, int inStart, int inEnd){


        if(postStart>= postEnd || inStart>= inEnd)
        {
            return null; //invalid
        }
        else if(postStart == postEnd-1 && postorder[postEnd-1] == inorder[inStart])
        {
            return new TreeNode(postorder[postEnd-1]);
        }
        else
        {
            TreeNode root = new TreeNode(postorder[postEnd-1]);
            int rootIndex = -1;
            for(int i=inStart;i<inEnd;i++)
            {
                if(inorder[i] == postorder[postEnd-1])
                {
                    rootIndex = i;
                    break;
                }
            }
            int leftLength = rootIndex-inStart;
            root.left = getNode(postorder, inorder, postStart, postStart+leftLength, inStart,rootIndex);
            root.right = getNode(postorder, inorder, postStart+leftLength, postEnd-1,rootIndex+1, inEnd);
            return root;
        }

    }
}
