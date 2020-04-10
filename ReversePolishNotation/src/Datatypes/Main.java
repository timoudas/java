package Datatypes;
import java.util.List;
import java.util.Scanner;


public class Main {
	
	
	public static void main(String[] args) {
		IDatatypes stack = new Stack();
	    double number1;
	    double number2;
		
		Scanner userObj = new Scanner(System.in);
		System.out.println("Enter what is to be calculated");

		String userInput = userObj.nextLine();
		System.out.println("You have entered: " + userInput);
		
		
		if (userInput.isEmpty()){
			System.out.println("You have not entered anything");
		} else {
				for(String i : userInput.split(" ")) {
					System.out.println(i);
					if(i.equals("+") || i.equals("-") || i.equals("*") || i.equals("/"))
				     {
				          switch (i) 
				          {
				                case "+":
				                    number1 = stack.pop();
				                    number2 = stack.pop();
				                    double sum = number1 + number2;
				                    stack.push(sum);	
				                    System.out.print(number1 + " + " + number2);
				                    break;
				                case "-":
				                    number1 = stack.pop();
				                    number2 = stack.pop();
				                    sum = number1 - number2;
				                    stack.push(sum);	
				                    System.out.print(number1 +" - "+ number2);
				                    break;
				                case "/":
				                    number1 = stack.pop();
				                    number2 = stack.pop();
				                    sum = number1 / number2;
				                    stack.push(sum);	
				                    System.out.print(number1 +" / "+ number2);
				                    break;
				                case "*":
				                    number1 = stack.pop();
				                    number2 = stack.pop();
				                    sum = number1 * number2;
				                    stack.push(sum);	
				                    System.out.print(number1 +" * "+ number2);
				                    break;
				                case "=":
				                    number1 = stack.pop();
				                    number2 = stack.pop();
				                    sum = number1 * number2;
				                    stack.push(sum);	
				                    System.out.println("You got " + stack);
				                    break;
				                }
				         }
				         else{
				                 stack.push(Double.parseDouble(i));
				         }				
				}
			}
		}
	}	

