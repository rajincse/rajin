package algorithm;

import java.util.LinkedList;

/**
 * Created by rajin on 9/16/2017.
 */
public class ValidParenthesesString {
    public  static  void main(String[] args){
        //String s ="((*)(*))((*";//
        //String s ="(*()()())";//**(())()()()()((*()*))((*()*)";
        //String s = "(*()";
        String s ="**((((*)(*((((((((*)((*)";
        System.out.println(new ValidParenthesesString().checkValidString(s));
    }
    public boolean checkValidString(String s) {

        if(s==null || s.isEmpty())
        {
            return true;
        }
        char[] charArray = s.toCharArray();
        LinkedList<Character> stack = new LinkedList<Character>();
        LinkedList<Character> starContainer = new LinkedList<Character>();
        for(int i=0;i<charArray.length;i++)
        {
            if(charArray[i] =='(')
            {
                stack.addFirst(charArray[i]);
            }
            else if(charArray[i] ==')')
            {
                if(!stack.isEmpty() )
                {

                    while (!stack.isEmpty() && stack.getFirst() !='(')
                    {
                        starContainer.add(stack.removeFirst());
                    }
                    if(stack.isEmpty() && starContainer.isEmpty())
                    {
                        return false;
                    }
                    else if(!stack.isEmpty())
                    {
                        stack.removeFirst();
                    }
                    else if(!starContainer.isEmpty())
                    {
                        starContainer.remove();
                    }

                    stack.addAll(0, starContainer);
                    starContainer.clear();
                }
                else
                {
                    return false;
                }
            }
            else
            {
                stack.addFirst(charArray[i]);

            }
        }
        int starCount =0;
        for(Character c: stack)
        {
            if(c.equals('*'))
            {
                starCount++;
            }
            else {
                starCount--;
                if(starCount<0)
                {
                    return false;
                }
            }
        }
        return true;

    }

}
