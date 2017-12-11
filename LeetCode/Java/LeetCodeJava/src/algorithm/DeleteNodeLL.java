package algorithm;

/**
 * Created by rajin on 12/10/2017.
 * 237. Delete Node in a Linked List
 */
public class DeleteNodeLL {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
