package algorithm;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by rajin on 12/6/2017.
 * 146. LRU Cache
 */
class LRUCache {

    private int maxCacheSize;
    private HashMap<Integer, CacheNode> map = new HashMap<Integer, CacheNode>();
    private CacheNode listHead = null;
    private CacheNode listTail = null;

    public LRUCache(int capacity) {
        this.maxCacheSize = capacity;
    }

    public int get(int key) {
        CacheNode node = map.get(key);
        if(node ==null) return -1;
        if(node != listHead)
        {
            removeFromList(node);
            insertAtFront(node);
        }
        return node.val;
    }
    public void put(int key, int value) {
        removeKey(key);
        if(map.size()>= maxCacheSize && listTail != null)
        {
            removeKey(listTail.key);
        }
        CacheNode node = new CacheNode(key, value);
        insertAtFront(node);
        map.put(key, node);
    }
    private void removeFromList(CacheNode node)
    {
        if(node ==null) return ;
        if(node.prev != null) node.prev.next = node.next;
        if(node.next != null) node.next.prev = node.prev;
        if(node == listHead) {
            listHead = node.next;

        }
        if(node == listTail) {
            listTail = node.prev;

        }
        if(listHead != null) listHead.prev = null;
        if(listTail != null) listTail.next = null;
    }
    private void insertAtFront(CacheNode node)
    {
        if(listHead ==null)
        {
            listHead = node;
            listTail = node;
            listHead.prev = null;
            listTail.next = null;
        }
        else
        {
            listHead.prev = node;
            node.next = listHead;
            listHead = node;
            listHead.prev = null;
        }
    }
    private void removeKey(int key)
    {
        CacheNode node = map.get(key);
        removeFromList(node);
        map.remove(key);
    }
    class CacheNode{
        int key;
        int val;
        CacheNode prev;
        CacheNode next;
        public CacheNode(int key, int val){
            this.key = key;
            this.val = val;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Stack<CacheNode> stack = new Stack<CacheNode>();
            CacheNode node = this.prev;
            while(node != null)
            {
                stack.add(node);
                node = node.prev;
            }

            while(!stack.isEmpty())
            {
                node = stack.pop();
                sb.append("{"+node.key+" , "+node.val+"}->");
            }
            sb.append("<<"+this.key+" , "+this.val+">>->");
            node = this.next;
            while(node != null)
            {
                sb.append("{"+node.key+" , "+node.val+"}->");
                node = node.next;
            }

            return sb.toString();
        }
    }

}
public class LRUCacheTest {
    public static  void main(String[] args)
    {

        LRUCache cache = new LRUCache( 1 /* capacity */ );

        cache.put(2, 1);
        System.out.println(cache.get(2));       // returns 1
        cache.put(3, 2);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns -1 (not found)

    }

}
