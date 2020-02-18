#include<bits/stdc++.h>
using namespace std; 
//Graph class containing methods to get all possible paths available from 
// vertex u to v
class Graph 
{ 
    int V;  
    list<int> *adj;
    void getPath(int u, int d, bool visited[], 
                            int path[], int &path_index,vector<vector<int> > &paths) 
    { 
        visited[u] = true; 
        path[path_index] = u; 
        path_index++; 
        vector<int> temp; 
        if (u == d) 
        { 
            for (int i = 0; i<path_index; i++){
                temp.push_back(path[i]);
            } 
            paths.push_back(temp);
        } 
        else 
        { 
            list<int>::iterator i; 
            for (i = adj[u].begin(); i != adj[u].end(); ++i) 
                if (!visited[*i]) 
                    getPath(*i, d, visited, path, path_index,paths); 
        } 
        path_index--; 
        visited[u] = false; 
    } 
  
public: 
    Graph(int V) 
    { 
        this->V = V; 
        adj = new list<int>[V]; 
    } 
    void addEdge(int u, int v) 
    { 
        adj[u].push_back(v);  
    } 
    void getAllPaths(int s, int d,vector<vector<int> > &paths) 
    { 
        bool *visited = new bool[V]; 

        int *path = new int[V]; 
        int path_index = 0; 
    
        for (int i = 0; i < V; i++) 
            visited[i] = false; 
        getPath(s, d, visited, path, path_index,paths); 
    } 
}; 
//caltime calculates the time taken for a given path 
int caltime(vector<int> &path,int S,int T){
    int n=path.size()-1;
    int t=0;
    int x;
        for(int i=0;i<n;i++){
            t=t+T;
            x=t/S;
            if (i==n-1) break;
            while(x%2==1){
                t=t+((x+1)*S-t);
                x=t/S;
            }  
        }
        return t;
}
int main() 
{ 
   int n,m,s,t; //input 
   cin>>n>>m>>s>>t;
    Graph g(n+1);//graph of n+1 vertices
    
    int a,b;
   for (int i=0;i<m;i++){
       cin>>a>>b;
        g.addEdge(a,b);
        g.addEdge(b,a);
   }
   cin>>a>>b;
   vector<vector<int> > paths;
   g.getAllPaths(a,b,paths);//passing paths as reference and all paths are stored in paths vector 
   vector<int> least;//a tempeorary vector conatining path of least time 
   vector<vector<int> > ll;// list of vectors who have same least time
   int min=32767;
   for (int i=0;i<paths.size();i++){
       if(caltime(paths[i],s,t)<min){
           min=caltime(paths[i],s,t);
           ll.clear();
            least=paths[i]; 
       }
       if(caltime(paths[i],s,t)==min){
           least=paths[i];
           ll.push_back(least);
       }
   }
   sort(ll.begin(),ll.end());// sorting lexicographically and selecting first or least one
   cout<<ll[0].size()<<endl;
   for(int p=0;p<ll[0].size();p++) cout<<ll[0][p]<<" ";
    return 0; 
} 