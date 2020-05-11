/*
 * Implement a max heap
 */


class Heap {
    int heap_array[];
    int n_elems = 0;
    int capacity;

    // Constructor
    Heap(int _capacity) {
        capacity = _capacity;
        heap_array = new int[capacity];
    }

 
    /**
     * Private method for maintaining the heap.
     * @param i, index of the element to heapify from
     **/
    private void heapify(int i) {

    	int left = 2*i + 1;
    	int right = 2*i + 2;
    	int largest = i;
    	//if left ≤ heap_length[A] and A[left] > A[largest] then:
        if (left <= n_elems && heap_array[left] > heap_array[largest]) {
            largest = left;
            //System.out.println("largest = left");
        }
    		
    	//if right ≤ heap_length[A] and A[right] > A[largest] then:
        if (right <= n_elems && heap_array[right] > heap_array[largest]) {
        	//System.out.println("largest = right");
            largest = right; 
        }
    	//if largest ≠ i then:
        if (largest != i) { 
            int swap = heap_array[i]; 
            heap_array[i] = heap_array[largest]; 
            heap_array[largest] = swap; 
            
            // Recursively heapify the affected sub-tree 
            heapify(largest); 
        } 

    }
    
    /**
     * Add an element to the heap and ensure the heap property
     * Throws an exception if trying to add elements to a full heap.
     * @param x Element to add
     */
    public void insert(int x) throws Exception {
        if(is_full()) {
            throw new Exception("The heap is full");
        }
        // Insert the element at end of Heap 
        heap_array[n_elems++] = x; 

        // now sift it up
        int current = n_elems-1;
        int parent = getParent(current);
        while (current > 0 && heap_array[current] > heap_array[parent]) {
            int swap = heap_array[parent];
            heap_array[parent] = heap_array[current];
            heap_array[current] = swap;
            current = parent;
            parent = getParent(current);
        }


    }
    

   public int extract_max() throws Exception {
   	//Get the largest
       // Get the last element 
   		int root = heap_array[0];
   	
   		int lastElement = heap_array[n_elems-1];
   	
       // Replace root with first element 
       heap_array[0] = lastElement; 
 
       // Decrease size of heap by 1 
       n_elems--;
       
       // heapify the root node 
       heapify(0);
       
       // return new size of Heap 
       return root;
       
   }
   private int getParent(int i) {
	   return (i-1)/2;
   }

    public int capacity() {
        return capacity;
    }

    public int size() {
        return n_elems;
    }

    public boolean is_empty() {
        return n_elems == 0;
    }
    
    public boolean is_full() {
    	return n_elems == capacity;
    }
    
    public void print() {
    	for(int i = 0; i < n_elems; i++) {
    		System.out.println(heap_array[i]);
    	}
    }
    




    /**
     * Remove and return largest element, and maintain the heap property.
     * Throws an exception if trying to extract an element from an empty heap.
     */
  


    /**
     * For convenience, a small program to test the code.
     * There are better ways of doing this kind of testing!
     * @throws Exception 
     *
     */
    static public void main(String args[]) throws Exception { // A simple test program
        // Declare two heaps. Both should work nicely!
        Heap h1 = new Heap(100);
        Heap h2 = new Heap(10);
        int data[] = {1, 4, 10, 14, 7, 9, 3, 8, 16};


        //
        // Insert 1 element to heap 1, and several to heap 2.
        System.out.println("Inserting data.");
        try {
            h1.insert(7); 
            // Insert a single element in heap 1
            // Insert several elements in heap 2. Heap 1 must not be affected.
            for (int x: data) {
                h2.insert(x);
            }


        } catch (Exception e) {
            System.err.println("During insertion:");
            System.err.println(e);
            System.exit(1);
        }

        if (h2.size() != data.length) {
            System.err.println("Error! Wrong number of elements in heap 2.");
        }
        

        //
        // Time to empty heap 2. Does that work?
        //

        try {
            System.out.println("Contents of heap 2:");
            while (! h2.is_empty()) {
                int x = h2.extract_max();
                System.out.println(x);
            }
            if (! h2.is_empty()) {
                System.err.println("Error! Heap 2 has not been emptied!");
            }
            if (h1.size() != 1) {
                System.err.println("Error! Wrong number of elements in heap 1.");
            }

        } catch (Exception e) {
            System.err.println("During extraction:");
            System.err.println(e);
            System.exit(1);
        }

    }

   
}