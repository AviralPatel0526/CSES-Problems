#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef pair<ll, int> pli;

struct Pair {
    int v;
    ll wt;
    Pair(int v, ll wt) : v(v), wt(wt) {}
};

void dijkstra(int s, vector<vector<Pair>>& graph, vector<ll>& ans) {
    priority_queue<pli, vector<pli>, greater<pli>> pq;
    pq.push({0, s});
    fill(ans.begin(), ans.end(), LLONG_MAX);
    ans[s] = 0;

    while (!pq.empty()) {
        auto [currWt, currV] = pq.top();
        pq.pop();

        if (currWt > ans[currV]) continue; // Optimization to avoid redundant paths

        for (auto& edge : graph[currV]) {
            if (currWt + edge.wt < ans[edge.v]) {
                ans[edge.v] = currWt + edge.wt;
                pq.push({ans[edge.v], edge.v});
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<vector<Pair>> adj(n), revadj(n);

    for (int i = 0; i < m; i++) {
        int src, dest;
        ll wt;
        cin >> src >> dest >> wt;
        src--; dest--;  // Convert to 0-based indexing

        adj[src].emplace_back(dest, wt);
        revadj[dest].emplace_back(src, wt);
    }

    vector<ll> forwardDijkstra(n), backwardDijkstra(n);

    dijkstra(0, adj, forwardDijkstra);
    dijkstra(n - 1, revadj, backwardDijkstra);

    ll ans = LLONG_MAX;

    for (int i = 0; i < n; i++) {
        for (auto& edge : adj[i]) {
            if(forwardDijkstra[i] != LLONG_MAX && backwardDijkstra[edge.v] != LLONG_MAX){

                ans = min(ans, forwardDijkstra[i] + edge.wt / 2 + backwardDijkstra[edge.v]);
            }
        }
    }

    cout << ans << '\n';

    return 0;
}
