package algorithm;

/**
 * Created by rajin on 9/14/2017.
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 */
public class ConstructBTFromInPre {
    public static void main(String[] args){
        int[] pre= new int[]{1,2,4,5,3,6};
        int[] in = new int[]{4,2,5,1,6,3};

        TreeNode node = new ConstructBTFromInPre().buildTree(pre,in);
        System.out.println(node.getPrintString());
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if(preorder.length ==0 || preorder.length != inorder.length){
            return null;
        }

        return getNode(preorder,inorder,0, preorder.length, 0, inorder.length);
    }

    public TreeNode getNode(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd){


        if(preStart>= preEnd || inStart>= inEnd)
        {
            return null; //invalid
        }
        else if(preStart == preEnd-1 && preorder[preStart] == inorder[inStart])
        {
            return new TreeNode(preorder[preStart]);
        }
        else
        {
            TreeNode root = new TreeNode(preorder[preStart]);
            int rootIndex = -1;
            for(int i=inStart;i<inEnd;i++)
            {
                if(inorder[i] == preorder[preStart])
                {
                    rootIndex = i;
                    break;
                }
            }
            int leftLength = rootIndex-inStart;
            root.left = getNode(preorder, inorder, preStart+1, preStart+1+leftLength, inStart,rootIndex);
            root.right = getNode(preorder, inorder, preStart+1+leftLength, preEnd,rootIndex+1, inEnd);
            return root;
        }

    }

}
