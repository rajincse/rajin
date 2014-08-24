package perspectives.graph;

import java.io.InputStream;
import java.io.OutputStream;

public interface GraphParser {
	
	public Graph from(InputStream br);
	public void to(Graph g, OutputStream bw);

}
