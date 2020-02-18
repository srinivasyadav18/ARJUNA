/*
 * 
 * 
 
In a social group, there are N people, with unique integer ids from 0 to N-1.

We have a list of logs, where each logs[i] = [timestamp, id_A, id_B] contains a non-negative integer timestamp, and the ids of two different people.

Each log represents the time in which two different people became friends.  Friendship is symmetric: if A is friends with B, then B is friends with A.

Let's say that person A is acquainted with person B if A is friends with B, or A is a friend of someone acquainted with B.

Return the earliest time for which every person became acquainted with every other person. Return -1 if there is no such earliest time.

 

Example 1:

Input:8
20190101 0 1
20190104 3 4
20190107 2 3
20190211 1 5
20190224 2 4
20190301 0 3
20190312 1 2
20190322 4 5
6
Output: 20190301

Explanation: 
First Line 'k' Indicates number of logs
next k lines indicates log information contains timestamp and info of people who become friends
(TimeStamp, Friend-1, Friend-2)
last line indicates number of people


Explanation :

The first event occurs at timestamp = 20190101 and after 0 and 1 become friends we have the following friendship groups [0,1], [2], [3], [4], [5].
The second event occurs at timestamp = 20190104 and after 3 and 4 become friends we have the following friendship groups [0,1], [2], [3,4], [5].
The third event occurs at timestamp = 20190107 and after 2 and 3 become friends we have the following friendship groups [0,1], [2,3,4], [5].
The fourth event occurs at timestamp = 20190211 and after 1 and 5 become friends we have the following friendship groups [0,1,5], [2,3,4].
The fifth event occurs at timestamp = 20190224 and as 2 and 4 are already friend anything happens.
The sixth event occurs at timestamp = 20190301 and after 0 and 3 become friends we have that all become friends.

Sample Input 2: 5
20190107 2 3
20190211 1 5
20190224 2 4
20190301 0 3
20190104 3 4
6

Output : -1

Explanation :
1 and 5 does not have any other friend (0,2,3,4) 
There in no such time that all 6 people become friends.


case = 1
input =8
20190101 0 1
20190104 3 4
20190107 2 3
20190211 1 5
20190224 2 4
20190305 0 3
20190312 1 2
20190322 4 5
6
output =20190305

case = 2
input =10
20190424 6 7
20190601 5 6
20190101 0 1
20190104 3 4
20190107 2 3
20190211 1 5
20190224 2 4
20190301 0 3
20190312 1 2
20190322 4 5
8
output =20190601

case = 3
input =12
20190107 2 3
20190211 1 5
20190424 6 7
20190501 5 6
20190101 0 1
20190104 3 4
20190107 2 6
20190551 8 5
20190224 2 4
20190301 0 3
20190312 1 2
20190322 4 5
9
output =20190551

case = 4
input =14
20190601 5 6
20190807 9 1
20190107 2 3
20190211 1 5
20190424 6 7
20190501 5 6
20190101 0 1
20190104 3 4
20190107 2 6
20190551 8 5
20190224 2 4
20190301 0 3
20190312 1 2
20190322 4 5
10
output =20190807

case = 5
input =5
20190107 2 3
20190211 1 5
20190224 2 4
20190301 0 3
20190104 3 4
6
output =-1

case = 6
input =6
20190101 0 1
20190104 3 4
20190107 2 3
20190811 1 5
20190224 2 4
20190707 0 3
6
output =20190811

case = 7
input =9
20190101 0 1
20190104 3 4
20190107 2 3
20191111 1 5
20190224 2 4
20190554 3 6
20190407 6 3
20191001 4 5
20190707 0 3
7
output =20191001


 */



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class EarliestMoment {

	public static int earliestAcq(int[][] logs, int N) {
        int[] parent = IntStream.range(0, N).toArray();
        int time = -1, cnt = N;
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        for (int[] log: logs) {
            int t = log[0], a = log[1], b = log[2];
            int A = root(a, parent), B = root(b, parent);
            parent[A] = parent[a] = parent[b] = B;
            if (A != B) {
                time = t;
                cnt--;
            }
        }
        return cnt == 1 ? time : -1;
    }
    
    public static int root(int a, int[] parent) {
        return parent[a] == a ? a: root(parent[a], parent);
    }
    
    
    public static void main(String args[] ) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String str ;
			String[] numbers;
			
			int nlogs=Integer.parseInt(br.readLine());      
			int[][] logs=new int[nlogs][3];

		       for(int i=0; i<nlogs; i++)
		       {
		    	   str = br.readLine();
		    	   numbers = str.split(" ");
		   logs[i]=Arrays.asList(numbers).stream().mapToInt(Integer::parseInt).toArray();
		       }
				int n=Integer.parseInt(br.readLine());      

		       System.out.println(earliestAcq(logs,n));
	}
    
}
