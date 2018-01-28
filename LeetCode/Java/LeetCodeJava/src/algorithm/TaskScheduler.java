package algorithm;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by rajin on 12/21/2017.
 * 621. Task Scheduler
 */
public class TaskScheduler {
    public static void main(String[] args)
    {
        char[] task = new char[]{'A','A','A','A','A','A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n =2;
        System.out.println(new TaskScheduler().leastInterval(task, n));
    }
    class TaskElement implements Comparable<TaskElement>
    {
        char taskChar;
        int waitTime;
        int count;
        public TaskElement(char ch)
        {
            this.taskChar = ch;
            this.waitTime =0;
            this.count=0;
        }

        @Override
        public int compareTo(TaskElement o) {
            return this.waitTime==o.waitTime?o.count - this.count: this.waitTime -o.waitTime;
        }

        @Override
        public String toString() {
            return "{" +
                    "taskChar=" + taskChar +
                    ", waitTime=" + waitTime +
                    ", count=" + count +
                    '}';
        }
    }

    public int leastInterval(char[] tasks, int n) {
        int time =0;
        HashMap<Character, TaskElement> instances = new HashMap<Character, TaskElement>();
        PriorityQueue<TaskElement> queue = new PriorityQueue<TaskElement>();
        for(char ch: tasks)
        {
            if(!instances.containsKey(ch))
            {
                instances.put(ch, new TaskElement(ch));
            }
            TaskElement element = instances.get(ch);
            element.count++;
            instances.put(ch, element);
        }
        for(Character key: instances.keySet())
        {
            queue.offer(instances.get(key));
        }
        while(!instances.isEmpty())
        {
            TaskElement elem = queue.poll();
            if(elem.waitTime ==0)
            {
                elem.waitTime = n+1;
                elem.count--;
                if(elem.count ==0)
                {
                    instances.remove(elem.taskChar);
                }
                else
                {
                    instances.put(elem.taskChar, elem);
                }
            }
            time++;
            queue.clear();
            for(Character key: instances.keySet())
            {
                TaskElement e = instances.get(key);
                e.waitTime = e.waitTime>0? e.waitTime-1:0;
                instances.put(key, e);
                queue.offer(e);
            }
        }

        return time;
    }
}
