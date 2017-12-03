package algorithm;

/**
 * Created by rajin on 12/3/2017.
 * 297. Serialize and Deserialize Binary Tree
 */
public class SerializeDeSerializeBT {
    public static void main(String[] args)
    {
        SerializeDeSerializeBT obj = new SerializeDeSerializeBT();
        obj.test();
    }

    public void test()
    {
        String data = "[-1,0,1]";
        TreeNode node = TreeNode.getTreeNode(data);
        Codec c = new Codec();
        String encode = c.serialize(node);
        System.out.println(node.getPrintString()+"=>"+encode);


        TreeNode node2 = c.deserialize(encode);
        System.out.println(node2 != null? node2.getPrintString():"null");
    }

    class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root ==null)
            {
                return "null";
            }
            else
            {
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                sb.append("\"val\":");
                sb.append(root.val);
                sb.append(",");
                sb.append("\"left\":");
                sb.append(serialize(root.left));
                sb.append(",");
                sb.append("\"right\":");
                sb.append(serialize(root.right));
                sb.append("}");

                return sb.toString();
            }

        }

        private int index=0;
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            this.index=0;
            if(data.equals("null"))
            {
                return null;
            }
            else
            {
                return getTreeNode(data.toCharArray());
            }

        }
        public TreeNode getTreeNode(char[] charArr)
        {
            // get val
            int start = index+7;
            int val =0;
            char ch = charArr[start];
            boolean isNegative = false;
            if(ch =='-')
            {
                isNegative = true;
                start++;
                ch = charArr[start];
            }
            while(ch != ',')
            {
                val *=10;
                val += ch-'0';
                start++;
                ch = charArr[start];
            }
            val = isNegative? val *-1: val;
            TreeNode node= new TreeNode(val);

            //chech left
            index = start+8;
            ch = charArr[index];
            if(ch =='{')
            {
                node.left = getTreeNode(charArr);
            }
            else
            {
                node.left = null;
                index+=5; // null
            }
            //check right
            index+=8;
            ch = charArr[index];
            if(ch =='{')
            {
                node.right = getTreeNode(charArr);
            }
            else
            {
                node.right = null;
                index+=5;//null
            }

            index++; // end }
            return node;
        }
    }
}
