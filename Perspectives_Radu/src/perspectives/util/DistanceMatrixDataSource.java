package perspectives.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.HashMap;

import perspectives.base.DataSource;
import perspectives.base.Property;
import perspectives.properties.PFileInput;

public class DistanceMatrixDataSource extends DataSource {
	
	public DistanceMatrix dm;

	public DistanceMatrixDataSource(String name) {
		super(name);
		
		Property<PFileInput> p = new Property<PFileInput>("Load", new PFileInput())
				{

					@Override
					protected boolean updating(PFileInput newvalue) {
						
						load(newvalue.path);
						
						return true;
					}
			
				};
		addProperty(p);
	}
	
	private void load(String file)
	{
		loadFromCSV(file);
		setLoaded(true);
	}
	
	private void loadFromCSV(String file)
	{
		try
		{
		
			BufferedReader d  = new BufferedReader(new FileReader(file));		

			String line = "";
			int y = 0;
			while ( (line = d.readLine()) != null)
			{
				String[] split = line.split("\t");
				
				if (dm == null)
					dm = new DistanceMatrix(split.length);
				
				for (int x=0; x<split.length; x++)
					dm.set(x, y, Float.parseFloat(split[x]));
				
				y++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

}
