package Courseinfo;

public class BSTNode {
		private String courseCode;
		private String courseName;
		private double credits;
		private BSTNode left = null;
		private BSTNode right = null;

		/**
	 * Constructor
	 *
	 */
	public BSTNode(String code, String name, double credits) {
		this.courseCode = code;
		this.courseName = name;
		this.credits = credits;
	}

	/**
	 * Specify the children of the current node
	 * @param left
	 * @param right
	 */
	/**
	public void setChildren(BSTNode left, BSTNode right) {
		this.left = left;
		this.right = right;
	}
	**/
	
	public void setLeftChild(BSTNode node) {
		this.left = node;
	}
	
	public void setRightChild(BSTNode node) {
		this.right = node;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public double getCredits() {
		return credits;
	}

	public BSTNode getLeftChild() {
		return left;
	}

	public BSTNode getRightChild() {
		return right;
	}
	
}