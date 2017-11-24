package algorithm;

import java.util.ArrayList;

/**
 * Created by rajin on 11/22/2017.
 * 388. Longest Absolute File Path
 */
public class LongestAbsolutePath {
    public static void main(String[] args){
        String input="dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(new LongestAbsolutePath().lengthLongestPath(input));
    }
    public int lengthLongestPath(String input) {
        if(input ==null || input.length() ==0)
        {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<String> directoryPaths = new ArrayList<String>();
        int tabCount =0;
        boolean isDir = true;
        int maxLength =0;
        for(char ch : input.toCharArray())
        {
            if(ch =='\n')
            {
                StringBuilder dir = new StringBuilder();

                if(tabCount>0){
                    dir.append(directoryPaths.get(tabCount-1));
                    dir.append("/");
                }
                dir.append(sb.toString());
                if(isDir)
                {
                    if(tabCount < directoryPaths.size())
                    {
                        directoryPaths.set(tabCount, dir.toString());
                    }
                    else
                    {
                        directoryPaths.add(dir.toString());
                    }

                }
                else
                {
                    System.out.println(dir.toString());
                    maxLength = Math.max(maxLength, dir.length());
                }
                sb.setLength(0);
                tabCount=0;
                isDir = true;
            }
            else if( ch =='\t')
            {
                tabCount++;
            }
            else{
                if(ch=='.')
                {
                    isDir = false;
                }
                sb.append(ch);
            }

        }
        //last Time
        StringBuilder dir = new StringBuilder();

        if(tabCount>0){
            dir.append(directoryPaths.get(tabCount-1));
            dir.append("/");
        }
        dir.append(sb.toString());
        if(isDir)
        {
            if(tabCount < directoryPaths.size())
            {
                directoryPaths.set(tabCount, dir.toString());
            }
            else
            {
                directoryPaths.add(dir.toString());
            }

        }
        else
        {
            System.out.println(dir.toString());
            maxLength = Math.max(maxLength, dir.length());
        }

        return maxLength;

    }
}
