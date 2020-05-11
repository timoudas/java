package graph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import static java.lang.System.out;


/*
 * This data is implicitly defining a graph. 
 * Columns 1 and 2 are vertex identifiers, so each line is a potential edge. 
 * Columns 6-8 and 10-12 describes the overlap. It the overlap is actually containment 
 * If contig A is a subsequence of contig B — then A should be discarded because it is redundant.
 */

public class Graph {
	String row;
	static int _vertices;
	ArrayList<ArrayList<Integer>> adj_list;
	
	public Graph() {
		
	}
	
	/*
	 * @param filename
	 * Load the identifiers of the vertices into an ArrayList
	 */
	private ArrayList<String> LoadIdentifiers(String filename) {
		ArrayList<String> Identifiers = new ArrayList<String>();
		String[] data = null;
		try {
			String path = filename;
			BufferedReader Genomefile = new BufferedReader(new FileReader(path));
			while ((row = Genomefile.readLine()) != null) {
			    data = row.split("\t");
			    if (!Identifiers.contains(data[0])) {
			    	Identifiers.add(data[0]);
			    }
			    if (!Identifiers.contains(data[1])) {
			    	Identifiers.add(data[1]);
			    }
			}
			Genomefile.close();
		
		} catch(IOException ex){
		System.out.println(ex);
		}
		return Identifiers;
	}
	
	public ArrayList<ArrayList<Integer>> ParseData() {
		adj_list = new ArrayList<ArrayList<Integer>>(_vertices);
		
	}
	
	/*
	 * Parse the Identiers into unique integers and map them in a HashMap
	 */
	private HashMap<String, Integer> ParseIdentifers(ArrayList<String> Identifiers) {
		HashMap<String, Integer> IDs = new HashMap<String, Integer>();
		int IntID = 0;
		for (String id : Identifiers) {
			IDs.put(id, IntID);
			IntID++;
		}
		_vertices = IntID;
		return IDs;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph();
		String[] data = graph.ParseData("/Users/andreas/eclipse-workspace/Genome/data/test.txt");
		for(String s: data) {
			out.println(s);
		}
	}
		

}
