/*
 * 
 * 
From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.

 

Example 1:

Input:abc
abcbc
Output: 2


Explanation: 
First Line Represents Source
Second Line Represents Target

The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".

Example 2:

Input:abc
acdbc
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.

Example 3:

Input: xyz
xzyxz
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".


case = 1
input =abc
abcabcabcabc
output =4

case = 2
input =abc
accaabcbcbc
output =6

case = 3
input =xyz
xzxyyxzxyzxyxxzyyz
output =10

case = 4
input =axyz
axzxyyxzxyzxyxxazayayaz
output =12

case = 5
input =abc
acdbc
output =-1


case = 6
input =ram
ramramramramramaramarmaramaraamammram
output =17


case = 7
input =abcd
acdbcabcddabcdd
output =6
 * 
 */



import java.util.Scanner;

public class ShortestWay {
	
	public static int getShortestWay(String source, String target) {
		 int t = 0;
	        int ans = 0;
	        while (t < target.length()) {
	            int pt = t;
	            
	            for (int s = 0; s < source.length(); s++) {
	                if (t < target.length() && source.charAt(s) == target.charAt(t)) {
	                    t++;
	                }
	            }
	            
	            if (t == pt) {
	                return -1;
	            }
	            ans++;
	        }
	        
	        return ans;
	}
	
	 public static void main(String args[] ) {
		 Scanner sc = new Scanner(System.in);
			String source=sc.next();
			String target=sc.next();


		    System.out.println(getShortestWay(source,target));
	}

}
