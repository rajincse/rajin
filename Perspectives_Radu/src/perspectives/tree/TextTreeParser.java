package perspectives.tree;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class TextTreeParser implements TreeParser{
	
	HashMap<String, TreeNode> map = new HashMap<String, TreeNode>();

	@Override
	public Tree from(InputStream br) {
		
		BufferedInputStream bis = new BufferedInputStream(br);
		DataInputStream dis = new DataInputStream(bis);
		
		TreeNode root = null;
		
		try{
		String line;
		while ((line = dis.readLine()) != null)
		{
			String[] split = line.split("\t");
			
			if (root == null)
			{
				root = new TreeNode();
				map.put(split[0], root);
			}
			
			TreeNode parent = map.get(split[0]);
			
			for (int i=1; i<split.length; i++)
			{
				TreeNode n = new TreeNode();
				map.put(split[i], n);
				
				parent.addChild(n);
			}
		}		
		return new Tree(root);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void to(Tree t, OutputStream bw) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Text";
	}

}
