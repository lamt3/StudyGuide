package datastructures.lists;

import java.util.ArrayList;
import java.util.List;

public class LinkedListAlgorithms {

	/*
	 * Input 2 Sorted Linked List
	 * Output: 1 Sorted Linked Linked List
	 */
	public static TomLinkedList mergeTwoLinkedLists(TomLinkedList a, TomLinkedList b){
		TomLinkedList mergedList = new TomLinkedList();
		
		TomNode temp1 = a.head;
		TomNode temp2 = b.head;
		
		if((int)temp1.data > (int)temp2.data){
			mergedList.addAtTail(temp2.data);
			temp2 = temp2.next;
			
		}else{
			mergedList.addAtTail(temp1.data);
			temp1 = temp1.next;
		}
		
		while(temp1 != null && temp2 != null){
			if((int)temp1.data > (int)temp2.data){
				mergedList.addAtTail(temp2.data);
				temp2 = temp2.next;
			}else{
				mergedList.addAtTail(temp1.data);
				temp1 = temp1.next;
			}
			
			
		}
		
		if (temp1 != null){
			while(temp1 != null){
				mergedList.addAtTail(temp1.data);
				temp1 = temp1.next;
			}
			
		}
		
		if (temp2 != null){
			while(temp2 != null){
				mergedList.addAtTail(temp2.data);
				temp2 = temp2.next;
			}
			
		}
		return mergedList;
	}
	
	public static TomNode rotate(TomNode head, int n) {
		TomNode temp = head;
		int count = 1;
		while(count <= n) {
			int val = (int) temp.data;
			temp = temp.next;
			TomNode temp2 = temp;
			while(temp2.next != null) {
				temp2=temp2.next;
			}
			temp2.next= new TomNode(val);
			count++;
		}
		return temp;
	}
	
	public static TomNode reverseByGroup(int n, TomNode head) {
		TomNode temp = head;
		List<TomNode> l = new ArrayList<TomNode>();
		while(temp != null) {
			int count = 1;
			TomNode temp2 = new TomNode(temp.data);
			TomNode temp3 = temp2; 
			while(count < n && temp.next != null) {
				temp2.next = new TomNode(temp.next.data);
				temp2 = temp2.next;
				temp = temp.next;
				count++;
			}
			temp = temp.next;
			l.add(temp3);
			
			
		}
		List<TomNode> reversedl = new ArrayList<TomNode>();
		for(TomNode node : l) {
			TomNode curr = null;
			TomNode prev = null;
			TomNode next = node;
			while(next != null) {
				curr = next;
				next = next.next;
				curr.next = prev;
				prev = curr;
			}
			reversedl.add(curr);
		}
		TomNode main = reversedl.get(0);
		for(int i = 1; i < reversedl.size(); i++) {
			TomNode neext = reversedl.get(i);
			TomNode tempp = main;
			while(tempp.next != null) {
				tempp = tempp.next;
			}
			tempp.next = neext;
			
			
		}
		
		return main;
		
	}
	
	
	
	
//	
	public static void main(String[] args){
//		TomLinkedList a = new TomLinkedList();
//		a.addAtTail(1);
//		a.addAtTail(2);
//		a.addAtTail(38);
//		
//		TomLinkedList b = new TomLinkedList();
//		b.addAtTail(4);
//		b.addAtTail(9);
//		b.addAtTail(13);
//		b.addAtTail(13);
//		
//		
//		TomLinkedList c = LinkedListAlgorithms.mergeTwoLinkedLists(a, b);
//		
//		while(c.head != null){
//			System.out.println(c.head.data);
//			c.head = c.head.next;
//		}
		TomNode head = new TomNode(1);
		head.next = new TomNode(2);
		head.next.next = new TomNode(3);
		head.next.next.next = new TomNode(4);
		head.next.next.next.next = new TomNode(5);
//		LinkedListAlgorithms.rotate(head, 3);
		LinkedListAlgorithms.reverseByGroup(2, head);
		
	}
	
	
}
