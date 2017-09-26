package algorithm.test;

import algorithm.TreeNode;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

/**
 * Created by rajin on 9/23/2017.
 */
public class TreeNodeTest {
    @Test
    public void getPrintString() throws Exception {

        String[] in = new String[]{
                "[]",
                "[1]",
                "[1,2]",
                "[1,null,3]",
                "[1,2,3]",
                "[5,4,8,11,null,13,4,7,2,null,null,null,1]",
                "[123,2]"
        };

        String []out =new String[]{
                null,
                "1\n",
                " 1\n |\n2-\n",
                "1 \n| \n-3\n",
                " 1 \n | \n2-3\n",
                "    5      \n    |      \n   4----8  \n   |    |  \n11-- 13--4 \n |       | \n7-2      -1\n",
                "123\n | \n2- \n",

        } ;
        for(int i=0;i<in.length;i++)
        {
            TreeNode node = TreeNode.getTreeNode(in[i]);
            if(node == null)
            {
                assertNull("Tree should be null", out[i]);
            }
            else
            {
                assertEquals("Node Print does not match:"+in[i]+"=>"+node+"\n",out[i],node.getPrintString());
            }
        }

    }


}