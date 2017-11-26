package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rajin on 11/25/2017.
 */
public class AsteroidCollision {
    public static void main(String[] args)
    {
        int[] a= new int[]{-2,-2,-2,1};
        int[] ans = new AsteroidCollision().asteroidCollision(a);
        System.out.println(Arrays.toString(ans));
    }
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> aliveAsteroids = new ArrayList<Integer>();
        for(int a: asteroids)
        {
            aliveAsteroids.add(a);
        }
        int size = aliveAsteroids.size();
        aliveAsteroids = asteroidFight(aliveAsteroids);
        while(aliveAsteroids.size() < size)
        {
            size = aliveAsteroids.size();
            aliveAsteroids = asteroidFight(aliveAsteroids);
        }
        int[] result = new int[aliveAsteroids.size() ];
        for(int i=0;i<aliveAsteroids.size();i++)
        {
            result[i] = aliveAsteroids.get(i);
        }

        return result;
    }

    public List<Integer> asteroidFight(List<Integer> asteroids)
    {
        List<Integer> aliveAsteroids = new ArrayList<Integer>();

        for(int i=0;i<asteroids.size();i++)
        {
            int b = asteroids.get(i);
            if(i==0)
            {
                if(i+1< asteroids.size())
                {
                    int c =asteroids.get(i+1);
                    if(collides(b,c) && Math.abs(b) <= Math.abs(c))
                    {
                        continue;
                    }
                }
            }
            else if(i== asteroids.size()-1)
            {
                if(i-1>=0)
                {
                    int a = asteroids.get(i-1);
                    if(collides(a,b) && Math.abs(b)<= Math.abs(a))
                    {
                        continue;
                    }
                }

            }
            else
            {
                if(i-1>=0 && i+1< asteroids.size())
                {
                    int a = asteroids.get(i-1);
                    int c =asteroids.get(i+1);
                    if(collides(a,b) && Math.abs(b)<= Math.abs(a) || collides(b,c) && Math.abs(b) <= Math.abs(c))
                    {
                        continue;
                    }
                }

            }
            aliveAsteroids.add(b);
        }

        return aliveAsteroids;
    }

    public boolean collides(int left, int right)
    {
        return left>=0 && right<0;
    }
}
