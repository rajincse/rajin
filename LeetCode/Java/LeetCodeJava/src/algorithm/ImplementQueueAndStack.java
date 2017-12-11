package algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by rajin on 12/11/2017.
 * 232. Implement Queue using Stacks
 * 225. Implement Stack using Queues
 */
public class ImplementQueueAndStack {
    class MyQueue {

        Stack<Integer> stack1;
        Stack<Integer> stack2;
        /** Initialize your data structure here. */
        public MyQueue() {
            stack1 = new Stack<Integer>();
            stack2 = new Stack<Integer>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(stack2.isEmpty())
            {
                while(!stack1.isEmpty())
                {
                    stack2.push(stack1.pop());
                }
            }

            return stack2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if(stack2.isEmpty())
            {
                while(!stack1.isEmpty())
                {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            if(stack2.isEmpty())
            {
                while(!stack1.isEmpty())
                {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.isEmpty();
        }
    }
    class MyStack {

        Queue<Integer> queue1;
        Queue<Integer> queue2;
        /** Initialize your data structure here. */
        public MyStack() {
            queue1= new LinkedList<Integer>();
            queue2= new LinkedList<Integer>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue1.add(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            int x= queue1.poll();
            while(!queue1.isEmpty())
            {
                queue2.add(x);
                x = queue1.poll();
            }
            //swap
            Queue<Integer> q = queue1;
            queue1 = queue2;
            queue2 = q;
            return x;
        }

        /** Get the top element. */
        public int top() {
            int x= queue1.poll();
            while(!queue1.isEmpty())
            {
                queue2.add(x);
                x = queue1.poll();
            }
            queue2.add(x);
            //swap
            Queue<Integer> q = queue1;
            queue1 = queue2;
            queue2 = q;
            return x;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }

}
