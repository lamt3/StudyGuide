package algorithms.string;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class StringAlgorithmsPractice {

	public static String findLongestPalindrome(String input){
		
		int inputLength = input.length();
		boolean matrix[][] = new boolean[inputLength][inputLength];
		
		
		//Initialize all string size of 1 to true
		for(int i = 0; i < inputLength; ++i){
			matrix[i][i] = true;
		}
		int start = 0;
		int maxLength = 1;
		
		//Initialize all pairs of identical chars to be true; 
		for(int i = 0; i<inputLength - 1; ++i){
			if(input.charAt(i) == input.charAt(i+1)){
				matrix[i][i+1] = true;
				start = i;
				maxLength = 2;
			}
		}
		
		//Find palindrome for length of starting with 3... then increase by 1 till size of string input
		for(int k = 3; k <= inputLength; ++k){
			//reinitalize start position of input..
			for(int i = 0; i < inputLength - k + 1; ++i){
				int endingIndex = i + k - 1; 
				if(matrix[i+1][endingIndex-1] && input.charAt(i) == input.charAt(endingIndex)){
					matrix[i][endingIndex] = true;
					if(k > maxLength){
						start = i;
						maxLength = k;
					}
				}
				
				
			}
			
		}
		return input.substring(start, start + maxLength);
		
	}
	
	public static String findLongestNonRepeatingNonRepeatingCharacters(String input){
		if(input == null){
			return null;
		}
		
		int[] lastCharPosition = new int[256]; 
		
		for(int i=0; i<= 256; i++){
			lastCharPosition[i] = -1;
		}
		
		lastCharPosition[input.charAt(0)] = 0;
		
		int start = 0;
		int maxLength = 0;
		int current = 0;
		int prevIndex = 0;
		
		for(int i = 1; i < input.length(); i++){
			prevIndex = lastCharPosition[input.charAt(i)];
			if(prevIndex == -1 || i- current > prevIndex){
				current++;
			}else{
				if(maxLength < current){
					maxLength = current; 
					start = i - maxLength;
				}
				current = i - prevIndex;
				
			}
			lastCharPosition[input.charAt(i)] = i; 
			
		}
		if(current > maxLength){
			maxLength = current;
			start = input.length() - maxLength;
		}
		
		return input.substring(start, start+ maxLength);
	}
	
	public static String findLongestNonRepeatingCharWithMap(String input){
		char[] charArray = input.toCharArray();
		Map<Character, Integer> charToPosition = new LinkedHashMap<Character, Integer>();
		String longestSubString = null;
		int maxLength = 0;
		
		for(int i = 0; i < charArray.length; i++){
			char c = charArray[i];
			if(!charToPosition.containsKey(c)){
				charToPosition.put(c, i);
			}else{
				i = charToPosition.get(c);
				charToPosition.clear();
			}
			
			if(maxLength < charToPosition.size()){
				maxLength = charToPosition.size();
				longestSubString = charToPosition.keySet().toString();
			}
			
			
			
		}
		return longestSubString;
	}
	
	public static Set<String> findAllPermutations(String input){
		Set<String> permutations = new HashSet<String>();
		if(input == null || input.length() == 0){
			permutations.add("");
			return permutations;
		}
		char firstChar = input.charAt(0);
		String restOfString = input.substring(1);
		Set<String> words = findAllPermutations (restOfString);
		for(String word: words){
			for(int i = 0; i < word.length(); i++){
				String prefix = word.substring(0, i);
				String suffix = word.substring(i);
				String perm = prefix + firstChar + suffix;
				permutations.add(perm);
			}
		}
		return permutations;
	}
	
	
	
}
