package perspectives.d3;
import java.awt.image.BufferedImage;

import org.json.simple.JSONObject;

public interface D3Renderer {
	
	public JSONObject renderToData();
	
	public BufferedImage getImage(String id);

}
