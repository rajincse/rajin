package algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by rajin on 9/29/2017.
 * 116. Populating Next Right Pointers in Each Node
 * 117. Populating Next Right Pointers in Each Node II
 */
class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }

    @Override
    public String toString() {
        return "{" +
                "\"val\":" + val +
                ", \"left\":"+this.left+
                ", \"right\":"+this.right+
                ", \"next\":"+this.next+
                "}";
    }
}
public class PopulateTreeLinkNode {


    public static void main(String[] args)
    {
        TreeLinkNode[] nodes = new TreeLinkNode[8];
        for(int i=0;i<nodes.length;i++)
        {
            nodes[i] = new TreeLinkNode(i+1);
        }

        for(int i=0;i<2;i++)
        {
            nodes[i].left = nodes[2*i+1];
            nodes[i].right = nodes[2*i+2];
        }
        nodes[2].left = null;
        nodes[2].right = nodes[5];

        nodes[3].left = nodes[6];
        nodes[5].right = nodes[7];
        System.out.println(nodes[0]);

        new PopulateTreeLinkNode().connect(nodes[0]);
        System.out.println(nodes[0]);
    }


    public void connect(TreeLinkNode root) {
        if(root ==null)
        {
            return;
        }

        TreeLinkNode currentQueueHead = root;

        while (currentQueueHead != null)
        {
            TreeLinkNode iterator = currentQueueHead;
            TreeLinkNode newQueue = null;
            TreeLinkNode newQueueIterator = null;
            while (iterator != null)
            {
                if(iterator.left != null)
                {
                    if(newQueue == null)
                    {
                        newQueue = iterator.left;
                        newQueueIterator = newQueue;
                    }
                    else
                    {
                        newQueueIterator.next = iterator.left;
                        newQueueIterator = newQueueIterator.next;
                    }
                }
                if(iterator.right != null)
                {
                    if(newQueue == null)
                    {
                        newQueue = iterator.right;
                        newQueueIterator = newQueue;
                    }
                    else
                    {
                        newQueueIterator.next = iterator.right;
                        newQueueIterator = newQueueIterator.next;
                    }
                }
                iterator = iterator.next;
            }
            currentQueueHead = newQueue;

        }

    }






}
