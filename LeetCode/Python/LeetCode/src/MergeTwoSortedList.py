# Definition for singly-linked list.
class ListNodeController(object):
    
    def __init__(self, headNode=None):
        if headNode != None:            
            self.head = headNode;
            
            node = self.head;
            while node.next != None:
                node = node.next;
            self.tail =node;
        else:
            self.head = None;
            self.tail = None;
        
    def addElement(self, elemVal):
        if self.tail == None:
            self.head = ListNode(elemVal);
            self.tail = self.head;
        else:
            newNode = ListNode(elemVal);
            self.tail.next = newNode;
            self.tail = newNode;
    def addAll(self, list):
        for val in list:
            self.addElement(val);
    def toString(self):
        s = '[';
        node = self.head;
        while node != None:
            s+= str(node.val)+'->';
            node = node.next;
        s +=']';
        
        return s;
    def getHead(self):
        return self.head;
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
    
        node1 = l1;
        node2 = l2;
        
        resultNode = None;
        resultNodeTail =None;
        while node1!= None or node2!= None:
            if node1!= None and node2!= None:
                if node1.val < node2.val:
                    if resultNode == None:
                        resultNode = node1;
                        resultNodeTail = resultNode;
                    else:
                        resultNodeTail.next = node1;
                        resultNodeTail = node1;
                    node1 = node1.next;    
                else:
                    if resultNode == None:
                        resultNode = node2;
                        resultNodeTail = resultNode;
                    else:
                        resultNodeTail.next = node2;
                        resultNodeTail = node2;
                    node2 = node2.next; 
            elif node1 != None and node2 == None:
                if resultNode == None:
                    resultNode = node1;
                    resultNodeTail = resultNode;
                else:
                    resultNodeTail.next = node1;
                    resultNodeTail = node1;
                node1 = node1.next;
            elif node1 == None and node2 != None:
                if resultNode == None:
                    resultNode = node2;
                    resultNodeTail = resultNode;
                else:
                    resultNodeTail.next = node2;
                    resultNodeTail = node2;
                node2 = node2.next;
            
        return resultNode;
            
                
list1 = [];
list2 = [0];
list1Controller = ListNodeController();
list1Controller.addAll(list1);

list2Controller = ListNodeController();
list2Controller.addAll(list2);

print 'list1 : %s\n list2 : %s'% (list1Controller.toString(), list2Controller.toString());
result = Solution().mergeTwoLists(list1Controller.getHead(), list2Controller.getHead());
resultController = ListNodeController(result);
print 'result :%s'%(resultController.toString());
        