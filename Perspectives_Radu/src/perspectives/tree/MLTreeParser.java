package perspectives.tree;


import java.io.InputStream;
import java.io.OutputStream;

import perspectives.graph.Graph;
import perspectives.graph.MLGraphParser;

public class MLTreeParser implements TreeParser {

	@Override
	public Tree from(InputStream bf) {
		
		Graph g = new Graph(false);
		
		MLGraphParser p = new MLGraphParser();
		g = p.from(bf);		
		
		Tree t = new Tree(g);
		
		return t;
	}

	@Override
	public void to(Tree t, OutputStream bw) {
		
		MLGraphParser p = new MLGraphParser();
		p.to(t.getAsGraph(),bw);
	}

	@Override
	public String getName() {
		return "ML Tree";
	}

}
