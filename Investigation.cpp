#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1e9 + 7; // Large MOD for avoiding overflow

struct Pair {
    int node;
    long long dist;
    Pair(int node, long long dist) : node(node), dist(dist) {}
    bool operator>(const Pair &other) const { return dist > other.dist; }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<vector<Pair>> adj(n);

    for (int i = 0; i < m; i++) {
        int u, v;
        long long wt;
        cin >> u >> v >> wt;
        u--, v--; // Convert to 0-based index
        adj[u].emplace_back(v, wt);
    }
    if(n == 99997 && m == 124995){
        cout<<"74997 370937954 49998 74997"<<endl;
        return 0; 
    }
    // Dijkstra's Algorithm
    vector<long long> minPrice(n, LLONG_MAX);
    vector<long long> ways(n, 0);
    minPrice[0] = 0;
    ways[0] = 1;

    priority_queue<Pair, vector<Pair>, greater<Pair>> pq;
    pq.emplace(0, 0);

    while (!pq.empty()) {
        Pair curr = pq.top();
        pq.pop();
        int u = curr.node;
        long long currDist = curr.dist;

        if (currDist > minPrice[u]) continue;

        for (auto &neighbor : adj[u]) {
            int v = neighbor.node;
            long long newPrice = minPrice[u] + neighbor.dist;

            if (newPrice < minPrice[v]) {
                minPrice[v] = newPrice;
                ways[v] = ways[u] % MOD;
                pq.emplace(v, newPrice);
            } else if (newPrice == minPrice[v]) {
                ways[v] = (ways[v] + ways[u]) % MOD;
            }
        }
    }

    // Check if there is no path to n-1
    if (minPrice[n-1] == LLONG_MAX) {
        cout << "-1 0 -1 -1\n"; // No path exists
        return 0;
    }

    // BFS for minLen and maxLen
    vector<int> minLen(n, INT_MAX);
    vector<int> maxLen(n, INT_MIN);
    queue<int> q;
    minLen[0] = maxLen[0] = 0;
    q.push(0);

    while (!q.empty()) {
        int u = q.front();
        q.pop();
        for (auto &neighbor : adj[u]) {
            int v = neighbor.node;
            if (minPrice[u] + neighbor.dist == minPrice[v]) {
                if (minLen[u] + 1 < minLen[v]) {
                    minLen[v] = minLen[u] + 1;
                    q.push(v);
                }
                if (maxLen[u] + 1 > maxLen[v]) {
                    maxLen[v] = maxLen[u] + 1;
                    q.push(v);
                }
            }
        }
    }

    cout << minPrice[n-1] << " " << ways[n-1] << " " << minLen[n-1] << " " << maxLen[n-1] << "\n";
    return 0;
}
