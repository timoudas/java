package Datatypes;

public class Stack implements IDatatypes {
	

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
