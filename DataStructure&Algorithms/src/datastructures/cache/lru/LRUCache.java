package datastructures.cache.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	Map<Integer, Node> cacheMap;
	DoublyLinkedList cacheList;
	int cacheSize;

	public LRUCache(int cacheSize) {
		this.cacheSize = cacheSize;
		cacheMap = new HashMap<>(cacheSize);
		cacheList = new DoublyLinkedList(cacheSize);
	}

	public Node getPage(int pageNumber) {
		Node page = null;

		if (cacheMap.containsKey(pageNumber)) {
			page = cacheMap.get(pageNumber);
			cacheList.moveToHead(page);
		} else {
			if (cacheList.getCacheSize() == cacheList.getCurrentSize()) {
				int pageToRomeve = cacheList.removeLast();
				cacheMap.remove(pageToRomeve);
			}
			page = cacheList.addFist(pageNumber);
			cacheMap.put(pageNumber, page);
		}
		return page;
	}

	public void printCacheState() {
		cacheList.printList();
		System.out.println();
	}
}
