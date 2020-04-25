package Courseinfo;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class CourseInfo {

	public static void main(String[] args) {
		BinarySearchTree courses = new BinarySearchTree();
		
		//courses.insert("DA3018", "Computer Science", 7.5);
		//courses.insert("MM2001", "Matematik I", 30.0);
		//courses.insert("DA2004", "Programmeringsteknik1", 7.5);
		//courses.insert("DA2005", "Programmeringsteknik2", 7.5);
		//courses.insert("DA2006", "Programmeringsteknik3", 7.5);
		//courses.insert("DA2007", "Programmeringsteknik4", 7.5);

		//System.out.println(courses.find("DA2007").getCourseName());
		

		
		
		
		//String code = courses.find("DA2007").getCourseCode();
		//double cred = courses.find("DA2007").getCredits();
		//String name = courses.find("DA2007").getCourseName();
		//System.out.println("CourseCode: " + code);
		//System.out.printf("Credits: %s\n", cred);
		//System.out.printf("Name: %s\n", name);
		//for(BSTNode node: courses) {
		//System.out.println(node.getCourseCode());  
		//}
		
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
		
		int n = courses.size();
		System.out.printf("There are %d courses in the database.\n", n);
		String code = courses.find("DA2004").getCourseCode();
		double cred = courses.find("DA2004").getCredits();
		String name = courses.find("DA2004").getCourseName();
		System.out.println("CourseCode: " + code);
		System.out.println("Coursename: " + name);
		System.out.println("Coursename: " + cred);
		Iterator<BSTNode> iter = courses.iterator();
         
		
		while (iter.hasNext()) {
            System.out.println("Works");
        }

	}
}