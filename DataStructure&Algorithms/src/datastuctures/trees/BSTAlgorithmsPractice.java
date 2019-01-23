package datastuctures.trees;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class BSTAlgorithmsPractice {
	
	static class Index{
		int index;
	}


	private static final Index Index = new Index();

	public static boolean completeMatch(TomTreeNode a, TomTreeNode b){
		if (a == null && b == null){
			return true;
		}
		
		if(a == null || b == null){
			return false; 
		}
		
		if(a.data == b.data){
			return (completeMatch(a.left, b.left) && completeMatch(a.right, b.right));
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
			return completeMatch(bigTreeRoot, smallTreeRoot);
		}
		return (checkIfSubTree(bigTreeRoot.left, smallTreeRoot) || checkIfSubTree(bigTreeRoot.right, smallTreeRoot));
		
	}
	
	
	/*Build Tree from prorder and inorder list*/
	public static TomTreeNode buildTree(int[] preorder, int[] inorder) {
		
		Set<TomTreeNode> set = new HashSet<>();
		Stack<TomTreeNode> stack = new Stack<>();
		
		TomTreeNode root = null;
		
		for(int pre = 0, in = 0; pre < preorder.length;) {
			TomTreeNode node = null;
			
			do {
				node = new TomTreeNode(preorder[pre]);
				if(root == null) {
					root = node;
				}
				if(!stack.isEmpty()) {
					if(set.contains(stack.peek())) {
						set.remove(stack.peek());
						stack.pop().right = node;
					}else {
						stack.peek().left = node;
					}
				}
				stack.push(node);
				
			}while(preorder[pre++] != inorder[in] && pre < preorder.length);
			
			node = null;
			while(!stack.isEmpty() && in < inorder.length && stack.peek() .data == inorder[in]) {
				node = stack.pop();
				in++;
			}
			if(node != null) {
				set.add(node);
				stack.push(node);
			}
			
			
			
		}
		return root;
		
	}
	
	/*Build Tree from prorder and inorder list RECURSIVE*/
	public static int preOrderIndex = 0;
	public static TomTreeNode makeBTree(int[] inOrder, int[] preOrder, int iStart, int iEnd) {
		
		if(iStart > iEnd) {
			return null;
		}
		
		TomTreeNode root = new TomTreeNode(preOrder[preOrderIndex]);
		preOrderIndex ++;
		
		if(iStart == iEnd) {
			return root;
		}
		
		int index = getInOrderIndex(inOrder, iStart, iEnd, root.data);
		root.left = makeBTree(inOrder, preOrder, iStart, index -1);
		root.right = makeBTree(inOrder, preOrder, index+1, iEnd);
		return root;
	}
	
	public static int getInOrderIndex(int[] inOrder, int start, int end, int data) {
		for(int i = start; i <= end; i++) {
			if(inOrder[i] == data) {
				return i;
			}
		}
		return -1;
	}
	
	
	/*Build Tree from postorder and inorder list RECURSIVE*/
//	public static int postOrderIndex = 0;
	
	
	public static TomTreeNode makeBTreeFromPostOrderAndInOrder(int[] inOrder, int[] postOrder, int iStart, int iEnd, Index postIndex) {
		
		if(iStart > iEnd) {
			return null;
		}
		
		TomTreeNode root = new TomTreeNode(postOrder[postIndex.index]);
		postIndex.index --;
		
		if(iStart == iEnd) {
			return root;
		}
		
		int index = getInOrderIndex(inOrder, iStart, iEnd, root.data);
		root.right = makeBTreeFromPostOrderAndInOrder(inOrder, postOrder, index+1, iEnd, postIndex);
		root.left = makeBTreeFromPostOrderAndInOrder(inOrder, postOrder, iStart, index -1, postIndex);
		return root;
	}
	
	/*Find max width of binary tree with null allowed*/
	public static int widthOfBinaryTree(TomTreeNode node) {
		Queue<AnnotatedTree> q = new LinkedList<AnnotatedTree>();
		q.add(new AnnotatedTree(node, 0, 0));
		int currDepth = 0;
		int left = 0;
		int ans=0;
		
		while(!q.isEmpty()) {
			AnnotatedTree currNode = q.poll();
			if(currNode != null) {
				
				q.add(new AnnotatedTree(currNode.node.left, currNode.depth + 1, currNode.pos * 2));
				q.add(new AnnotatedTree(currNode.node.right, currNode.depth + 1, currNode.pos * 2 + 1));
				
				if(currDepth != currNode.depth) {
					currDepth = currNode.depth;
					left = currNode.pos;
				}
				
				ans = Math.max(ans, currNode.pos - left + 1);
				
				
				
			}
		}
		return ans;
		
	}
	
	public static int maxWidth(TomTreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int maxWidth = 0;
		
		Queue<TomTreeNode> q = new LinkedList<>();
		q.add(root);
		
		while(!q.isEmpty()) {
			int count = q.size();
			maxWidth = Math.max(maxWidth, count);
			
			while(count-- > 0) {
				TomTreeNode temp = q.remove();
				if(temp.left != null) {
					q.add(temp.left);
				}
				if(temp.right != null) {
					q.add(temp.right);
				}
			}
			
		}
		return maxWidth;
		
	}
	
	/*
	 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.
	 */
	
	public TomTreeNode constructMaximumBinaryTree(int[] nums) {
        // int index = findMaxIndex(nums, 0, nums.length-1);
        // TreeNode root = new TreeNode(nums[index]);
        // root.left = constructTree(nums, 0, index-1);
        // root.right = constructTree(nums, index+1, nums.length-1);
        return constructTree(nums, 0, nums.length -1); 
    }
    
    public int findMaxIndex(int[] nums, int start, int end){
        int max = nums[start];
        int maxIndex = start; 
        for(int i = start; i <= end; i++){
            if(nums[i] > max){
                max = nums[i];
                maxIndex = i;
            }
        }
        return maxIndex; 
    }
    
    public TomTreeNode constructTree(int[] nums, int start, int end){
        if (start > end){
            return null;
        }
        
        int index = findMaxIndex(nums, start, end);
        System.out.println(index);
        TomTreeNode root = new TomTreeNode(nums[index]);
        root.left = constructTree(nums, start, index-1);
        root.right = constructTree(nums, index+1, end);
        return root;
        
        
        
    }
	

	
	
	public static void main(String[] args) {
		
//		BST MAKE BTREE FROM POST ORDER AND INORDER 
//		int [] inOrder = new int[] { 4, 8, 2, 5, 1, 6, 3, 7 };
//		 int post[] = new int[] { 8, 4, 5, 2, 6, 7, 3, 1 }; 
//		 Index ind = BSTAlgorithmsPractice.Index;
//		 ind.index = inOrder.length-1;
//		
//		BSTAlgorithmsPractice.makeBTreeFromPostOrderAndInOrder(inOrder, post, 0, inOrder.length-1, ind);
		
		TomTreeNode node = new TomTreeNode(1);
		node.left = new TomTreeNode(3);
		node.right = new TomTreeNode(2);
		node.left.left = new TomTreeNode(5);
		node.left.right = new TomTreeNode(3);
		node.right.left = new TomTreeNode(10);
		node.right.right = new TomTreeNode(9);
		int a = BSTAlgorithmsPractice.maxWidth(node);
		

		
		
	}
	
	
	
}
