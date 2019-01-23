package datastructures.lists;

public class TomLinkedList {
	public TomNode head;
	
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
	
	
	public void addAtHead(Object data) {
		TomNode tempNode = this.head;
		TomNode nodeToAdd = new TomNode(data);
		this.head = nodeToAdd;
		nodeToAdd.next = tempNode;
	}
	
	public void addAtTail(Object data){
		if(head == null){
			head = new TomNode(data);
		}else{
			TomNode temp = head;
			while(temp.next != null){
				temp = temp.next;
			}
			temp.next = new TomNode(data);
			//this.head = temp;
		}
	}

	public void addAtIndex(Object data, int index){
		TomNode temp = this.head; 
		
		for(int i=0;i<index-1 && temp.next!=null;i++){
			temp = temp.next;
			
		}
		TomNode holder = temp.next;
		TomNode newNode = new TomNode(data);
		newNode.next = holder;
		temp.next = newNode;
		this.head=temp;	
	}
	
	public void deleteAtIndex(int index){
		TomNode temp = this.head;
		for(int i=0; i< index-1; i++){
			temp = temp.next;
		}
		TomNode holder = temp.next.next;
		temp.next = holder;
		this.head = temp;
	}
	
}
