package Courseinfo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class CourseInfo {

	public static void main(String[] args) {
		BinarySearchTree courses = new BinarySearchTree();
		
		//Read from csv
		String row;
		try {
			String path = "/Users/andreas/eclipse-workspace/BinarySearchTree/src/Courseinfo/courses.csv";
		BufferedReader csvReader = new BufferedReader(new FileReader(path));
		while ((row = csvReader.readLine()) != null) {
		    String[] data = row.split(",");
		    System.out.println("Added: " + " "+ data[0] +" "+ data[1] +" "+ Double.parseDouble(data[2]));
		    courses.insert(data[0], data[1], Double.parseDouble(data[2]));
		}
		csvReader.close();
		} catch(IOException ex){
			System.out.println(ex);
		}
		
		//Size of tree
		int n = courses.size();
		System.out.printf("There are %d courses in the database.\n", n);
		
		//Find node
		String code = courses.find("DA2004").getCourseCode();
		double cred = courses.find("DA2004").getCredits();
		String name = courses.find("DA2004").getCourseName();
		System.out.println("CourseCode: " + code);
		System.out.println("Coursename: " + name);
		System.out.println("Coursename: " + cred);

         
		//Iter over nodes
		for (BSTNode node : courses) {
            System.out.println(node.getCourseName());
        }

	}
}