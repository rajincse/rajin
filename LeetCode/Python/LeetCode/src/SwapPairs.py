# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None
from Utility import ListNodeController



class Solution(object):
    def swapPairs(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        
        node = head;
        result = node;
        previous = None;
        while node != None:
            nextNode = node.next;
            
            if nextNode != None:
                node.next = nextNode.next;
                nextNode.next = node;
                if node == head:
                    result = nextNode;
                if previous != None:
                    previous.next = nextNode;
                
            previous = node;
            node = node.next;
        
        return result;

list = [1,2,3,4,5];
controller = ListNodeController();
controller.addAll(list);
listString = controller.toString();
result = Solution().swapPairs(controller.getHead());
resultController = ListNodeController(result);

print '%s = %s' % (listString , resultController.toString());