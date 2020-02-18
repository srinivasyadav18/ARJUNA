/*
 * 
 * 
There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in sorted order.


Example 1:

Input:4
4
0 1
1 2
2 0
1 3
Output: 1 3

Explanation :
First Line "n" Indicates Number of Servers
Second Line "k" Indicates Number of Connections
Next k lines represents connections between servers.
1 3 is critical connection if we remove this connection there is no other possibility to connect to server 3

Example 2:

Input:6
5
0 1
1 2
5 4
2 0
1 3

Output: 
1 3
4 5

Explanation :
5 4 and 1 3 are critical connections in the given input. 
output should be in the sorted order as shown above.


Example 3 :

Input:4
5
0 1
1 2
2 3
3 1
0 2

Output: 
-1

Explanation :
No Critical Connection Found



case = 1
input =6
5
0 1
1 2
5 4
2 0
1 3

output =1 3
4 5

case = 2
input =4
5
0 1
1 2
2 3
3 1
0 2
output =-1

case = 3
input =8
9
1 2
2 3
4 5
6 7
0 4
1 3
3 6
5 2
1 7
output =0 4
2 5
4 5

case = 4
input =5
4
1 2
0 3
2 4
1 3
output =0 3
1 2
1 3
2 4


case = 5
input =6
5
2 1
0 3
2 4
1 3
4 5
output =0 3
1 2
1 3
2 4
4 5

case = 6
input =6
7
2 1
0 3
2 4
1 3
4 5
1 4
1 5
output =0 3
1 3

case = 7
input =7
8
2 1
0 3
2 4
1 3
4 5
1 4
1 5
2 3
output =0 3
 */

package Elite2020;

import java.util.*;


class ListComparator<T extends Comparable<T>> implements Comparator<List<T>> {

	  @Override
	  public int compare(List<T> o1, List<T> o2) {
	    for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
	      int c = o1.get(i).compareTo(o2.get(i));
	      if (c != 0) {
	        return c;
	      }
	    }
	    return Integer.compare(o1.size(), o2.size());
	  }

	}

 class Critical {
	
	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		int[] disc = new int[n], low = new int[n];
		// use adjacency list instead of matrix will save some memory, adjmatrix will cause MLE
		List<Integer>[] graph = new ArrayList[n];
		List<List<Integer>> res = new ArrayList<>();
		Arrays.fill(disc, -1); // use disc to track if visited (disc[i] == -1)
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		// build graph
		for (int i = 0; i < connections.size(); i++) {
			int from = connections.get(i).get(0), to = connections.get(i).get(1);
			graph[from].add(to);
			graph[to].add(from);
		}

		for (int i = 0; i < n; i++) {
			if (disc[i] == -1) {
				dfs(i, low, disc, graph, res, i);
			}
		}
		Collections.sort(res, new ListComparator<>());

		return res;
	}

	int time = 0; // time when discover each vertex

	private void dfs(int u, int[] low, int[] disc, List<Integer>[] graph, List<List<Integer>> res, int pre) {
		disc[u] = low[u] = ++time; // discover u
		for (int j = 0; j < graph[u].size(); j++) {
			int v = graph[u].get(j);
			if (v == pre) {
				continue; // if parent vertex, ignore
			}
			if (disc[v] == -1) { // if not discovered
				dfs(v, low, disc, graph, res, u);
				low[u] = Math.min(low[u], low[v]);
				if (low[v] > disc[u]) {
					// u - v is critical, there is no path for v to reach back to u or previous vertices of u
					res.add(Arrays.asList(u, v));
				}
			} else { // if v discovered and is not parent of u, update low[u], cannot use low[v] because u is not subtree of v
				low[u] = Math.min(low[u], disc[v]);
			}
		}
	}

	
	
	
	public static void main(String args[] ) {
		 Scanner scan = new Scanner(System.in);
			int s=scan.nextInt();
			Critical c=new Critical();
			List<List<Integer>> connections = new ArrayList<List<Integer>>();
			
			int n=scan.nextInt();

		       for(int i=0; i<n; i++)
		       {
					List<Integer> conn = new ArrayList<Integer>();

		           for(int j=0; j<2; j++)
		           {
		        	  
		        	   conn.add( scan.nextInt());
		   			
		           }
		           connections.add(conn);
		       }
		       List<List<Integer>>  result=c.criticalConnections(s,connections);
		       
if(!result.isEmpty()) {
		       for (int i = 0; i < result.size(); i++) { 
		            for (int j = 0; j < result.get(i).size(); j++) { 
		                System.out.print(result.get(i).get(j) + " "); 
		            } 
		            System.out.println(); 
		        } 
}
else
    System.out.println("-1"); 

	
	}


}
