import java.util.*;
import static java.lang.System.out;


public class RandGraph {
	
	public static int meanDiameter(int totalGraphs, double prob ) {
		int meanDiameter = 0;
		for(int i = 0; i < totalGraphs; i++) {
			Graph graph = new Graph(100, prob);
			meanDiameter += graph.maxDistance(graph.distance(0));
		}
		meanDiameter = meanDiameter / totalGraphs;
		out.println(meanDiameter);
		return meanDiameter;
	}
	public static void main(String[] args) {
		double increment = 0.05;
		double max = 0.9;
		for(double i = 0.4; i < max; i+=increment) {
			meanDiamseter(100, i);
		}
	}
}
