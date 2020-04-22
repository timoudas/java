// Enter what is to be calculated
// 11 12 + 15 10 - * =
// You have entered: 11 12 + 15 10 - * =
// You got: 115.0
package Datatypes;

import java.util.Scanner;


public class Main {
	
	double number1;
	double number2;
	double sum;
	
	public interface IDatatypes {

		public boolean is_empty();
		
		public void push(double x);
		
		public double pop();
	
}
	
	static class Stack implements IDatatypes {
		

		private double arr[] = new double[10];
		
		// private int head = 0;
		private int tail;
		private int length = 0;
		

		
		public boolean is_empty() {
			return length == 0;
		}

		
		public void push(double x) {
			if(isFull(length)) {
				arr = this.createNewArray(arr);
			}
			arr[length] = x;
			length++;	
			
		}

		private boolean isFull(int arrLenght) {
			// Check if array is full
			if (arrLenght == tail) {
				return true;
			} else {
				return false;
			}
		}
		
		private double[] createNewArray(double[] oldArr) {
			double[] newArr = new double[oldArr.length * 2];
			
			for (int i = 0; i < length; i++) {
				newArr[i] = oldArr[i];
			}
			tail = newArr.length; //Borde vara nya värdet 20
			return newArr;
					
		}
		
		public double pop() {
			if(is_empty()) {
				return 0;
			} else {
				double requestedValue =  arr[length - 1];
				arr[length - 1] = 0.0;
				length--;
				return requestedValue;
			}
		}

		public String toString() {
		
		String str = "";
		for (int i = 0; i <= arr.length - 1; i++) {
			str += arr[i];
			str += ", ";
		}
		return str;
		
		}
	}

	public static void main(String[] args) {
				Main calc = new Main();
				Scanner userObj = new Scanner(System.in);
				
				while(true) {
					IDatatypes stack;

					stack = new Stack();
					System.out.println("Enter what is to be calculated");
					
	
					String userInput = userObj.nextLine();
					userObj.close();
					System.out.println("You have entered: " + userInput);
					
					if (userInput.isEmpty()){
						System.out.println("You have not entered anything");
					} else {
						calc.calculate(userInput, stack);	
					}
				}
	}
	
	private void calculate(String userInput, IDatatypes stack) {
		for(String i : userInput.split(" ")) {
			if(i.equals("+") || i.equals("-") || i.equals("*") || i.equals("/") || i.contentEquals("="))
		     {
				switch (i) 
		          {
		                case "+":
		                    number1 = stack.pop();
		                    number2 = stack.pop();
		                    sum = number1 + number2;
		                    stack.push(sum);
		                    break;
		                case "-":
		                    number1 = stack.pop();
		                    number2 = stack.pop();
		                    sum = number2 - number1; // Switched because pop order
		                    stack.push(sum);
		                    break;
		                case "/":
		                    number1 = stack.pop();
		                    number2 = stack.pop();
		                    sum = number2/ number1;
		                    stack.push(sum);
		                    break;
		                case "*":
		                    number1 = stack.pop();
		                    number2 = stack.pop();
		                    sum = number2 * number1;
		                    stack.push(sum);
		                    break;
		                case "=":
		                	number1 = stack.pop();
		                	number2 = stack.pop();
		                	sum = number1 + number2;
			                stack.push(sum);
		                	double number3 = number1 + number2;
		                	System.out.println("You got: " + number3);
		                	break;
		            }
		         }
		         else{
		                 stack.push(Double.parseDouble(i));
		         }				
			}
	}
	
	
	
	
}

