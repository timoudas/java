package Courseinfo;
import java.util.Stack;
import java.util.Iterator;


/**
 * Store course information in a binary search tree
 *
 */
class BinarySearchTree implements Iterable<BSTNode> {
	
	
	
	/**
	 * Attributes
         */
	BSTNode root=null;  // The root of the tree. Start with an empty tree.
	int currentKey = 0;
	int size = 0;

	public BinarySearchTree() {
		// Empty constructor?
	
	}
	

	/**
	 * Public interface for inserting data into the datastructure. Utilizes
	 * a private, more technical method.
	 * @param courseCode, eg "DA3018"
	 * @param courseName, eg "Computer Science"
	 * @param courseCredits, eg 7,5
	 */

	public void insert(String courseCode, String courseName, double courseCredits) {
		size++;
		BSTNode node = new BSTNode(courseCode, courseName, courseCredits);
		root = insert(root, node); // Call to private method with the same name, but other parameters
	}


	/**
	 * Insert 'node' into the tree pointed at by 'root'.
	 * @returns The node that should be the root of this subtree.
	 * @param root
	 * @param node
	 *
	 * WARNING! This method has a bug, it does not behave according to specification!
	 */
	
	private BSTNode insert(BSTNode root, BSTNode node) {
		if (root==null) {
			//System.out.println("Root is not set " + node.getCourseCode());
			//size++;
			return node; // The easy case. Let the current node be the root of a new (sub?)tree.
		} else {
			String currentKey = root.getCourseCode();
			
			BSTNode left = root.getLeftChild();
			BSTNode right = root.getRightChild();
			//System.out.println("Root is " + root.getCourseCode());

			//If left or right == null, add node
			
			if (node.getCourseCode().compareTo(currentKey) < 0) { // left string "before" right string BUGGEN
				if (left == null) {
					root.setLeftChild(node);
					//System.out.println("Placed it " + node.getCourseCode());
					//size++;
				} else {
					//System.out.println("Still looking " + node.getCourseCode());
					left = insert(left, node);
					}
			} else if (node.getCourseCode().compareTo(currentKey) > 0) { // left string "after" right string
				if (right == null) {
					root.setRightChild(node);
					//System.out.println("Placed it " + node.getCourseCode());
					//size++;
				} else {
					//System.out.println("Still looking " + node.getCourseCode());
					left = insert(right, node);
					}
			}
			return root;
		}
	}


	/**
	 * size: Count the number of nodes in the search tree
	 */

	public int size() {
		return size;
	}

	/**
	 * find: Find a course given a course code
	 */

	public BSTNode find(String courseCode) {
		return findRecursive(root, courseCode);
	}
	
	private BSTNode findRecursive(BSTNode current, String value) {
		//System.out.println(current.getCourseCode());
		if (current == null) {
			System.out.println("I'm null");
			return null;
			
		} else {
			if(current.getCourseCode().compareTo(value) == 0) {
				//System.out.println("I'm Done");
				return current;
				
			} else if(current.getCourseCode().compareTo(value) > 0) {
				//System.out.println(current.getCourseCode().compareTo(value));
				//System.out.println("I'm left..");
				return findRecursive(current.getLeftChild(), value);
				
			} else if(current.getCourseCode().compareTo(value) < 0) {
				//System.out.println(current.getCourseCode().compareTo(value));
				//System.out.println("I'm right..");
				return findRecursive(current.getRightChild(), value);
			} else {
				return null;
			} 
			//return null;
		}
	}
	
	//ITERATOR INTERFACE
    public Iterator<BSTNode> iterator() {
        return new BSTIterator();
    }

    //ITERATOR
	private class BSTIterator implements Iterator<BSTNode> {

		//private BSTNode root = null;
		private Stack<BSTNode> stack;
			

		public BSTIterator() {
			stack = new Stack<>();
			pushAll(root);
		}
		
		private void pushAll(BSTNode root) {
			while(root!=null) {
				stack.push(root);
				root = root.getLeftChild();
			}
		}
		
		public boolean hasNext() {
			return !stack.isEmpty();
			}
		
		public BSTNode next() {
			BSTNode root = stack.pop();
			pushAll(root.getRightChild());
			return root;
		}
	
	}
}


	/**
	 * Nodes in the search tree
	 * This class should be sufficient for the project.
	 *
	 */
	
