import java.util.*;
import static java.lang.System.out;


public class RandGraph {
	
	public static int meanDiameter(int totalGraphs, double prob ) {
		int meanDiameter = 0;
		int maxDiameter = 0;
		int count = 0;
		for(int i = 0; i < totalGraphs; i++) {
			Graph graph = new Graph(100, prob);
			for (int n = 0; n < graph._vertices; n++){
				maxDiameter = graph.maxDistance(graph.distance(n));
				
				if(graph.maxDistance(graph.distance(n)) > maxDiameter){
					maxDiameter = graph.maxDistance(graph.distance(n));
				}
			}
			count++;
			meanDiameter += maxDiameter;
			
		}
		out.println(count);
		meanDiameter = meanDiameter / totalGraphs;
		out.println(meanDiameter);
		return meanDiameter;
	}
	public static void main(String[] args) {
		double increment = 0.05;
		double max = 0.9;
		for(double i = 0.4; i < max; i+=increment) {
			meanDiameter(100, i);
		}
	}
}
