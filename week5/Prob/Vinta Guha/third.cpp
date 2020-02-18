#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
//calpaths returns all paths in a grid from one corner to other corner if path doesnot contain -1s
void calpaths(vector<vector<int> >grid,vector<int> path,vector<vector<int> > &paths,int i,int j,int r,int c,vector<int> &input){
    if(i+1==r && j+1==c){

        for(int i=0;i<path.size();i++){
            if (input[path[i]]==-1) return;
        }
        paths.push_back(path);
        return ;
    }
    if (j+1!=c){
        path.push_back(grid[i][j+1]);
        calpaths(grid,path,paths,i,j+1,r,c,input);
        path.pop_back();
        
    }
    if(i+1!=r){
        path.push_back(grid[i+1][j]);
        calpaths(grid,path,paths,i+1,j,r,c,input);
    }
}
//sum func gives total mangoes from a complete path
int sum(vector<int> &a,vector<int> &b,vector<int> input){
    int s=0;
    for(int i=0;i<a.size();i++) {s=s+input[a[i]];input[a[i]]=0;}
    for(int i=0;i<b.size();i++) s=s+input[b[i]];
    return s;
}
int main(){
    int n;
    cin>>n;
    vector<int> input(n*n);
    for(int i=0;i<n*n;i++){
        cin>>input[i];//input grid as a vector
    }
    int count=0;
    vector<vector<int> > vec( n , vector<int> (n, 0));
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++){
            vec[i][j]=count++;
        }
    }
    vector<int> path;
    vector<vector<int> > fpaths;// all forward paths
    path.push_back(vec[0][0]);
    calpaths(vec,path,fpaths,0,0,n,n,input);
    vector<vector<int> > vec2=vec;
    vector<vector<int> > rpaths;// all reverse paths
    reverse(vec2.begin(),vec2.end());
    for(int i=0;i<vec2.size();i++){
        reverse(vec2[i].begin(),vec2[i].end());
    }
    path.clear();
    path.push_back(vec2[0][0]);
    calpaths(vec2,path,rpaths,0,0,n,n,input);
    int s;
    int max=-1;
    for(int i=0;i<fpaths.size();i++){
        s=0;
        for(int j=0;j<rpaths.size();j++){
            s=sum(fpaths[i],rpaths[j],input);//calculating sum of one complete path 
            if(s>max){
                max=s;//picking maximum mangoes 
            }
        }
    }
    if (max==-1) {cout<<0;return 0;}
    cout<<max;
}