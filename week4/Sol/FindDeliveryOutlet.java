package Elite2020;
/*
 * 
 A popular courier services company has many delivery outlets spread in various streets in a city. The
streets of the city are arranged in a 2D grid of N rows and N columns. Each cell of the grid is assigned
either 0 or 1. 0 represents a delivery-outlet and 1 represents delivery-hub.

Given the grid containing 0s and 1s, find out a delivery-outlet such that its distance to the nearest
delivery hub is maximized.

Note: the distance between two cells (x0, y0) and (x1, y1) is |x0-x1| + |y0-y1|
If there are delivery outlets or delivery hubs then return -1.


Example 1:

Input:3
1 0 1
0 0 0
1 0 1
Output: 2
Explanation: 
● First line 3 size of the square grid.
● Next 3 lines represent delivery-outlet (0) and delivery-hub (1).
● The delivery-outlet (1, 1) is as far as possible from all delivery-
hubs with distance 2 .


Example 2:
Input:3
1 0 0
0 0 0
0 0 0

Output: 4

Explanation: 

First line 3 size of the square grid.

Next 3 lines represent delivery-outlet (0) and delivery-hub (1).

The delivery-outlet (2, 2) is as far as possible from all delivery-
hubs with distance 4 .

Example 3:
Input:3
1 0 1
0 0 0
0 0 1
Output: 2

Explanation: 
First line 3 size of the square grid.
Next 3 lines represent delivery-outlet (0) and delivery-hub (1).
The delivery-outlet (2, 0) is as far as possible from all delivery-
hubs with distance 2 .

 */

import java.util.*;

public class FindDeliveryOutlet {

	static int[][] directions = new int[][] {{-1, 0},{1,0},{0,-1},{0,1}};
    public static int maxDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)return -1;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> queue = new ArrayDeque<>();
        for(int i = 0 ; i < grid.length ; i++) {
            for(int j = 0 ; j < grid[0].length ; j++) {
                if(grid[i][j] == 1) {
                    queue.offer(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        int level = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0 ; i < size ; i++) {
                int[] start = queue.poll();
                int x = start[0];
                int y = start[1];
                for(int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length 
                       && !visited[newX][newY] && grid[newX][newY] == 0) {
                        visited[newX][newY] = true;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
            level++;
        }
        return level <= 0 ? -1 : level;
    }
    
    public static void main(String args[] ) {
		 Scanner scan = new Scanner(System.in);
			int N=scan.nextInt();

			int[][] grid=new int[N][N];
		       for(int i=0; i<N; i++)
		       {
		           for(int j=0; j<N; j++)
		           {
		        	   grid[i][j] = scan.nextInt();
		           }
		       }
		       System.out.println(maxDistance(grid));
	}
    
}
