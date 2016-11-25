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
    def __str__(self):
     return self.toString();
 
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
    def __str__(self):
        return ""+ListNodeController(self).toString();

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
    
    def mergeKLists(self, lists):
        """
        :type lists: List[ListNode]
        :rtype: ListNode
        """
#         msg ='';
#         for list in lists:
#             msg+= str(list);
#         print 'sorting list: [%s]' %msg;

        if len(lists) ==0:
            return None;
        elif len(lists)==1:
            return lists[0];
        elif len(lists)==2:
            return self.mergeTwoLists(lists[0], lists[1]);
        
        else:
            dividingIndex = int((len(lists)/2));
#             print 'dividing %d'% dividingIndex;
            list1 = lists[0:dividingIndex];
            list2 = lists[dividingIndex:];
            sorted1 = self.mergeKLists(list1);
            sorted2 = self.mergeKLists(list2);
            
            return self.mergeKLists([sorted1, sorted2]);
        
# lists = [[1,3,5], [2,6, 9], [4,7,8]];
lists=[]
listNodes=[];
for list in lists:
    controller = ListNodeController();
    controller.addAll(list);
    listNodes.append(controller.getHead());
    
 
print 'list = %s' % (lists);
result = Solution().mergeKLists(listNodes);

resultController = ListNodeController(result);
print 'result :%s'%(resultController.toString());