package perspectives.properties;

import perspectives.base.PropertyType;

public class PObject extends PropertyType{

	private Object value;
	
	public PObject(Object v)
	{
		value = v;
	}
	
	@Override
	public PObject copy() {		
		return new PObject(value);
	}

	@Override
	public String typeName() {
		return "PObject";
	}

	@Override
	public String serialize() {
	
		return null;
	}

	@Override
	public PropertyType deserialize(String s) {
		
		return null;
	}

}
