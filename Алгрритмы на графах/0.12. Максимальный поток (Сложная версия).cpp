#include <iostream>
#include <vector>
#include <cstring>
#include <cmath>
#include <algorithm>
using namespace std;

const long long INF = 1000000000;
struct Edge {
    int a;
    int b;
    long long cap;
    long long flow;
};
int n, m;
long long* distances;
long long* arr;
long long* q;
vector<Edge> edge;
vector<int>* g;
void add_edge(const int& u, const int& v, const int& capacity) {
    Edge e1 = { u, v, capacity, 0 };
    Edge e2 = { v, u, 0, 0 };
    g[u].push_back((long long)edge.size());
    edge.push_back(e1);
    g[v].push_back((long long)edge.size());
    edge.push_back(e2);
}

bool bfs() {
    int qh = 0;
    int qt = 0;
    q[qt++] = 0;
    memset(distances, -1, n * sizeof(long long));
    distances[0] = 0;
    while (qh < qt && distances[n-1] == -1) {
        long long v = q[qh++];
        for (long long i = 0; i < g[v].size(); i++) {
            int id = g[v][i];
            int to = edge[id].b;
            if (distances[to] == -1 && edge[id].flow < edge[id].cap) {
                q[qt++] = to;
                distances[to] = distances[v] + 1;
            }
        }
    }
    return distances[n-1] != -1;
}
long long dfs(const int& v, const long long& flow) {
    if (!flow) {
        return 0;
    }
    if (v == n-1) {
        return flow;
    }
    for (; arr[v] < g[v].size(); arr[v]++) {
        long long id = g[v][arr[v]];
        long long to = edge[id].b;
        if (distances[to] != distances[v] + 1) {
            continue;
        }
        long long pushed = dfs(to, min(flow, edge[id].cap - edge[id].flow));
        if (pushed) {
            edge[id].flow += pushed;
            edge[id ^ 1].flow -= pushed;
            return pushed;
        }
    }
    return 0;
}
long long dinic() {
    long long flow = 0;
    for (;;) {
        if (!bfs()) {
            break;
        }
        memset(arr, 0, n * sizeof(long long));
        while (long long pushed = dfs(0, INF)) {
            flow += pushed;
        }
    }
    return flow;
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> n >> m;
    distances = new long long[n];
    arr = new long long[n];
    q = new long long[n];
    g = new vector<int>[n];
    int u, v, w;
    for (int i = 0; i < m; i++) {
        cin >> u >> v >> w;
        u--;
        v--;
        add_edge(u, v, w);
    }
    cout << dinic();
}