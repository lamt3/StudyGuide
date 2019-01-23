package datastuctures.trees;

import java.util.Map;
import java.util.TreeMap;

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

}
