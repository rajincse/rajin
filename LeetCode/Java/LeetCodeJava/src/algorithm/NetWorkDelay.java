package algorithm;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Created by rajin on 12/9/2017.
 */
public class NetWorkDelay {
    public static void main(String[] args)
    {
        int[][] times = new int[][]
                {
                        {1,2,1},
                        {2,1,3}
                };
        int N = 2;
        int K = 2;
        System.out.println(new NetWorkDelay().networkDelayTime(times, N, K));
    }
    class Node implements Comparable<Node>
    {
        int index;
        int val;
        public Node(int index, int val)
        {
            this.index = index;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }

        @Override
        public int hashCode() {
            int result = index;
            result = 31 * result + val;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node)
            {
                Node n = (Node) obj;
                return n.index == this.index;
            }
            else
            {
                return super.equals(obj);
            }

        }
    }
    public int networkDelayTime(int[][] times, int N, int K) {

        int[] reachTime = new int[N];
        for(int i=0;i<reachTime.length;i++)
        {
            reachTime[i] =-1;
        }
        int[][] graph = new int[N][N];
        for(int i=0;i<graph.length;i++)
        {
            for(int j=0;j<graph[i].length;j++)
            {
                graph[i][j] = -1;
            }
        }
        for(int[] time: times)
        {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            graph[u-1][v-1] = w;
        }

        reachTime[K-1] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<Node>();

        for(int i=0;i<graph[K-1].length;i++)
        {
            if(graph[K-1][i] >= 0)
            {
                reachTime[i] = Math.max(reachTime[i], graph[K-1][i]);

            }
            if(i != K-1)
            {
                Node n = new Node(i, reachTime[i]);

                queue.add(n);

            }
        }
        while(!queue.isEmpty())
        {
            Node n = queue.poll();
            int u = n.index;
            for(int v=0;v<graph[u].length;v++)
            {
                if(graph[u][v] >= 0)
                {
                    if(reachTime[v] >= 0)
                    {
                        reachTime[v] = Math.min(reachTime[v], reachTime[u]+ graph[u][v]);

                    }
                    else
                    {
                        reachTime[v] = reachTime[u]+ graph[u][v];
                    }
                    Node node = new Node(v, reachTime[v]);
                    if(queue.contains(node))
                    {
                        queue.remove(node);
                        queue.add(node);
                    }

                }
            }
        }


        int maxTime = Integer.MIN_VALUE;
        for(int i=0;i<reachTime.length;i++)
        {
            if(reachTime[i]<0)
            {
                return -1;
            }
            else
            {
                maxTime = Math.max(reachTime[i], maxTime);
            }
        }

        return maxTime;
    }
}
