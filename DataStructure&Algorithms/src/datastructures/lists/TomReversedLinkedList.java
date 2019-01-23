package datastructures.lists;

public class TomReversedLinkedList {

	public TomNode head = null;
	
	public void createLinkedList(int n){
		int i = 1;
		TomNode temp = null;
		while (i < n){
			if(head == null){
				head = new TomNode(i);
				temp = head;
			}else{
				temp.next = new TomNode(i);
				temp = temp.next;
			}
		
			i++;
		}
		
	}
	
	public void reverseLinkedList(){
		
		TomNode prev = null; 
		TomNode curr = null;
		TomNode next = head;
		
		while(next != null){
			curr = next;
			next = next.next;
			curr.next = prev; 
			prev = curr; 
		}
		head = curr;
		
	}
	
	public void reverseLinkedListRecur(){
		reverseLinkedListRecur(head);
	}
	
	private void reverseLinkedListRecur(TomNode curr){
		if(curr == null){
			return;
		}
		if(curr.next == null){
			this.head= curr;
			return;
		}
		
		reverseLinkedListRecur(curr.next);
		curr.next.next = curr;
		curr.next = null;
	}
	
	public static void main(String[] args){
		TomReversedLinkedList rll = new TomReversedLinkedList();
		rll.createLinkedList(5);
		rll.reverseLinkedListRecur();
		while(rll.head != null){
			System.out.println(rll.head.data);
			rll.head = rll.head.next;
		}
		
		
	}

	
	
	
	
}
