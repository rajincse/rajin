package algorithm;

import java.util.*;

/**
 * Created by rajin on 12/11/2017.
 * 218. The Skyline Problem
 */
public class SkyLine {
    public static void main(String[] args)
    {
        int[][] buildings = new int[][]
                {
//                        {2,9,10},
//                        {3,7,15},
//                        {5,12,12},
//                        {15,20,10},
//                        {19,24,8}
                        {1,2,1},
                        {1,2,2},
                        {1,2,3},
                };
        List<int[]> result = new SkyLine().getSkyline(buildings);
        for(int[] r: result)
        {
            System.out.println(Arrays.toString(r));
        }
    }
    public List<int[]> getSkyline(int[][] buildings) {
        List<BuildingEdge> buildingEdges = new ArrayList<BuildingEdge>();
        for(int[] building: buildings)
        {
            buildingEdges.add(new BuildingEdge(building[0], building[2], true));
            buildingEdges.add(new BuildingEdge(building[1], building[2], false));
        }
        Collections.sort(buildingEdges);

        PriorityQueue<SortedHeight> queue = new PriorityQueue<SortedHeight>();

        List<int[]> result = new ArrayList<int[]>();
        queue.add(new SortedHeight(0));
        int previousHeight =0;
        for(BuildingEdge edge: buildingEdges)
        {
            if(edge.upward)
            {
                queue.add(new SortedHeight(edge.height));
            }
            else
            {
                queue.remove(new SortedHeight(edge.height));
            }
            int currentMaxHeight = queue.peek().height;

            if(currentMaxHeight != previousHeight)
            {
                result.add(new int[]{edge.x, currentMaxHeight});
                previousHeight = currentMaxHeight;
            }
        }

        return result;
    }
    class SortedHeight implements Comparable<SortedHeight>
    {
        int height;
        public SortedHeight(int h)
        {
            this.height = h;
        }

        @Override
        public int compareTo(SortedHeight o) {
            return o.height - this.height;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SortedHeight that = (SortedHeight) o;

            return height == that.height;
        }

        @Override
        public int hashCode() {
            return height;
        }
    }
    class BuildingEdge implements Comparable<BuildingEdge>{
        int x;
        int height;
        boolean upward;
        public BuildingEdge(int x, int height, boolean upward){
            this.x = x;
            this.height = height;
            this.upward = upward;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", height=" + height +
                    ", upward=" + upward +
                    '}';
        }

        @Override
        public int compareTo(BuildingEdge o)
        {
            if(this.x != o.x)
            {
                return this.x- o.x;
            }
            else
            {
                if(this.upward==o.upward)
                {
                    return this.upward? o.height-this.height: this.height-o.height;
                }
                else
                {
                    return this.upward?-1:1;
                }
            }
        }
    }
}
