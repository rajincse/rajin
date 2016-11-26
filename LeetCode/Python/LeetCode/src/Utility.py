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