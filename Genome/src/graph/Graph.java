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
 * 
 * Contig: segment av dna
 * Rad: Beskriver en overlap
 * 6,7: Start/End of overlap in first contig
 * 8: Length of first contig
 * 1 (6,7) raden går inte över hela
 * 1 (10,11) fullständig overlap
 *
 * ---> kolla (6,7,8) & (10,11 12) om någon är overlap: ta bort, annars behåll
 */

public class Graph {
	String row;
	static int _vertices;
	ArrayList<ArrayList<Integer>> adj_list;
	
	public Graph(String filename) {
		ParseData(filename);
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
	
	/*
	 * Parse the Identiers into unique integers and map them in a HashMap
	 */
	private HashMap<String, Integer> ParseIdentifiers(ArrayList<String> Identifiers) {
		HashMap<String, Integer> IDs = new HashMap<String, Integer>();
		int IntID = 0;
		for (String id : Identifiers) {
			IDs.put(id, IntID);
			IntID++;
		}
		_vertices = IntID;
		return IDs;
	}
	
	public void addEdge(int u, int v) {
		adj_list.get(u).add(v);
		adj_list.get(v).add(u); 
	}
	
	public void Edge(int u, int v) {
		if (checkElem(adj_list, v, u) == false && u != v){
			addEdge(v, u);
		}
	}
	
    private boolean checkElem(ArrayList<ArrayList<Integer>> arr, int vertex, int node) {
    	ArrayList<Integer> temp = arr.get(vertex);
    	if(temp.contains(node)){
    		return true;
    	} else {
    		return false;
    	}
    }
	
	private ArrayList<ArrayList<Integer>> BuildGraph(HashMap<String, Integer> ParsedIds, String filename){
		int vertexU = 0;
		int vertexV = 0;
        for (int i = 0; i < _vertices; i++) {
        	adj_list.add(new ArrayList<Integer>());
        }
		try {
			String path = filename;
			BufferedReader Genomefile = new BufferedReader(new FileReader(path));
			while ((row = Genomefile.readLine()) != null) {
			    String[] data = row.split("\t");
			    if (ParsedIds.containsKey(data[0])) {
			    	vertexU = ParsedIds.get(data[0]);
			    }
			    if (ParsedIds.containsKey(data[1])) {
			    	vertexV = ParsedIds.get(data[1]);
			    }
			    Edge(vertexU, vertexV);
			    
			}
			Genomefile.close();
		} catch(IOException ex){
		System.out.println(ex);
		}
		return adj_list;
	}
	
	public ArrayList<ArrayList<Integer>> ParseData(String filename) {
		adj_list = new ArrayList<ArrayList<Integer>>(_vertices);
		ArrayList<String> fileIds = LoadIdentifiers(filename);
		HashMap<String, Integer> ParsedIds = ParseIdentifiers(fileIds);
		System.out.println(Collections.singletonList(ParsedIds)); // method 2
		ArrayList<ArrayList<Integer>> graph = BuildGraph(ParsedIds, filename);
		return graph;
	}
	
    public void printGraph() { 

        for (int i = 0; i < adj_list.size(); i++) { 
            System.out.println("\nAdjacency list of vertex " + i); 
            for (int j = 0; j < adj_list.get(i).size(); j++) { 
                System.out.print(" -> "+adj_list.get(i).get(j)); 
            } 
            System.out.println(); 
        } 
    }
    
    public void PrintNodeDist() {
    		out.println(Arrays.toString(NodeDegreeDist()));
    }
    
    private int[] NodeDegreeDist() {
    	int[] dist = new int[_vertices];
    	for (int i = 0; i<_vertices; i++) {
    		int node = adj_list.get(i).size();
    		dist[i] = node;
    	}
    	return dist;
    }
    
    
    /*
     * Returns the total components in a graph
     * A component is defined as a subset in graph
     */
    private int[] GraphComponents() {
    	return int[];
    }
    
    /*
     * Returns the size distrubution of the components
     */
    private int[] GraphComponentsDist() {
    	return int[];
    }


	
	public static void main(String[] args) {
		Graph graph = new Graph("/Users/andreas/eclipse-workspace/Genome/data/test.txt");
		graph.printGraph();
		
		graph.PrintNodeDist();
		
	}
	
	/**
	 * Contig: segment av dna
	Rad: Beskriver en overlap
	6,7: Start/End of overlap in first contig
	8: Length of first contig
	1 (6,7) raden går inte över hela
	1 (10,11) fullständig overlap

	---> kolla (6,7,8) & (10,11 12) om någon är overlap: ta bort, annars behåll
	**/
}
	
