package perspectives.tree;

import java.io.InputStream;
import java.io.OutputStream;

public interface TreeParser {
	
	public Tree from(InputStream br);
	public void to(Tree t, OutputStream bw);
	
	public String getName();
	

}
