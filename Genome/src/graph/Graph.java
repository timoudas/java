package graph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/*
 * This data is implicitly defining a graph. 
 * Columns 1 and 2 are vertex identifiers, so each line is a potential edge. 
 * Columns 6-8 and 10-12 describes the overlap. It the overlap is actually containment 
 * If contig A is a subsequence of contig B — then A should be discarded because it is redundant.
 */

public class Graph {
	String row;
	
	public Graph(string filename) {
		
	}
	
	/*
	 * @param filename
	 */
	private ArrayList<Double> ParseData(String filename) {
		try {
			String path = filename;
			BufferedReader Genomefile = new BufferedReader(new FileReader(path));
			while ((row = Genomefile.readLine()) != null) {
			    String[] data = row.split(",");
			    System.out.println("Node: " + data[0] + "," +" "+ data[1]);
			    //Check for redundancy 
			    //ParseIdentifers here
			}
			graphFile.close();
		
		} catch(IOException ex){
		System.out.println(ex);
		}
	}
	
	private void ParseIdentifers() {
		
	}

}
