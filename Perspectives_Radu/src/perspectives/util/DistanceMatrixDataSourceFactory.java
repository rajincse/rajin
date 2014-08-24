package perspectives.util;

import perspectives.base.DataSource;
import perspectives.base.DataSourceFactory;

public class DistanceMatrixDataSourceFactory extends DataSourceFactory {

	@Override
	public DataSource create(String name) {
		return new DistanceMatrixDataSource(name);
	}

	@Override
	public String creatorType() {
		
		return "DistanceMatrix";
	}

}
