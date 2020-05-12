import java.util.*;
import static java.lang.System.out;
import java.util.concurrent.ThreadLocalRandom; 


public class Graph {
	static int _vertices;
	ArrayList<ArrayList<Integer>> adj_list;
	
	Graph(){
		adj_list = new ArrayList<ArrayList<Integer>>(_vertices);
        for (int i = 0; i < _vertices; i++) {
        	adj_list.add(new ArrayList<Integer>());
        }
		System.out.println("Graph is empty");
		System.out.println("Use Graph(int vertices, double prob) to generate a Graph");
		System.out.println("Or input edges manually with method Edge()");
	}
	
	public void Edge(int u, int v) {
		if (checkElem(adj_list, v, u) == false && u != v){
			addEdge(v, u);
		}
	}
	
	public void addEdge(int u, int v) {
		adj_list.get(u).add(v);
		adj_list.get(v).add(u); 
		//out.println(adj_list);
	}
	
	/*
	 * loop through all pairs of vertices u, v and decide, 
	 * randomly with probability p, whether the edge (u, v) 
	 * is in the graph.
	 */

	Graph(int vertices, double prob){
		_vertices = vertices;
		adj_list = new ArrayList<ArrayList<Integer>>(vertices);
        for (int u = 0; u < vertices; u++) {
        	//System.out.println(i);
        	adj_list.add(new ArrayList<Integer>());
        }
        for (int v = 0; v < vertices; v++) {
        	for(int u = 0; u < vertices; u++) {
				double random = ThreadLocalRandom.current().nextDouble(0, 1);
				if (random < prob) {
					//System.out.println(checkElem(adj_list, v));
					if (checkElem(adj_list, v, u) == false && u != v){
						addEdge(v, u);
					}
				}
			}
        }
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
    
    /*
     * @param vert: A vertex in the graph
     */
    public void printVertex(int vert) {
    	System.out.print(" -> "+adj_list.get(vert)); 
    }
       
    /*
     * @param arr: list of list that represents graph
     * @param vertex: a vertex in the graph
     * @param node: node to be checked in vertex
     */
    private boolean checkElem(ArrayList<ArrayList<Integer>> arr, int vertex, int node) {
    	ArrayList<Integer> temp = arr.get(vertex);
    	if(temp.contains(node)){
    		return true;
    	} else {
    		return false;
    	}
    }

	/*
	 * @param start: A vertex to start the search from in the graph
	 */
    public ArrayList<Integer> distance(int vertex) {
        boolean visited[] = new boolean[_vertices];
        Queue<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> distance = new ArrayList<Integer>();
        for(int i=0; i<adj_list.size(); i++) {
        	distance.add(-1);
        }
        
        visited[vertex] = true;
        queue.add(vertex);

        int length = 0;
        //int Nodistance = 0;

        while (!queue.isEmpty()) {
            int v = queue.poll();

            List<Integer> adj = adj_list.get(v);
            length++;
            for (Integer w : adj) {
                if (!visited[w]) {
                    //System.out.println("Distance from vertex: " + vertex + " to: " + w +" is " + length);
                    distance.set(w, length);
                    visited[w] = true;
                    queue.add(w);
                }
            }
        }
        return distance;
    }
    
    public int maxDistance(ArrayList<Integer> dist) {
    	return Collections.max(dist); 	
    }
		
	public static void main(String[] args) {
		Graph graph = new Graph(10, 0.6);
		graph.printGraph();
		int vertex = 0;
		ArrayList<Integer> vertex_dist = graph.distance(vertex);
		for (int i=0; i<vertex_dist.size(); i++) {
			int distance = vertex_dist.get(i);
			out.println("Distance from vertex: " + vertex + " to: " + i + " is " + distance);
		}
		out.println(graph.maxDistance(graph.distance(vertex)));
	}	
}


