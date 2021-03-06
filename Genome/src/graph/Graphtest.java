package graph;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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

public class Graphtest {
	String row;
	static int _vertices;
	ArrayList<ArrayList<Integer>> adj_list;
	
	public Graphtest(String Ids, String filename) {
		ParseData(Ids, filename);
	}
	

	
	/*
	 * @param filename
	 * Load the identifiers of the vertices into an ArrayList
	 */
	private ArrayList<String> LoadIdentifiers(String filename) {
		ArrayList<String> Identifiers = new ArrayList<String>();
		String[] data = null;
		int count = 0;
		try {
			String path = filename;
			FileInputStream path_to_uniq = new FileInputStream(filename);
			BufferedReader Genomefile = new BufferedReader(new InputStreamReader(path_to_uniq));
			while ((row = Genomefile.readLine()) != null) {
				count ++;;
			    data = row.split(" ");
			    Identifiers.add(data[0]);
			 
			    if ((count % 100000) == 0) {
			    	out.println(count);
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
		out.println(Identifiers.size());
		int IntID = 0;

		for (String id : Identifiers) {
			IDs.put(id, IntID);
			IntID++;
			if(IntID % 100000 == 0) {
				out.println(IntID);
			}
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
	
	public ArrayList<ArrayList<Integer>> ParseData(String uniq, String filename) {
		out.println("Adding ArrayList adj_list...");
		adj_list = new ArrayList<ArrayList<Integer>>(_vertices);
		out.println("Setting fileIds...");
		ArrayList<String> fileIds = LoadIdentifiers(uniq);
		out.println("Parsing fileIds...");
		HashMap<String, Integer> ParsedIds = ParseIdentifiers(fileIds);
		//System.out.println(Collections.singletonList(ParsedIds)); 
		out.println("Building Graph...");
		ArrayList<ArrayList<Integer>> graph = BuildGraph(ParsedIds, filename);
		out.println("Graph completed");
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
    
    public String NodeDist() {
    	return Arrays.toString(NodeDegreeDist());
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

    public int GraphComponents() { 
        // Mark all the vertices as not visited 
        boolean[] visited = new boolean[_vertices]; 
        int components = 0;
        for(int v = 0; v < _vertices; ++v) { 
            if(!visited[v]) { 
                // print all reachable vertices 
                // from v 
            	BFS(v,visited); 
                components++;
            } 
        } 
        return components;
    } 
    
    private void BFS(int vertex, boolean[] visited) {
    	//boolean visited[] = new boolean[_vertices];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[vertex] = true;
        queue.add(vertex);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            List<Integer> adj = adj_list.get(v);
            for (Integer w : adj) {
                if (!visited[w]) {
                    visited[w] = true;
                    queue.add(w);
                }
            }
        }
    }
    
    public ArrayList<Integer> GraphComponentsDist() { 
        // Mark all the vertices as not visited 
        boolean[] visited = new boolean[_vertices]; 
        int dist;
        ArrayList<Integer> compDist = new ArrayList<Integer>();
        for(int v = 0; v < _vertices; ++v) { 
            if(!visited[v]) { 
                // print all reachable vertices 
                // from v 
            	dist = BFSdistance(v,visited); 
            	
            	compDist.add(dist);
            } 
        } 
        return compDist;
    } 
    
    private int BFSdistance(int vertex, boolean[] visited) {
    	//boolean visited[] = new boolean[_vertices];
        Queue<Integer> queue = new ArrayDeque<>();
        int distance = 0;

        visited[vertex] = true;
        queue.add(vertex);

        //int Nodistance = 0;

        while (!queue.isEmpty()) {
            int v = queue.poll();

            List<Integer> adj = adj_list.get(v);
            distance++;
            for (Integer w : adj) {
                if (!visited[w]) {
                    //System.out.println("Distance from vertex: " + vertex + " to: " + w +" is " + length);
                    visited[w] = true;
                    queue.add(w);
                }
            }
        }
        return distance;
    }
    
    /*
     * Returns the size distrubution of the components
     */

	
	public static void main(String[] args) throws IOException {
		out.println("Loading file..");
		String uniq = "C:\\Users\\timou\\Desktop\\java\\Genome\\data\\uniq.txt";
		String dataset ="C:\\Users\\timou\\Desktop\\java\\Genome\\data\\dataset.txt";
		Graphtest graph = new Graphtest(uniq, dataset);
		out.println("Loading complete");
		PrintStream GCDist = new PrintStream(new File("C:\\Users\\timou\\Desktop\\java\\Genome\\output\\GCDist.txt")); 
        // Assign o to output stream 
		System.err.println("Writing GraphComponentsDist");
        System.setOut(GCDist); 
        System.out.println(graph.GraphComponentsDist());
        System.err.println("GraphComponentsDist completed");
        
		PrintStream GCcomp = new PrintStream(new File("C:\\Users\\timou\\Desktop\\java\\Genome\\output\\GCcomp.txt")); 
        // Assign o to output stream 
		System.err.println("Writing GraphComponents");
        System.setOut(GCcomp); 
        System.out.println(graph.GraphComponents());
        System.err.println("GraphComponents completed");
        
		PrintStream NodeDist = new PrintStream(new File("C:\\Users\\timou\\Desktop\\java\\Genome\\output\\NodeDist.txt")); 
        // Assign o to output stream 
		System.err.println("Writing NodeDist");
        System.setOut(NodeDist); 
        System.out.println(graph.NodeDist());
        System.err.println("NodeDist completed");


		
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
	

