package datastructures.lists;

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
//	
	public static void main(String[] args){
		TomLinkedList a = new TomLinkedList();
		a.addAtTail(1);
		a.addAtTail(2);
		a.addAtTail(38);
		
		TomLinkedList b = new TomLinkedList();
		b.addAtTail(4);
		b.addAtTail(9);
		b.addAtTail(13);
		b.addAtTail(13);
		
		
		TomLinkedList c = LinkedListAlgorithms.mergeTwoLinkedLists(a, b);
		
		while(c.head != null){
			System.out.println(c.head.data);
			c.head = c.head.next;
		}
		
		
	}
	
	
}
