package datastructures.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashTable {
	/*
	 * Important Facts About HashTable
	 * 
	 * Load Factoring
	 * 		http://javabypatel.blogspot.com/2015/10/what-is-load-factor-and-rehashing-in-hashmap.html
	 * 
	 * Get/ Put/ Delete - O(1) --> in well formed hash tables
	 * In bad cases can go up to O(N)
	 * 
	 */
	
	private List<DataItem>[] hashArray;
	private int arraySize;
	private int hashTableCount;
	private static double LOAD_FACTOR= 0.75;
	
	
	public HashTable(int n) {
		
		arraySize = n;
		hashTableCount = 0;
		hashArray= new ArrayList[n];
		for(int i =0; i< n; i++) {
			hashArray[i] = new ArrayList<DataItem>();
		}
		
	}
	
	public void put(int key, int value) {
		int hashKey = hashFunc(key);
		hashArray[hashKey].add(new DataItem(key, value));	
		hashTableCount++;
	}
	
	public Integer get(int key){
		int hashKey= hashFunc(key);
		for(DataItem item : hashArray[hashKey]) {
			if(key == item.key) {
				return item.data;
			}
		}
		return null;
	}
	
	public boolean delete(int key) {
		int hashKey = hashFunc(key);
		for(int i = 0; i < hashArray[hashKey].size(); i++) {
			if(hashArray[hashKey].get(i).key== key) {
				hashArray[hashKey].remove(i);
				hashTableCount--;
				return true;
			}
		}
		return false;
	}
	
	public int hashFunc(int key) {
		return key%arraySize;
	}
	
	
	

}

class DataItem{
	public int key;
	public int data;
	public DataItem(int key, int data) {
		this.key=key;
		this.data=data;
	}
}