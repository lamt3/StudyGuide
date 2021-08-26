package algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringAlgo2 {

	public static boolean isMatch(String str, String pat) {
		Map<Character, String> map = new HashMap<>();
		return isMatch(str, 0, pat, 0, map);
	}

	static boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map) {
		// base case
		if (i == str.length() && j == pat.length())
			return true;
		if (i == str.length() || j == pat.length())
			return false;

		// get current pattern character
		char c = pat.charAt(j);

		// if the pattern character exists
		if (map.containsKey(c)) {
			String s = map.get(c);

			// then check if we can use it to match str[i...i+s.length()]
			if (i + s.length() > str.length() || !str.substring(i, i + s.length()).equals(s)) {
				System.out.println(i + s.length() + " " + str.length());
				System.out.println(str.substring(i, i + s.length()) + " " + s);
				return false;
			}

			// if it can match, great, continue to match the rest
			return isMatch(str, i + s.length(), pat, j + 1, map);
		}

		// pattern character does not exist in the map
		for (int k = i; k < str.length(); k++) {
			// create or update the map
			map.put(c, str.substring(i, k + 1));

			// continue to match the rest
			if (isMatch(str, k + 1, pat, j + 1, map)) {
				return true;
			}
		}

		// we've tried our best but still no luck
		System.out.println("removing" + c);
		map.remove(c);

		return false;
	}

	public static List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList();
		backtrack(ans, "", 0, 0, n);
		return ans;
	}

	public static void backtrack(List<String> ans, String cur, int open, int close, int max) {
		if (cur.length() == max * 2) {
			ans.add(cur);
			return;
		}

		if (open < max)
			backtrack(ans, cur + "(", open + 1, close, max);
		if (close < open)
			backtrack(ans, cur + ")", open, close + 1, max);
	}

	static final int no_of_chars = 256;

	static String findSubString(String str, String pat) {
		int len1 = str.length();
		int len2 = pat.length();

		// check if string's length is less than pattern's
		// length. If yes then no such window can exist
		if (len1 < len2) {
			System.out.println("No such window exists");
			return "";
		}

		int hash_pat[] = new int[no_of_chars];
		int hash_str[] = new int[no_of_chars];

		// store occurrence ofs characters of pattern
		for (int i = 0; i < len2; i++)
			hash_pat[pat.charAt(i)]++;

		int start = 0, start_index = -1, min_len = Integer.MAX_VALUE;

		// start traversing the string
		int count = 0; // count of characters
		for (int j = 0; j < len1; j++) {
			// count occurrence of characters of string
			hash_str[str.charAt(j)]++;

			// If string's char matches with pattern's char
			// then increment count
			if (hash_pat[str.charAt(j)] != 0 && hash_str[str.charAt(j)] <= hash_pat[str.charAt(j)])
				count++;

			// if all the characters are matched
			if (count == len2) {
				// Try to minimize the window i.e., check if
				// any character is occurring more no. of times
				// than its occurrence in pattern, if yes
				// then remove it from starting and also remove
				// the useless characters.

				// hash_pat[str.charAt(start)] == 0 --> check to remove useless string in
				// sequence (i.e not in pattern)
				// (hash_str[str.charAt(start)] > hash_pat[str.charAt(start)] --> check to see
				// if char in string sequence occuring more than given pattern
				// This will push start value (slide window forward)

				while (hash_str[str.charAt(start)] > hash_pat[str.charAt(start)] || hash_pat[str.charAt(start)] == 0) {

					if (hash_str[str.charAt(start)] > hash_pat[str.charAt(start)])
						hash_str[str.charAt(start)]--;
					start++;
				}

				// update window size
				int len_window = j - start + 1;
				if (min_len > len_window) {
					min_len = len_window;
					start_index = start;
				}
			}
		}

		// If no window found
		if (start_index == -1) {
			System.out.println("No such window exists");
			return "";
		}

		// Return substring starting from start_index
		// and length min_len
		return str.substring(start_index, start_index + min_len);
	}

	private static class TrieNode {
		TrieNode[] next;
		int index;
		List<Integer> list;

		TrieNode() {
			next = new TrieNode[26];
			index = -1;
			list = new ArrayList<>();
		}
	}

	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> res = new ArrayList<>();

		TrieNode root = new TrieNode();

		for (int i = 0; i < words.length; i++) {
			addWord(root, words[i], i);
		}

		for (int i = 0; i < words.length; i++) {
			search(words, i, root, res);
		}

		return res;
	}

	private void addWord(TrieNode root, String word, int index) {
		for (int i = word.length() - 1; i >= 0; i--) {
			int j = word.charAt(i) - 'a';

			if (root.next[j] == null) {
				root.next[j] = new TrieNode();
			}

			if (isPalindrome(word, 0, i)) {
				root.list.add(index);
			}

			root = root.next[j];
		}

		root.list.add(index);
		root.index = index;
	}
	
	/*
	 * The point of each algorithm is to store each word backwards in a trie and try to find matches
	 * If you think about it a palindrome pair is a word combined with another word that's backwards of original word 
	 * ie. AB, BA --> ABBA 
	 * 
	 * Edge cases to consider: 
	 * 	1. ABA , BA --> words arent exact matches backwards --> this is why we store in trie root instances when rest of words are palindrome i.e AB[A] --> A is a palindrome
	 */
	
	private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
		for (int j = 0; j < words[i].length(); j++) {
			System.out.println("Current Word: " + words[i]);
			System.out.println("Root Index = " + root.index);
			
			/*
			 * First instance we check is when we traverse a current word before the word finishes we come across a word in the trie path
			 * In other words, current word is LONGER than the matching pair word (ABBA (current word) + BA (pair))
			 * This is used to check for instances such as: 
			 * Words [ABAA, BA]
			 * Current Word: ABAA
			 * TRIE ROOT TRAVERSED: AB (This is BA backwords since we store each word backwards in trie 
			 * So we need to check that:
			 * 		1. BA is a word 
			 * 		2. That the rest of the word AB{AA} is a palindrome --> which it is since AA is a palandrome 
			 * Means we can form a palindrome pair ABAABA 
			 */
			if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
				res.add(Arrays.asList(i, root.index));
			}

			root = root.next[words[i].charAt(j) - 'a'];
			if (root == null)
				return;
		}

		
		/*
		 * Second instance we check is when we finish traversing a current word but haven't come across a matching word in the trie path but there may be a palindrome we can form further in the trie path. 
		 * In other words, when current word is SHORTER than the pair word. 
		 * This is used to check for instances such as: 
		 * Words [ABAA, BA, AAB]
		 * Current Word: BA
		 * TRIE ROOT TRAVERSED: BA (This is AB backwords since we store each word backwards in trie 
		 * So we need to check that:
		 * 		1. AB is a word --> it is not a word in the list
		 * 		2. Then we need to check within this path is there palindrome in rest of word --> so far we matched AB(traversed trie) and BA(current word) 
		 * 		3. In trie path we see that there is the A (from AAB) which is a palidrnome (A by itself is a palidnrome so we add that to the list as pairs --> BA+AAB
		 * Means we can form a palindrome pair BAAAB
		 */
		
		for (int j : root.list) {
			if (i == j)
				continue;
			res.add(Arrays.asList(i, j));
		}
	}

	private boolean isPalindrome(String word, int i, int j) {
		while (i < j) {
			if (word.charAt(i++) != word.charAt(j--))
				return false;
		}

		return true;
	}
	
	
	public List<Integer> anagramSubStringSearch(String[] pat, String[] text){
		
		Map<String, Integer> charCountMap = new HashMap<String, Integer>();
		for(String s : pat) {
			charCountMap.put(s, charCountMap.getOrDefault(s, 0) + 1);
		}
		
		Map<String, Integer> visitedCountMap = new HashMap<String, Integer>();
		
		List<Integer> indexes = new ArrayList<Integer>();
		
		int pointer1=0;
		
		for(int i=0; i < text.length; i++) {
			
			if(!charCountMap.containsKey(text[i])) {
				if(pointer1 + 1 > text.length-1) {
					break;
				}else {
					pointer1 = i+1;
					visitedCountMap.clear();
				}
				
				
			}else if(!visitedCountMap.containsKey(text[i])) {
				visitedCountMap.put(text[i], 1);
				
				
				
			}
			
			else if(visitedCountMap.get(text[i]) + 1 > charCountMap.get(text[i])) {
				visitedCountMap.put(text[i], visitedCountMap.get(text[i]) + 1);
				while(visitedCountMap.get(text[i]) > charCountMap.get(text[i])) {
					String textToRemove = text[pointer1];
					visitedCountMap.put(textToRemove, visitedCountMap.get(textToRemove)-1);
					pointer1++;
				}	
				
			}else {
				
				
				
				
			}
			
			
		}
		
		return null;
		
		
		
		
		
	}
	
	
	
	
	

	public static void main(String[] args) {
		// StringAlgo2.isMatch("teblte", "aba");
		// StringAlgo2.generateParenthesis(3);

//		String str = "this is a test string";
//		String pat = "tist";
//		System.out.print("Smallest window is :\n " + findSubString(str, pat));
		
		String[] words = new String[] {"caa", "aa", "babaa","kraa"};
		StringAlgo2 s = new StringAlgo2();
 		s.palindromePairs(words);
 		
 		

	}

}
