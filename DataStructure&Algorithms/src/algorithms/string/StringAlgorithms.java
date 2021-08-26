package algorithms.string;

import java.util.HashSet;
import java.util.Set;

public class StringAlgorithms {

	public static String findLongestNonRepeatingCharacters(String input){
		if(input == null){
			return null;
		}
		
		int n = input.length(); 
		if(n < 2){
			return input; 
		}
		
		int[] charIndexes = new int[256];
		
		/* Initialize the visited array as -1, -1 is 
        used to indicate that character has not been 
        visited yet. */
		for(int i = 0; i < 256; i++){
			charIndexes[i] = -1;
		}
		
		  /* Mark first character as visited by storing the
        index of first   character in visited array. */
		
		System.out.println(input.charAt(0));
		charIndexes[input.charAt(0)] = 0;
		
		int currLength = 1; 
		int maxLength = 1; 
		int prevIndex = 0;
		int startIndex = 0; 
		
		//NRCS = Non Repeating Character String
		  /* Start from the second character. First character is
        already processed (cur_len and max_len are initialized
      as 1, and visited[str[0]] is set */
		for(int i =1; i < n; i++){
			System.out.println(input.charAt(i));
			prevIndex = charIndexes[input.charAt(i)];
			
		     /* If the current character is not present in
	           the already processed substring or it is not
	              part of the current NRCS, then do cur_len++ */
			if(prevIndex == -1 || i - currLength > prevIndex){
				currLength++;
			}
			
		      /* If the current character is present in currently
            considered NRCS, then update NRCS to start from
            the next character of previous instance. */
			
			else{
				
				if(currLength > maxLength){
					maxLength = currLength; 
					startIndex = i - maxLength; 
				}
				//Update the current Length (NRCS) to start from next character of previous instance 
				currLength = i - prevIndex;
				
			}
			
			charIndexes[input.charAt(i)] = i;
		}
		
		/*This check for in case the longest non repeating characters occur at the end of a string input */
		if(currLength > maxLength){
			maxLength = currLength;
			startIndex = n - maxLength;
		}
		return input.substring(startIndex, startIndex + maxLength);
		
		
	}
	
	public static String reverseWords(String input){
		int n = input.length();
		char[] reverseWord = new char[n];
		
		for(int i = 0; i < n; i++){
			char c = input.charAt(i);
			reverseWord[n- 1 -i] = c;
		}
		return new String(reverseWord);
		
	}
	
	public static String findLongestPalindrome(String input){
		int n = input.length();
		boolean table[][] = new boolean[n][n];
		 
        // All substrings of length 1 are palindromes
        int maxLength = 1;
        for (int i = 0; i < n; ++i)
            table[i][i] = true;
 
        // check for sub-string of length 2.
        int start = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                table[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }
         
		
		
		for(int k = 3; k<= n; ++k){
			System.out.println("\n");
			System.out.println("Position K: " + k);
			
			for(int i = 0; i < n - k + 1; ++i){
				int j = i + k - 1; 
				System.out.println("Position i: " + i + " Value: " + input.charAt(i));
				System.out.println("Position j: " + j + " Value: " + input.charAt(j));
				System.out.println("Value of Table at i+1 and j-1: " + table[i+1][j-1]);
				if(table[i+1][j-1] && input.charAt(i) == input.charAt(j)){
					System.out.println("True Condition...");
					table[i][j]=true;
					if(k>maxLength){
						start = i;
						maxLength=k;
					}
				}				
			}
			
			
		}
		
		
		return input.substring(start, start+maxLength);
		
	}
	
	
	public static String findLongestPalindromePractice(String input){
		int inputLength = input.length();
		
		//Table holds value of substring combinations and holds whether substring is a palindrome or not.
		boolean[][] table = new boolean[inputLength][inputLength];
		
		//1. All substring of size 1 is a palindrome...
		for(int i = 0; i < inputLength; ++i){
			table[i][i] = true; 
		}
		
		//2. Check for substrings of size 2. If 2 substrings are identical then it is a palindrome. 
		int start = 0; 
		int maxLength = 1;
		for(int i = 0; i < inputLength - 1; ++i){
			if(input.charAt(i) == input.charAt(i+1)){
				// 2 characters next to each other are identical..
				table[i][i+1] = true;
				start = i;
				maxLength = 2; 
			}
		}
		
		//3. Check for substrings of size 3+. 
		//k is the size of the substring to check. Increase by 1 until it is the size of inputLength 
		for(int k = 3; k>= inputLength; ++k){
			//reset start index since there could be longer palindrome than size 2 (found in step 2) 
			for(int i = 0; i <= inputLength - k + 1; ++i){
				// this will give the ending index based on the range of k. So if k is 3 you want to check string positions (0, 2), (1,3), (2,4) until hitting end of input string
				int endIndexToCheck = i + k - 1; 
				
				// check to see if the inner position of range i+1 to endIndexToCheck-1 is already a palindrome --> 
				//if true, then check to see if position of string from i to endIndexToCheck is the same
				// if true then found new longest palindrome sequence
				if(table[i+1][endIndexToCheck-1] && input.charAt(i)==input.charAt(endIndexToCheck)){
					table[i][endIndexToCheck] = true;
					if(k > maxLength){
						maxLength = k;
						start = i;
					}
					
				}
				
			}
			
		}
		return input.substring(start, start + maxLength);
	}
	
	public static Set<String> findAllPermutations(String input){
		Set<String> permutations = new HashSet<String>();
		if(input == null || input.length() == 0){
			permutations.add("");
			return permutations;
		}
		char first = input.charAt(0);
		String remainingString = input.substring(1);
		Set<String> words = findAllPermutations(remainingString);
		for (String word: words){
			System.out.println("Current Word: " + word);
			for(int i = 0; i<=word.length(); i++){
				String prefix = word.substring(0, i);
				String suffix = word.substring(i);
				System.out.println("Perm: " + prefix+ first + suffix);
				permutations.add(prefix+ first + suffix);
			}
		}
		return permutations;
	}
	
	
	
	public static void main(String[] args){
//		System.out.println(StringAlgorithms.findLongestNonRepeatingCharacters("ABCDABDEFGCAGDBD"));
//		System.out.println(StringAlgorithms.reverseWords("doggy"));
//		System.out.println(StringAlgorithms.findLongestPalindrome("moom"));
		Set<String> a = StringAlgorithms.findAllPermutations("abcd");
//		for (String b: a){
//			System.out.println(b);
//		}
	}
	
}
