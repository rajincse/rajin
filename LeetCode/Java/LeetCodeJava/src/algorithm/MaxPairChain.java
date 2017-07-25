package algorithm;


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by rajin on 7/22/2017.
 */
class  Pair implements Comparable<Pair>{
    int first;
    int second;
    public Pair(int first, int second)
    {
        this.first  = first;
        this.second = second;
    }


    public boolean isCompatible(Pair other)
    {
        return other.first > this.second;
    }
    @Override
    public int compareTo(Pair o) {

        int diff =this.first - o.first;
        diff  = diff ==0? this.second - o.second:diff;
//        System.out.println("Comparing:"+this+", "+o+": "+diff);
        return diff;
    }

    @Override
    public String toString() {
        return "(" +
                 first +
                "," + second +
                ')';
    }
}
class ScoredPair implements  Comparable<ScoredPair>{

    Pair pair;
    int score;
    public ScoredPair(Pair pair, int score){
        this.pair = pair;
        this.score = score;
    }
    public void setScore(int score)
    {
        this.score = score;
    }
    @Override
    public int compareTo(ScoredPair o) {
        return this.score - o.score;
    }

    @Override
    public String toString() {
        return "ScoredPair{" +
                "pair=" + pair +
                ", score=" + score +
                '}';
    }
}
public class MaxPairChain {
    public static void main(String[] args)
    {
        int[][] pairs = new int[][]{
//                {1,2},{2,4},{4,9},{5,6},{7,15},{9,11},{12,13}
                {-1,1},{-2,7},{-5,8},{-3,8},{1,3},{-2,9},{-5,2}
//                {-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}
        };

        System.out.println(new MaxPairChain().findLongestChain(pairs));

    }
    public int findLongestChain(int[][] pairs) {

        return  findLongestChainNNLogN(pairs);

    }
    public int findLongestChainNNLogN(int[][] pairs) {
        ArrayList<Pair> pairList = new ArrayList<Pair>();
        for(int i=0;i<pairs.length;i++)
        {
            pairList.add(new Pair(pairs[i][0],pairs[i][1]));
        }
        Collections.sort(pairList);

        ArrayList<ScoredPair> scoredPairList = new ArrayList<ScoredPair>();

        for(Pair p: pairList)
        {
            scoredPairList.add(new ScoredPair(p,1));
        }


        int maxChain = 1;
        while (!scoredPairList.isEmpty())
        {
            ScoredPair source = scoredPairList.remove(0);
            for(ScoredPair dest: scoredPairList)
            {
                if(source.pair.isCompatible(dest.pair) && source.score+1> dest.score)
                {
                    dest.score= source.score+1;
                    maxChain = Math.max(maxChain, dest.score);
                }
            }
            Collections.sort(scoredPairList);

        }
        return maxChain;
    }

    public int findLongestChainExponential(int[][] pairs){
        ArrayList<Pair> pairList = new ArrayList<Pair>();
        for(int i=0;i<pairs.length;i++)
        {
            pairList.add(new Pair(pairs[i][0],pairs[i][1]));
        }
        Collections.sort(pairList);

        return  findChain(new ArrayList<Pair>(), pairList );
    }
    public int findChain(ArrayList<Pair> prefix, ArrayList<Pair> rest)
    {
        System.out.println("pre:"+prefix+", rest:"+rest);
        if(rest.isEmpty())
        {
            return  prefix.size();
        }
        else
        {
            if(prefix.isEmpty())
            {
                int maxChain =0;
                for(int i=0;i<rest.size();i++)
                {
                    Pair p =rest.get(i);
                    prefix.add(p);
                    ArrayList<Pair> newRest = new ArrayList<Pair>(rest.subList(0,i));
                    newRest.addAll(rest.subList(i+1, rest.size()));
                    maxChain = Math.max(maxChain, findChain(prefix, newRest));
                    prefix.clear();
                }


                return maxChain;
            }
            else
            {
                Pair lastPair = prefix.get(prefix.size()-1);
                int maxChain =prefix.size();
                for(int i=0;i<rest.size();i++)
                {
                    Pair p = rest.get(i);
                    if(lastPair.isCompatible(p))
                    {
                        prefix.add(p);
                        ArrayList<Pair> newRest = new ArrayList<Pair>(rest.subList(i+1, rest.size()));

                        maxChain = Math.max(maxChain, findChain(prefix, newRest));
                        prefix.remove(prefix.size()-1);
                    }
                }

                return maxChain;
            }

        }
    }
}
