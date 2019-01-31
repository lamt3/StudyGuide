package datastructures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TomBSTPractice {

	public TomTreeNode root; 
	
	public TomBSTPractice(int data){
		this.root = new TomTreeNode(data);
	}
	
	public boolean find(int data){
		TomTreeNode temp = this.root;
		if(temp == null){
			return false;
		}
		
		while(temp != null){
			if(temp.data == data){
				return true;
			}
			
			if(temp.data > data){
				temp = temp.left;
			}else{
				temp = temp.right;
			}
			
		}
		
		return false;
	}
	
	public boolean findRec(int data){
		return findRecHelper(data, this.root);
	}
	
	public boolean findRecHelper(int data, TomTreeNode head){
		if(head == null){
			return false;
		} 
		if(head.data == data){
			return true;
		}
		
		if(head.data> data){
			findRecHelper(data, head.left);
		}
		return findRecHelper(data, head.right);
	}
	
	public void insert(int data){
		TomTreeNode temp = this.root;
		if (temp == null){
			this.root = new TomTreeNode(data);
		}
		TomTreeNode parent = null;
		
		while(temp != null){
			parent = temp;
			if(temp.data > data){
				temp = temp.left;
				if(temp == null){
					parent.left = new TomTreeNode(data);
				}
			}else{
				temp = temp.right;
				if(temp == null){
					parent.right = new TomTreeNode(data);
				}
			}
		}
	}
	

	public void deleteKeyRec(int key){
		deleteKeyRec(key, this.root);
	}
	
	public TomTreeNode deleteKeyRec(int key, TomTreeNode head){
		if(head == null){
			return head;
		}
		
		if(head.data > key){
			head.left = deleteKeyRec(key, head.left);
		}else if(head.data < key){
			head.right = deleteKeyRec(key, head.right);
		}else{
			
			if(head.left == null){
				return head.right;
			}else if(head.right == null){
				return head.left;
			}else{
				head.data = findMinimumKey(head.right);
				head.right = deleteKeyRec(head.data, head.right);					
			}
		}
		
		return head;
	}
	
	
	public int findMinimumKey(TomTreeNode current){
		while(current.left != null){
			current = current.left; 
		}
		return current.data;
	}
	
	
	public List<Integer> getLevelOrderTraversal(){
		return getLevelOrderTraversal(this.root);
	}
	
	public List<Integer> getLevelOrderTraversal(TomTreeNode head){
		List<Integer> nodeList = new ArrayList<Integer>();
		Queue<TomTreeNode> q = new LinkedList<TomTreeNode>();
		q.add(head);
		while(!q.isEmpty()){
			TomTreeNode currentNode = q.remove();
			nodeList.add(currentNode.data);
			if(currentNode.left != null){
				q.add(currentNode.left);
			}
			if(currentNode.right != null){
				q.add(currentNode.right);
			}
			
		}
		return nodeList;
	}
	
	public static void main(String[] args){
		TomBSTPractice a = new TomBSTPractice(1);
		a.root.left = new TomTreeNode(2);
		a.root.right =  new TomTreeNode(3);
		a.root.left.left=  new TomTreeNode(4);
		a.root.left.right =  new TomTreeNode(5);
//		
		BSTAlgorithms.deleteKLessPath(8, a);
		
		List<Integer>c = a.getLevelOrderTraversal();
		for(int i : c){
			System.out.println(i);
		}
		
	}
	
	
}

