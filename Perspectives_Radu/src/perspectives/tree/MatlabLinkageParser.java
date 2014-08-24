package perspectives.tree;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import perspectives.base.Property;
import perspectives.properties.PDouble;

public class MatlabLinkageParser implements TreeParser{

	@Override
	public Tree from(InputStream br) {
		
		BufferedInputStream bis = new BufferedInputStream(br);
		DataInputStream dis = new DataInputStream(bis);
		
		TreeNode root = null;
		
		try{
			
			ArrayList<String> lines = new ArrayList<String>();
			String line;
			while ((line = dis.readLine()) != null)
				lines.add(line);
			
			int nrElem = lines.size()+1;
			
			TreeNode[] allNodes = new TreeNode[nrElem + lines.size()];
			for (int i=0; i<nrElem; i++)
			{
				allNodes[i] = new TreeNode();
				allNodes[i].addProperty(new Property<PDouble>("height", new PDouble(0)));
			}
			
			for (int i=0; i<lines.size(); i++)
			{
				String[] split = lines.get(i).split("\t");
				
				allNodes[nrElem+i] =  new TreeNode();
				TreeNode child1 = allNodes[Integer.parseInt(split[0])-1];
				TreeNode child2 = allNodes[Integer.parseInt(split[1])-1];
				double h = Double.parseDouble(split[2]);
				allNodes[nrElem+i].addChild(child1);
				allNodes[nrElem+i].addChild(child2);
				allNodes[nrElem+i].addProperty(new Property<PDouble>("height", new PDouble(h)));
				
			}
			
			root = allNodes[allNodes.length-1];			
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
		return "Matlabl Linkage";
	}

}
