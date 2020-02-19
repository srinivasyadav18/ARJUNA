#ifndef ARJUNA_H
#define ARJUNA_H

#include <vector>
#include <iostream>
#include <algorithm>
#include <string>
#include <sstream>


void printv(std::vector<int> &v);//
std::string lcp(std::string &a, std::string &b);
std::vector<std::string> split(const std::string &str, const std::string &delim);
std::string lrs(std::string s);
std::vector<int> strings_to_ints(std::string str);
template <typename Container>
void print(Container &c,std::string &sep){
    for (auto i : c) std::cout<<i<<sep;
}

template <typename Container>
void print(Container &c){
    for (auto i : c) std::cout<<i<<" ";
}
class Graph
{
private:
    int V;
    std::vector<int> *G;
    void dfs_helper(int pos, std::vector<bool> &visited, std::vector<int> &dfs_result);
    bool sp_helper(int pos, int dest, std::vector<bool> &visited, std::vector<int> &dist, std::vector<int> &parent);
    void all_path_helper(int from, int to, std::vector<bool> &visited, std::vector<int> path, std::vector<std::vector<int> > &paths);
public:
    Graph(int v);
    void addEdge(int a, int b);
    void display();
    std::vector<int> bfs(int pos);
    std::vector<int> dfs(int pos);
    std::vector<std::vector<int> > level_order_traversal(int pos);
    std::vector<int> shortest_path(int from, int to);
    std::vector<std::vector<int> > all_paths(int from, int to);
};


#endif