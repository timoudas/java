import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


//Users/andreas/eclipse-workspace/Graph/src/test.txt
public class GraphIO {
	
	public static void main(String[] args) {
		
		int vertex = 0;
		Scanner userObj = new Scanner(System.in);
	
		System.out.println("Input a filename");
		System.out.println("File should be a comma-separated text file with nodes like the following '5, 1'");
		
		String filename = userObj.nextLine();
		userObj.close();
		System.out.println("You have entered: " + filename);
		

		//Read graph file
		String row;
		try {
			String path = filename;
			BufferedReader graphFile = new BufferedReader(new FileReader(path));
			while ((row = graphFile.readLine()) != null) {
			    String[] data = row.split(",");
			    for (String s : data) {
			    	if(Integer.parseInt(s) > vertex) {
			    		vertex = Integer.parseInt(s);
			    	}
			    }
			}
			graphFile.close();
		
		} catch(IOException ex){
		System.out.println(ex);
		}
		
		Graph._vertices = vertex + 1;
		Graph graph = new Graph();

		try {
			String path = filename;
			BufferedReader graphFile = new BufferedReader(new FileReader(path));
			while ((row = graphFile.readLine()) != null) {
			    String[] data = row.split(",");
			    System.out.println("Node: " + data[0] + "," +" "+ data[1]);
			    graph.addEdge(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
			}
			graphFile.close();
		
		} catch(IOException ex){
		System.out.println(ex);
		}
		
		graph.printGraph();
		graph.distance(0);
		
		
	}
}
