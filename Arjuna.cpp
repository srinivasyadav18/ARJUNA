#include"Arjuna.h"
//Graph class
//Again
void printv(std::vector<int> &v)
{
    for (int i = 0; i < v.size(); i++)
    {
        std::cout << v[i] << " ";
    }
    std::cout << std::endl;
}
int lcs(const std::string &s1,const std::string &s2){
	std::vector<std::vector<int> > dp(s2.size()+1,std::vector<int>(s1.size()+1,0));;;
	for(int i=1;i<s2.length()+1;i++)
	{
		for(int j=1;j<s1.length()+1;j++)
		{
			if (s1[i-1]==s2[j-1]) dp[i][j]=dp[i-1][j-1]+1;
			else dp[i][j]=std::max(dp[i][j-1],dp[i-1][j]);
		}
	};
	return dp.back().back();
}

std::string lcp(const std::string &a,const std::string &b)
{
    std::string res = "";
    for (int i = 0; i < std::min(a.length(), b.length()); i++)
    {
        if (a[i] == b[i])
            res = res + a[i];
        else
            break;
    }
    return res;
}
std::vector<std::string> split(const std::string &str, const std::string &delim)
{
    std::vector<std::string> tokens;
    size_t prev = 0, pos = 0;
    do
    {
        pos = str.find(delim, prev);
        if (pos == std::string::npos)
            pos = str.length();
        std::string token = str.substr(prev, pos - prev);
        if (!token.empty())
            tokens.push_back(token);
        prev = pos + delim.length();
    } while (pos < str.length() && prev < str.length());
    return tokens;
}
std::string lrs(std::string s)
{
    std::vector<std::string> prefixes;
    for (int i = 0; i < s.length(); i++)
    {
        prefixes.push_back(s.substr(i, s.length() - 1));
    }
    sort(prefixes.begin(), prefixes.end());

    std::string temp;
    std::string res;
    int max = 0;
    for (int i = 0; i < prefixes.size() - 1; i++)
    {
        temp = lcp(prefixes[i], prefixes[i + 1]);
        if (temp.length() > max)
        {
            max = temp.length();
            res = temp;
        }
    }
    return res;
}

std::vector<int> strings_to_ints(std::string str)
{
    std::istringstream ss(str);
    std::vector<std::string> nums;
    std::vector<int> numbers;
    do
    {
        std::string word;
        ss >> word;
        nums.push_back(word);
    } while (ss);
    nums.pop_back();
    for (int i = 0; i < nums.size(); i++)
    {
        numbers.push_back(atoi(nums[i].c_str()));
    }
    return numbers;
}

std::string random_string_gen(){
	srand(time(0));
	int size=rand()%15;
	std::string s="";
	for (int i=0;i<size;i++)
	{	char c=rand()%25+97;
		s=s+c;
	}
	std::cout<<s<<std::endl;
	return s;
}
std::string random_string_gen(int size){
	srand(time(0));
	std::string s="";
	for (int i=0;i<size;i++)
	{	char c=rand()%25+97;
		s=s+c;
	}
}


Graph::Graph(int v)
{
    this->V = v;
    G = new std::vector<int>[V];
}

void Graph::addEdge(int a, int b)
{
    G[a].push_back(b);
}

void Graph::display()
{
    std::cout << "Displaying Graph\n";
    for (int i = 0; i < V; i++)
    {
        std::cout << i << " ";
        for (int j = 0; j < G[i].size(); j++)
        {
            std::cout << G[i][j] << " ";
        }
        std::cout << std::endl;
    }
    std::cout << std::endl;
}

std::vector<int> Graph::bfs(int pos)
{
    std::vector<int> res;
    std::vector<int> queue;
    std::vector<bool> visited(this->V, false);
    visited[pos] = true;
    queue.push_back(pos);
    while (queue.size())
    {
        int front = *queue.begin();
        res.push_back(front);
        queue.erase(queue.begin());
        for (int i = 0; i < G[front].size(); i++)
        {
            if (visited[G[front][i]] == false)
            {
                visited[G[front][i]] = true;
                queue.push_back(G[front][i]);
            }
        }
    }
    return res;
}
void Graph::dfs_helper(int pos, std::vector<bool> &visited, std::vector<int> &dfs_result)
{
    visited[pos] = true;
    dfs_result.push_back(pos);
    for (int i = 0; i < G[pos].size(); i++)
    {
        if (visited[G[pos][i]] == false)
        {
            dfs_helper(G[pos][i], visited, dfs_result);
        }
    }
}
std::vector<int> Graph::dfs(int pos)
{
    std::vector<bool> visited(V, false);
    std::vector<int> dfs_result;
    dfs_helper(pos, visited, dfs_result);
    return dfs_result;
}
std::vector<std::vector<int> > Graph::level_order_traversal(int pos)
{
    std::vector<std::vector<int> > paths;
    int t_c = 0;
    int limit = 1;
    std::vector<int> q;
    std::vector<bool> visited(V, false);
    std::vector<int> temp;
    visited[pos] = true;
    q.push_back(pos);
    while (q.size())
    {
        int front = *q.begin();
        q.erase(q.begin());
        if (t_c == limit)
        {
            paths.push_back(temp);
            limit = temp.size();
            temp.clear();
            t_c = 0;
        }
        t_c++;
        for (int i = 0; i < G[front].size(); i++)
        {
            if (visited[G[front][i]] == false)
            {
                visited[G[front][i]] = true;
                q.push_back(G[front][i]);
                temp.push_back(G[front][i]);
            }
        }
    }
    return paths;
}
bool Graph::sp_helper(int pos, int dest, std::vector<bool> &visited, std::vector<int> &dist, std::vector<int> &parent)
{
    std::vector<int> queue;
    queue.push_back(pos);
    while (queue.size())
    {
        int front = *queue.begin();
        queue.erase(queue.begin());
        //std::cout << "front=" << front << std::endl;
        for (int i = 0; i < G[front].size(); i++)
        {
            if (visited[G[front][i]] == false)
            {
                visited[G[front][i]] = true;
                parent[G[front][i]] = front;
                dist[G[front][i]] = dist[front] + 1;
                queue.push_back(G[front][i]);
                if (G[front][i] == dest)
                    return true;
            }
        }
    }
    return false;
}
std::vector<int> Graph::shortest_path(int from, int to)
{
    std::vector<bool> visited(V, false);
    std::vector<int> dist(V, INT16_MAX);
    std::vector<int> parent(V);
    std::vector<int> path;
    visited[from] = true;
    dist[from] = 0;
    parent[from] = -1;
    if (sp_helper(from, to, visited, dist, parent) == true)
    {
        int bubble_up = to;
        while (bubble_up != -1)
        {
            path.push_back(bubble_up);
            bubble_up = parent[bubble_up];
        }
    }
    std::reverse(path.begin(), path.end());
    return path;
}
void Graph::all_path_helper(int from, int to, std::vector<bool> &visited, std::vector<int> path, std::vector<std::vector<int> > &paths)
{
    visited[from] = true;
    path.push_back(from);
    if (from == to)
    {
        paths.push_back(path);
    }
    else
    {
        for (int i = 0; i < G[from].size(); i++)
        {
            if (visited[G[from][i]] == false)
            {
                all_path_helper(G[from][i], to, visited, path, paths);
            }
        }
    }
    path.pop_back();
    visited[from] = false;
}
std::vector<std::vector<int> > Graph::all_paths(int from, int to)
{
    std::vector<bool> visited(V, false);
    std::vector<int> path;
    std::vector<std::vector<int> > paths;
    all_path_helper(from, to, visited, path, paths);
    return paths;
}

