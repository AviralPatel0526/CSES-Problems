#include <bits/stdc++.h>
using namespace std;

struct Pair {
    int v;
    long long wt;
    Pair(int _v, long long _wt) : v(_v), wt(_wt) {}
};

// Comparator for priority queue (min heap)
struct Compare {
    bool operator()(const Pair &p1, const Pair &p2) {
        return p1.wt > p2.wt;  // Min-heap (smallest weight first)
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;

    vector<vector<pair<int, long long>>> adj(n);
    for (int i = 0; i < m; i++) {
        int src, dest;
        long long wt;
        cin >> src >> dest >> wt;
        src--, dest--; // Convert 1-based index to 0-based

        adj[src].push_back({dest, wt});
        // adj[dest].push_back({src, wt}); // If the graph is undirected
    }

    priority_queue<Pair, vector<Pair>, Compare> pq;
    pq.push(Pair(0, 0));  // Start from node 0

    vector<long long> distance(n, LLONG_MAX);
    distance[0] = 0;

    while (!pq.empty()) {
        Pair curr = pq.top();
        pq.pop();

        if (curr.wt > distance[curr.v]) continue; // **Lazy Dijkstra: Skip if already processed**

        for (auto &[neighbor, weight] : adj[curr.v]) {
            if (curr.wt + weight < distance[neighbor]) {
                distance[neighbor] = curr.wt + weight;
                pq.push(Pair(neighbor, distance[neighbor]));
            }
        }
    }

    for (int i = 0; i < n; i++) {
        cout << (distance[i] == LLONG_MAX ? -1 : distance[i]) << " ";
    }
    cout << endl;

    return 0;
}
