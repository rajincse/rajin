package algorithm;

import java.util.HashMap;

/**
 * Created by rajin on 11/6/2017.
 */
class Point {
      int x;
      int y;
      Point() { x = 0; y = 0; }
      Point(int a, int b) { x = a; y = b; }
  }
public class MaxPointsOnALine {
    public static void main(String[] args){
        Point[] points = new Point[]{ //[[0,0],[94911151,94911150],[94911152,94911151]]
                new Point(0,0),
                new Point(94911151,94911150),
                new Point(94911152,94911151),
        };
        System.out.println(new MaxPointsOnALine().maxPoints(points));
    }
    public  int gcd(int a, int b){
        if(b==0) return a;
        else
        {
            return gcd(b,a%b);
        }
    }
    public int maxPoints(Point[] points) {
        if(points ==null || points.length ==0){
            return 0;
        }

        if(points.length <=2){
            return points.length;
        }

        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<Integer, HashMap<Integer, Integer>>();
        int maxPoints =0;
        for(int i=0;i<points.length;i++){
            int samePoint =0;
            int sameX =1;

            map.clear();
            for(int j=0;j<points.length;j++)
            {
                if(i==j) continue;
                if(points[i].x == points[j].x && points[i].y == points[j].y)
                {
                    samePoint++;
                }
                else if(points[i].x == points[j].x ){
                    sameX++;
                }
                else
                {
                    int delX = points[i].x - points[j].x;
                    int delY = points[i].y - points[j].y;
                    int gcd = gcd(delX, delY);
                    delX/=gcd;
                    delY/=gcd;
                    if(map.containsKey(delX) && map.get(delX).containsKey(delY)){

                        int count =map.get(delX).get(delY);
                        map.get(delX).put(delY, count+1);
                    }
                    else if(map.containsKey(delX) && !map.get(delX).containsKey(delY)){
                        map.get(delX).put(delY, 2);
                    }
                    else
                    {
                        map.put(delX, new HashMap<Integer, Integer>());
                        map.get(delX).put(delY, 2);
                    }

                }
            }
            int localMax = sameX;
            for(Integer delX: map.keySet())
            {
                for(Integer delY: map.get(delX).keySet())
                {
                    localMax = Math.max(localMax, map.get(delX).get(delY));
                }
            }

            localMax += samePoint;

            maxPoints = Math.max(maxPoints, localMax);
        }
        return maxPoints;
    }
}
