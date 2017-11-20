package algorithm;

import java.util.HashMap;

/**
 * Created by rajin on 11/17/2017.
 * 138. Copy List with Random Pointer
 */
class RandomListNode {
      int label;
      RandomListNode next, random;
      RandomListNode(int x) { this.label = x; }
  };
public class CopyListRandomPointer {

    public static void main(String[] args){
        RandomListNode nd1 = new RandomListNode(1);
        RandomListNode nd2 = new RandomListNode(2);
        RandomListNode nd3 = new RandomListNode(3);
        RandomListNode nd4 = new RandomListNode(4);

        nd1.next = nd2;
        nd2.next = nd3;
        nd3.next = nd4;
        nd4.next = null;

        nd1.random = nd3;
        nd2.random = nd4;
        nd3.random = null;
        nd4.random = nd1;

        RandomListNode result = new CopyListRandomPointer().copyRandomList(nd1);

    }
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head ==null)
        {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode node = head;
        while (node != null)
        {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }

        node = head;
        while (node != null)
        {
            map.get(node).next = node.next ==null? null: map.get(node.next);
            map.get(node).random = node.random==null? null: map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }


}
