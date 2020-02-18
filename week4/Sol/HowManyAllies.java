package Elite2020;
/*
 * 
In medieval period, in East Asia, there were several small kingdoms. Every kingdom was an ally
with other neighboring kingdoms. The whole region appears as a giant connected graph.

Given the graph, find out the total number of kingdoms allied at t distance away from each
other (i.e., t distance connectivity). For example, two kingdoms are directly connected are at 1
distance connectivity. While the two kingdoms having a common ally without having directly
connectivity (immediate neighbor), are at 2 distance connectivity.

First line of input line contains, two integers n and e, where N is the kingdoms (nodes) and E are
the edges. Next e line will contain two integers U and V meaning that node u and node v are
connected to each other in undirected fashion. Next line contains single integer, m, which is
number of queries. Next m lines, each have two inputs, one as source node and other as a
required t distance connectivity which should be used to process query.

Sample input :
9 10
0 1
1 2
0 6
1 3
2 3
3 6
6 7
8 6
6 5
4 5
3
3 2
4 3
1 1

Sample output :
4
4
3


Explanation :

First line 9 10 represents the number of kingdoms (nodes) and number of
edges (allies)

Each line of the next 10 lines represents IDs of kingdoms that are direct
allies (undirected connection)

Next m lines, each have two inputs, one as source node and other as a
required t distance connectivity which should be used to process query
3 represents number of queries

3 2: Source node: 3, and we have to find out total number of nodes at a
distance of 2 from node 3.

0 (3->1->0), 7 (3->6->7), 9 (3->6->8), 5 (3->6->5) = 4

4 3: Source node: 4, and we have to find out total number of nodes at a
distance of 3 from node 4.

0 (4->5->6->0), 3 (4->5->6->3), 7 (4->5->6->7), 8 (4->5->6->8) = 4

Source node: 1, and we have to find out total number of nodes at a
distance of 1 from node 1.

0 (1->0), 3 (1->3), 2 (1->2) = 3


 */

import java.util.*;

public class HowManyAllies {

	 static  class Graph{
	        int ver,dis;
	        Graph(int v,int d){
	            ver=v;
	            dis=d;
	        }
	    }
	    public static void main(String args[] ) throws Exception {
	      
	        Scanner sc= new Scanner(System.in);
	        int v=sc.nextInt(); int e=sc.nextInt();
	     ArrayList<ArrayList<Integer>> ar=new  ArrayList<ArrayList<Integer>>();
	     ArrayList<Integer> res=new ArrayList<Integer>();              
	            for(int i=0;i<v;i++){
	                ar.add(new ArrayList<Integer>());
	            }
	                               
	                 while(e-->0){
	                        int s=sc.nextInt(); int d=sc.nextInt();
	                        ar.get(s).add(d);
	                        ar.get(d).add(s);
	                  }
	                               
	            int qu=sc.nextInt();
	            while(qu-->0){
	                int src=sc.nextInt(); int dis=sc.nextInt();
	                boolean[] vis=new boolean[v];
	                Queue<Graph> q=new LinkedList<>();
	                q.add(new Graph(src,0));
	                vis[src]=true;
	                int count=0;
	                
	                while(!q.isEmpty()){
	                    Graph t=q.poll();
	                    if(t.dis==dis){
	                        count++;
	                    }
	                    else{
	                        ArrayList<Integer> a=ar.get(t.ver);
	                        for(int i=0;i<a.size();i++){
	                            if(!vis[a.get(i)]){
	                                vis[a.get(i)]=true;
	                                q.add(new Graph(a.get(i),t.dis+1));
	                            }
	                        }
	                    }
	                    
	                
	                }
	                	res.add(count);
	            }
	            
	            for(int num:res){
		    	    System.out.println(num);
		    	} 
              
	    }
}
