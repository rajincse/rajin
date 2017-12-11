package algorithm;

import java.util.HashMap;

/**
 * Created by rajin on 12/11/2017.
 * 208. Implement Trie (Prefix Tree)
 */
public class ImplementTrie {
    class Trie {

        /** Initialize your data structure here. */
        class TrieNode
        {
            public static final char ROOT_CHAR='*';
            public static final char TERMINATING_CHAR='*';
            char val;
            HashMap<Character, TrieNode> children;
            public TrieNode(char val)
            {
                this.val = val;
                this.children = new HashMap<Character, TrieNode>();
            }
        }
        private TrieNode root;
        public Trie() {
            this.root = new TrieNode(TrieNode.ROOT_CHAR);
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            insert(this.root, word.toCharArray(), 0);
        }
        private void insert(TrieNode root, char[] charArray, int start)
        {
            if(start==charArray.length)
            {

                root.children.put(TrieNode.TERMINATING_CHAR, new TrieNode(TrieNode.TERMINATING_CHAR));
            }
            else
            {
                char ch = charArray[start];
                if(!root.children.containsKey(charArray[start]))
                {

                    root.children.put(ch, new TrieNode(ch));
                }
                insert(root.children.get(ch), charArray, start+1);
            }

        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return search(this.root, word.toCharArray(), 0);
        }
        private boolean search(TrieNode root, char[] charArray, int start)
        {
            if(start==charArray.length)
            {
                return root.children.containsKey(TrieNode.TERMINATING_CHAR);
            }
            else
            {
                char ch = charArray[start];
                if(root.children.containsKey(ch))
                {
                    return search(root.children.get(ch), charArray, start+1);
                }
                else
                {
                    return false;
                }

            }
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return startsWith(this.root, prefix.toCharArray(), 0);
        }
        private boolean startsWith(TrieNode root, char[] charArray, int start)
        {
            if(start==charArray.length)
            {
                return true;
            }
            else
            {
                char ch = charArray[start];
                if(root.children.containsKey(ch))
                {
                    return startsWith(root.children.get(ch), charArray, start+1);
                }
                else
                {
                    return false;
                }

            }
        }
    }
}
