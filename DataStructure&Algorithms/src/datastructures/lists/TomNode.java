package datastructures.lists;

public class TomNode {
	TomNode next;
	Object data;
	
	public TomNode(Object data){
		this.data = data;
	}
	
	public TomNode(TomNode temp){
		this.data = temp.data;
		this.next = temp.next;
	}
	
}
