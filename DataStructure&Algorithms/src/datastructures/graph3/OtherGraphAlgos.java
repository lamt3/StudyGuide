package datastructures.graph3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OtherGraphAlgos {

	public Map<Integer, List<Integer>> constructGraphFromInDegrees(int[] degrees) {
		Map<Integer, List<Integer>> g = new HashMap<Integer, List<Integer>>();
		
		List<Element> e  = constructOrderedList(degrees);
		
		
		for (int i = 0; i < e.size(); i++) {

			
			int index = e.get(i).index;
			if (!g.containsKey(e.get(i).index)) {
				g.put(index, new ArrayList<Integer>());
			}
			

			for (int j = i+1; j < degrees.length; j++) {
				int secondIndex = e.get(j).index;
				if(degrees[index]==0) {
					break;
				}
				
				
				
				if (!g.get(index).contains(secondIndex) && degrees[index] <= degrees[secondIndex] && degrees[secondIndex] != 0) {
					g.get(index).add(secondIndex);
					if (!g.containsKey(secondIndex)) {
						g.put(secondIndex, new ArrayList<Integer>());
					}
					g.get(secondIndex).add(index);
					degrees[index] = degrees[index] - 1;
					degrees[secondIndex] = degrees[secondIndex] - 1;
				}

			}

		}

		return g;
	}
	
	class Element implements Comparable<Element>{
		int index, value;

	    Element(int index, int value){
	        this.index = index;
	        this.value = value;
	    }

	    public int compareTo(Element e) {
	        return this.value - e.value;
	    }
	}
	
	private List<Element> constructOrderedList(int[] degrees){
		List<Element> elements = new ArrayList<Element>();
		for(int i=0; i<degrees.length; i++) {
			 elements.add(new Element(i, degrees[i]));
		}
		Collections.sort(elements);
		Collections.reverse(elements); 
		return elements;
	}
	
	public void findDigits(int[][] board, int i, int j, String num, Set<String> answer) {
		if(num.length() == 7) {
			answer.add(num);
			return;
		}
		
		if(num.length() > 1 && num.charAt(1) == '3') {
			System.out.println(num);
		}
		
		if(i + 1 <board.length && i + 1 >=0) {
			findDigits(board, i + 1, j, num+board[i+1][j], answer);
		}
		if(i - 1 <board.length && i - 1 >=0) {
			findDigits(board, i - 1, j, num+board[i-1][j], answer);
		}
		if(j + 1 <board[0].length && j + 1 >=0) {
			findDigits(board, i, j + 1, num+board[i][j+1], answer);
		}
		if(j - 1 <board[0].length && j - 1 >=0) {
			findDigits(board, i , j - 1, num+board[i][j-1], answer);
		}
	}

	public static void main(String[] args) {
//		int[] degrees = new int[] { 2, 2, 1, 1, 2 };
//		int[] degrees = new int[] {4,1,2,2,2,2,2,1 };
		OtherGraphAlgos a = new OtherGraphAlgos();
//		a.constructGraphFromInDegrees(degrees);
		int count = 1;
		int[][] board = new int[3][3];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				board[i][j]=count;
				count++;
			}
		}
		Set<String>answer = new HashSet<String>();
		a.findDigits(board, 1, 0, "4", answer);
		System.out.println(answer.size());
//		for(String b : answer) {
//			System.out.println(b);
//		}
		
	}

}
