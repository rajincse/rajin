package algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by rajin on 8/9/2017.
 */
class BoardPosition{
    int row;
    int col;
    public  BoardPosition(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "BP(" +
                "" + row +
                "," + col +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardPosition that = (BoardPosition) o;

        if (row != that.row) return false;
        return col == that.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}
public class WordSearch {
    public static void main(String[] args)
    {
        char[][] board = new char[][]
        {
                {'A','B','C', 'S'},
                {'S','F','C', 'S'},
                {'A','D','E', 'E'},
        };
//                {{'A'}};
        for(char[] ca: board)
        {
            System.out.println(Arrays.toString(ca));
        }
        String word = "Z";
        System.out.println("Result:"+new WordSearch().exist(board, word));
    }
    public boolean exist(char[][] board, String word) {
        HashMap<Character, ArrayList<BoardPosition>> boardMap = new HashMap<Character, ArrayList<BoardPosition>>();

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                Character c = new Character(board[i][j]);
                if(!boardMap.containsKey(c)){
                    boardMap.put(c, new ArrayList<BoardPosition>());
                }
                ArrayList<BoardPosition> list = boardMap.get(c);
                list.add(new BoardPosition(i,j));
            }
        }
        for(Character k: boardMap.keySet()){
            System.out.println(k+"=>"+boardMap.get(k));
        }

        return wordExists(word.toCharArray(),0,boardMap, new ArrayList<BoardPosition>());
    }

    public boolean wordExists(char[] word, int start, HashMap<Character,  ArrayList<BoardPosition>> map, ArrayList<BoardPosition> blackListed){
        if(start <word.length)
        {
            System.out.println(" s:"+start+"("+word[start]+"), black:"+blackListed);
        }

        if(start == word.length)
        {
            return true;
        }

        Character c= word[start];
        ArrayList<BoardPosition> positions = map.get(c);

        if(positions == null)
            return false;


        for(BoardPosition p: positions)
        {
            if(blackListed.isEmpty())
            {
                blackListed.add(p);
                if(wordExists(word,start+1,map, blackListed))
                {
                    return true;
                }
                blackListed.remove(p);
            }
            else
            {
                BoardPosition lastAdded = blackListed.get(blackListed.size()-1);
                if(isAdjacent(lastAdded, p))
                {
                    if(!blackListed.contains(p))
                    {
                        blackListed.add(p);
                        if(wordExists(word,start+1,map, blackListed))
                        {
                            return true;
                        }
                        blackListed.remove(p);
                    }

                }
            }


        }

        return false;


    }

    public  boolean isAdjacent(BoardPosition p1, BoardPosition p2){
         return (p1.row == p2.row &&   Math.abs(p1.col-p2.col)==1) || (p1.col == p2.col &&   Math.abs(p1.row-p2.row)==1);

    }




}
