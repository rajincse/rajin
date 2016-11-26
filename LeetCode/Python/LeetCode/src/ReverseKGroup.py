# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

from Utility import ListNodeController; 
class Solution(object):
    def reverseList(self, head):
        node = head;
        result = head;
        previous = None;
        while node != None:
            nextNode = node.next;
            if nextNode != None:
                nextNextNode = nextNode.next;
                nextNode.next = node;
                node.next = previous;
            
                previous = nextNode;
                node = nextNextNode;
                result = nextNode;
            else:
                node.next = previous;
                result = node;
                break;
                
        return result;
            
    def reverseKGroup(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        
        node = head;
        result = head;
        previousListTail = None;
        while node != None:
            currentListHead = node;
            previous = None;
            i = 0;
            while node != None and i< k:
                previous = node;
                node = node.next;                
                i+= 1;
            if i == k:
                previous.next = None;
                reverseList = self.reverseList(currentListHead);
                if currentListHead == head:
                    result = reverseList;
                currentListHead.next = node;
                
                if previousListTail != None:
                    previousListTail.next = reverseList;
                previousListTail = currentListHead;
            else:
                break;
        
        return result; 
                
#list = [1,2,3,4,5,6,7,8,9];
list =[1,2,3];
k = 1;
controller = ListNodeController();
controller.addAll(list);
listString = controller.toString();


result = Solution().reverseKGroup(controller.getHead(), k);
resultController = ListNodeController(result);

print '%s = %s' % (listString , resultController.toString());