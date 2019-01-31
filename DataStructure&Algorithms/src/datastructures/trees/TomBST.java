package datastructures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TomBST {
	
	private TomTreeNode head; 
	
	public TomBST(int data){
		this.head = new TomTreeNode(data);
	}
	
	
	public boolean find(int data){
		TomTreeNode temp = this.head;
		while(temp != null){
			if(temp.data==data){
				return true;
			}else if(data >= temp.data){
				temp = temp.left; 
			}else{
				temp = temp.right;
			}
			
		}
		return false;
	}
		
	
	public boolean findRecursive(int data){
		TomTreeNode root = this.head;
		return findRecursiveHelper(root, data);
	}
	
	private boolean findRecursiveHelper(TomTreeNode root, int data){
		if(root == null){
			return false;
		}
		
		if(root.data == data){
			return true;
		}
		
		if(data > root.data){
			return findRecursiveHelper(root.left, data);
		}
		return findRecursiveHelper(root.right, data);
	}
	
// _______________________________________________________________________________________________________________		
	public void insertNonRec(int data){
		TomTreeNode nodeToInsert = new TomTreeNode(data);
		if(this.head==null){
			this.head = nodeToInsert;
			return;
		}
		
		TomTreeNode parent = null;
		TomTreeNode current = this.head; 
		while(true){
			parent = current;
			if(data <= current.data){
				current = current.left;
				if(current == null){
					parent.left = nodeToInsert;
					return;
				}
			}else{
				current = current.right;
				if(current == null){
					parent.right = nodeToInsert;
					return;
				}
			}
			
			
			
		}
		
	}
	
	public void insertRecursive(int data){
		this.head = insertRecHelper(this.head, data);
	}
	
	private TomTreeNode insertRecHelper(TomTreeNode root, int data){
		TomTreeNode nodeToInsert = new TomTreeNode(data);
		if(root == null){
			root = nodeToInsert;
			return root;
		}
		
		if(data < root.data){
			insertRecHelper(root.left, data);
		}else{
			insertRecHelper(root.right, data);
		}
		return root;
	}
// _______________________________________________________________________________________________________________	
	public TomTreeNode deleteKey(int data, TomTreeNode root){
		TomTreeNode parent = null;
		TomTreeNode current = root;
		
		//Search if data exists in BST 
		while(current != null && current.data != data){
			parent = current; 
			if(data > current.data){
				current = current.right;
			}else{
				current = current.left; 
			}
			
		}
		//Return false if key is not found in BST
		if(current == null){
			return root; 
		}
		
		//Case 1: Node to be deleted has no children (It's a leaf node) 
		if(current.left == null && current.right == null){
			//If current is not the root node... 
			if(current != this.head){
				if(parent.left == current){
					parent.left = null;
				}else{
					parent.right = null;
				}
			}
			// Only root in this BST so delete the root
			else{
				root = null;
			}
			
		}
		
		//Case 2: Node to be deleted has 2 children
		else if(current.left != null && current.right != null){
			//Find the in order successor node --> this successor node will replace the current node to be deleted
			TomTreeNode successorNode = findMinimumKey(current.right);
			//Recursively traverse tree again to delete successor node from original position; 
			deleteKey(successorNode.data, root);
			//Set current to be successorNode
			current = successorNode; 
		}
		
		//Case 3: Node to be deleted has only one child
		else{
			TomTreeNode child = current.left != null ? current.left: current.right; 
			
			//if node to be deleted is not a root node, then set its parent to its child
			if(current != root){
				if(current== parent.left){
					parent.left = child;
				}else{
					parent.right = child;
				}
			}else{
				root = child;
			}
			
		}
		return root; 
	}
	
	public void deleteKeyRec(int key){
		this.head = deleteKeyRec(key, this.head);		
	}
	
	public TomTreeNode deleteKeyRec(int key, TomTreeNode head){
		if(head == null){
			return head;
		}
		System.out.println(head.data);
		if(head.data > key){
			head.left = deleteKeyRec(key, head.left);
		}
		else if(head.data < key){
			head.right = deleteKeyRec(key, head.right);
		}
		//if key same as root then this is the node to be deleted
		else{
			
			// if node with only one or no child
			if(head.left == null){
				return head.right;
			}else if(head.right == null){
				return head.left;
			}else{
				// node with 2 children --> get in order successor (smallest in the right subtree)
				head= findMinimumKey(head.right);
				
				head.right = deleteKeyRec(head.data, head.right);
			}
		}
		return head;
	}
	
	
	
	//Helper function to find smallest value in right subtree 
/*	find a minimum value in the right subtree;	
 * 	replace value of the node to be removed with found minimum. Now, right subtree contains a duplicate!
 * 	apply remove to the right subtree to remove a duplicate.
 */
	public TomTreeNode findMinimumKey(TomTreeNode current){
		while(current.left != null){
			current = current.left;
		}
		return current;
	}
	
// _______________________________________________________________________________________________________________	
	//Check if Tree IS A BST
	//Run Time O(N)
	// https://www.ideserve.co.in/learn/check-if-a-binary-tree-is-a-binary-search-tree
	public boolean isBST(TomTreeNode root){
		return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public boolean isBSTUtil(TomTreeNode node, int min, int max){
		//An empty tree is a BST
		if(node == null){
			return true;
		}
		
		if(node.data < min || node.data > max){
			return false;
		}
		return (isBSTUtil(node.left, min, node.data) && isBSTUtil(node.right, node.data, max));
	}
	
// _______________________________________________________________________________________________________________
//Check if Two Nodes are cousins 
 /* Two nodes are cousins if: 
	1: they are not siblings (children of same parent).
	2: they are on the same level.
  */
	
	public boolean isCousins(TomTreeNode a, TomTreeNode b){
		if(a==b){
			return false; 
		}
		 return (!isSibling(a, b) && getLevel(a) == getLevel(b));
		
	}
	public int getLevel(TomTreeNode a){
		return getLevel(head, a, 1);
	}
	
	private int getLevel(TomTreeNode root, TomTreeNode a, int currentLevel){
		if(root == null){
			return 0;
		}
		if(root == a){
			return currentLevel; 
		}
		int level = getLevel(root.left, a, currentLevel+1);
		if(level != 0){
			return level;
		}else{
			//if it comes here didn't find the node in the left subtree so look in the right subtree
			return getLevel(root.right, a, currentLevel + 1);
		}
	}
	
	public boolean isSibling(TomTreeNode a, TomTreeNode b){
		return isSibling(head, a, b);
	}

	private boolean isSibling(TomTreeNode root, TomTreeNode a, TomTreeNode b){
		if(root == null){
			return false; 
		}
		return((root.left == a && root.right == b) || (root.right ==a && root.left == b) ||
				isSibling(root.left, a, b) || isSibling(root.right,a,b));
		
		
	}

	
// _______________________________________________________________________________________________________________	
	//LEVEL ORDER TRAVERSEL 
	
/*
 * Level Order Traversal of a Binary Tree

    1. Create a queue and add root to the queue.
    2. Till the queue is not empty, repeat the following steps (3-5):
    3. Get a node from the queue and print it
    4. If the node has a left child, add the left child to the queue.
    5. If the node has a right child, add the right child to the queue.
 */
	
	public List<Integer> getLevelOrderTraversal(){
		return getLevelOrderTraversal(head);
	}
	
	private List<Integer> getLevelOrderTraversal(TomTreeNode root){
		List<Integer> treeValues = new ArrayList<Integer>();

		if(root == null){
			return treeValues; 	
		}
		Queue<TomTreeNode> queue = new LinkedList<TomTreeNode>();
		queue.add(root);
		while(!queue.isEmpty()){
			TomTreeNode current = queue.remove();
			treeValues.add(current.data);
			if(current.left != null){
				queue.add(current.left);
			}
			if(current.right != null){
				queue.add(current.right);
			}
		}
		return treeValues;
	}
	
	
//-----------------------------------------------------------------------------------------------------------------------------
	/*
	 * A binary tree is called a height balanced binary tree if it satisfies following conditions -  
		1. If at any given node, absolute difference of height of left sub-tree and height of right sub-tree is not greater than 1.
		2. For any given node, left sub-tree and right sub-tree that node are balanced binary trees themselves. 
	 */
	
	public boolean checkIfBalanced(TomTreeNode node){
		int leftHeight;
		int rightHeight;
		
		if(node == null){
			return true;
		}
		
		leftHeight = height(node.left);
		rightHeight = height(node.right);
		
		if(Math.abs(leftHeight - rightHeight) <=1 
				&& checkIfBalanced(node.left) && checkIfBalanced(node.right)){
			return true;
		}
		
		return false;
	}
	
	public int height(TomTreeNode node){
		if(node == null){
			return 0;
		}
		return 1+ Math.max(height(node.left), height(node.right));
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------------------
		/*PRE ORDER TRAVERSAL
		 * Visit node, vist left, visit right
		 * Can be recursive or iteratively
		 */
	
	public void preOrderRecursion(TomTreeNode node) {
		if(node == null) {
			return;
		}
		System.out.println("Node Data is: " + node.data);
		preOrderRecursion(node.left);
		preOrderRecursion(node.right);
	}
	
	public void preOrderIterative(TomTreeNode node) {
		if(node == null) {
			return;
		}
		
		Stack<TomTreeNode> stack = new Stack<TomTreeNode>();
		stack.push(node);
		
		
		while(stack != null) {
			
			TomTreeNode current = stack.pop();
			System.out.println("Node Data is: " + current.data);
			if(current.right != null) {
				stack.push(current.right);
			}
			if(current.left != null) {
				stack.push(current.left);
			}
			
		}
		
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------
			/*IN ORDER TRAVERSAL
			 * Visit left, root right
			 * Can be recursive or iteratively
			 * http://www.java67.com/2016/08/binary-tree-inorder-traversal-in-java.html
			 */
	
	public void inOrderRecursion(TomTreeNode node) {
		if(node == null) {
			return;
		}
		
		inOrderRecursion(node.left);
		System.out.println(node.data);
		inOrderRecursion(node.right);
	}
	
	public void inOrderIterative(TomTreeNode node) {
		if(node == null) {
			return;
		}
		
		Stack<TomTreeNode> s = new Stack<TomTreeNode>();
		TomTreeNode curr = node;
		
		while(curr != null || !s.isEmpty()) {
			
			while(curr!=null) {
				s.push(curr);
				curr = curr.left;
			}
			curr = s.pop();
			System.out.println(curr.data);
			curr = curr.right;
		}
		
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------
	/*POST ORDER TRAVERSAL
	 *  1. Traverse the left subtree, i.e., call Postorder(left-subtree)
   		2. Traverse the right subtree, i.e., call Postorder(right-subtree)
   		3. Visit the root.
   		Useful for when deleting nodes from tree
	 */
	
	public void postOrderRecursion(TomTreeNode node) {
		if(node == null) {
			return;
		}
		postOrderRecursion(node.left);
		postOrderRecursion(node.right);
		System.out.println(node.data);
		
	}
	
	public void postOrderIterative(TomTreeNode node) {
		if(node == null) {
			return;
		}
		Stack<TomTreeNode> s = new Stack<>();
		s.push(node);
	
		while(!s.isEmpty()) {
			TomTreeNode curr = s.peek();
			
			if(curr.left == null && curr.right == null) {
				TomTreeNode n = s.pop();
				System.out.println(n.data);
			}else {
				if(curr.right!= null) {
					s.push(curr.right);
					curr.right = null;
				}
				if(curr.left != null) {
					s.push(curr.left);
					curr.left=null;
				}
				
			}
	
			
			
		}
		
	}
	
	public static void main(String[] args) {
		TomBST c = new TomBST(5);
		c.head.left = new TomTreeNode(2);
		c.head.right = new TomTreeNode(19);
		
		c.insertNonRec(6);
		
	}
	
}
