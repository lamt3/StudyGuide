package datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BSTAlgorithms {

	public static boolean completeMatch(TomTreeNode a, TomTreeNode b){
		if(a == null && b==null){
			return true;
		}
		if(a == null || b == null){
			return false;
		}
		if(a.data == b.data){
			return (completeMatch(a.right, b.right) && completeMatch(a.left, b.left));
		}
		return false;
	}
	
	public static boolean checkIfSubTree(TomTreeNode bigTreeRoot, TomTreeNode smallTreeRoot){
		if(smallTreeRoot == null){
			return true;
		}
		if(bigTreeRoot == null){
			return false; 
		}
		
		if(bigTreeRoot.data == smallTreeRoot.data){
			return BSTAlgorithms.completeMatch(bigTreeRoot, smallTreeRoot);
		}
		return(checkIfSubTree(bigTreeRoot.left, smallTreeRoot) || checkIfSubTree(bigTreeRoot.right, smallTreeRoot));
		
	}
	
	public static void deleteKLessPath(int k, TomBSTPractice bst){
		int sum[] = new int[1];
		deleteKLessPath(bst.root, sum, k);
		if(sum[0] < k){
			bst.root = null;
		}
	}
	
	public static TomTreeNode deleteKLessPath(TomTreeNode node, int[] sum, int k){
		if(node == null){
			return null;
		}
		
		int[] ls = new int[1];
		int[] rs = new int[1];
		ls[0] = rs[0] = sum[0] + node.data;
		node.left = deleteKLessPath(node.left, ls, k);
		node.right = deleteKLessPath(node.right, rs, k);
		
		sum[0] = ls[0] > rs[0] ? ls[0]: rs[0];
		if(sum[0] < k){
			node = null;
		}
		return node;
	}
	
	public static boolean isSymmetric(TomTreeNode a, TomTreeNode b){
		if(a == null && b== null){
			return true;
		}
		
		if(a == null || b == null){
			return false;
		}
		
		if(a.data == b.data){
			if(isSymmetric(a.left, b.right)){
				return isSymmetric(a.right, b.left);
			}
		}
		return false;
	}
	
	public static void connectNodesAtSameLevel(TomTreeNode node) {
		
		node.nextRight = null;
		
		
		
		
	}
	
	
	public static void reverseAlternateLvels(TomTreeNode node) {
		
		
		Queue<TomTreeNode> qL = new LinkedList<TomTreeNode>(); 
		Stack<TomTreeNode> sR = new Stack<TomTreeNode>();
		
		qL.add(node);
		sR.push(node);
		int width = 0;
		boolean isInvert = true; 
		
		while(!qL.isEmpty()) {
			width = qL.size();
			
			while(width-->0) {
				
				TomTreeNode currL = qL.poll();
				TomTreeNode currR = sR.pop();
				
				System.out.println("current Left : " + currL.data);
				System.out.println("current Right : " + currR.data);
				
				if(isInvert) {
					
					currL.left = new TomTreeNode(currR.right.data);
					currL.right=new TomTreeNode(currR.left.data);
					
					if(currR.left != null) {
						qL.add(currL.left);
						sR.push(currL.left);
					}
					if(currR.right != null) {
						qL.add(currL.right);
						sR.push(currL.right);	
					}
				}
					
				else {
					
					currL.left=new TomTreeNode(currR.left.data);
					currL.right= new TomTreeNode(currR.right.data);
					if(currR.left != null) {
						qL.add(currL.left);
						sR.push(currL.left);
					}
					if(currR.right != null) {
						qL.add(currL.right);
						sR.push(currL.right);	
					}
				
					
				}
				
				
			}
			isInvert = isInvert==false? true:false;
			
			
			
		}
		
		
		
		
	}
	
	public static void main(String[] args) {
		
		TomTreeNode node = new TomTreeNode(0);
		node.left=new TomTreeNode(1);
		node.right = new TomTreeNode(2);
		
		node.left.left = new TomTreeNode(3);
		node.left.right = new TomTreeNode(4);
		
		node.right.left = new TomTreeNode(5);
		node.right.right = new TomTreeNode(6);
		
		node.left.left.left= new TomTreeNode(7);
		node.left.left.right = new TomTreeNode(8);
		
		node.left.right.left= new TomTreeNode(9);
		node.left.right.right = new TomTreeNode(10);
		
		node.right.left.left = new TomTreeNode(11);
		node.right.left.right = new TomTreeNode(12);
		
		node.right.right.left = new TomTreeNode(13);
		node.right.right.right = new TomTreeNode(14);
		
		BSTAlgorithms.reverseAlternateLvels(node);
		System.out.println(node);
	}

}
