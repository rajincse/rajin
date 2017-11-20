import GazeCol.MovieAnalytics;
import perspectives.base.Environment;
import perspectives.tree.HierarchicalClusteringViewerFactory;
import perspectives.tree.TreeDataFactory;
import perspectives.tree.TreeViewerFactory;
import perspectives.util.DistanceMatrixDataSource;
import perspectives.util.TableData;



public class main {

	  public static void main(String[] args) {  
		  Environment e = new Environment(false, Environment.PLAYING);
		  e.registerDataSourceFactory(new TreeDataFactory());
		  e.registerViewerFactory(new TreeViewerFactory());
		  e.registerViewerFactory(new HierarchicalClusteringViewerFactory());
//		  e.addViewer(new RajinViewer("blah"));
		  e.addViewer(new MovieAnalytics("Movie"));
//		  e.addDataSource(new TableData("Clustering"));
//		  e.addDataSource(new DistanceMatrixDataSource("Clustering"));
		  
		  
	  }
	      
}


