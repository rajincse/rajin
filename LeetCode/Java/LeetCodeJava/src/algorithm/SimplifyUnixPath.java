package algorithm;

import java.util.LinkedList;

/**
 * Created by rajin on 7/28/2017.
 */
public class SimplifyUnixPath {
    public static void main(String[] args)
    {
        String path = "/home/foo/.ssh/../.ssh2/authorized_keys/";
        System.out.println(new SimplifyUnixPath().simplifyPath(path));
    }

    public String simplifyPath(String path) {

        String[] split = path.split("/");
        LinkedList<String> stack = new LinkedList<String>();
        for(int i=0;i<split.length;i++)
        {
            String item = split[i];
            if(item.equals(".."))
            {
                if(!stack.isEmpty())
                {
                    stack.removeLast();
                }

            }
            else if(!item.isEmpty() && !item.equals(".")){
                stack.add(item);
            }
        }
        StringBuilder simplePath = new StringBuilder();

        if(stack.isEmpty())
        {
            simplePath.append("/");
        }

        for(String item: stack)
        {
            simplePath.append("/");
            simplePath.append(item);
        }

        return simplePath.toString();
    }
}
