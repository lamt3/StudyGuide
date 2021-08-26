package datastructures.cache.lru;

public class DoublyLinkedList {
	private final int cacheSize;
	private int currentSize;
	private Node head;
	private Node tail;

	public DoublyLinkedList(int size) {
		cacheSize = size;
		currentSize = 0;
		head = null;
		tail = null;
	}

	public Node addFist(int pageNumber) {
		Node newNode = new Node(pageNumber);

		if (head == null) {
			head = newNode;
			tail = newNode;
			currentSize = 1;
			return head;
		}
		currentSize++;
		newNode.setNext(head);
		head.setPrev(newNode);
		head = newNode;

		return head;
	}

	public int removeLast() {

		int pageNumber = tail.getPageNumber();

		tail = tail.getPrev();
		tail.setNext(null);
		currentSize--;

		return pageNumber;
	}

	public void moveToHead(Node page) {

		if (page == null || head == null || page == head)
			return;

		if (page == tail) {
			tail = tail.getPrev();
			tail.setNext(null);
		}

		Node next = page.getNext();
		Node prev = page.getPrev();

		prev.setNext(next);
		if (next != null)
			next.setPrev(prev);

		page.setPrev(null);
		page.setNext(head);
		head.setPrev(page);

		head = page;
	}

	public void printList() {
		if (head == null)
			return;

		Node temp = head;

		while (temp != null) {
			System.out.print(temp);
			temp = temp.getNext();
		}
	}

	public int getCacheSize() {
		return cacheSize;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public Node getHead() {
		return head;
	}

	public Node getTail() {
		return tail;
	}
}
