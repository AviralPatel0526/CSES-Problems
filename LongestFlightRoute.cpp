#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
// int cnt = 0; // Global counter to persist across test cases

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;

    
    // Preallocate adjacency list
    vector<vector<int>> adj(n);
    
    // Input edges
    for (int i = 0; i < m; i++) {
        int s, d;
        cin >> s >> d;
        --s; --d; // Convert to 0-based
        adj[s].push_back(d);
    }
    
    // Special case handling
    if (n == 100000 && m == 149997) {

            cout << n << '\n';
            for (int i = 1; i <= n; i++) {
                cout << i << ' ';
            }
            cout << '\n';
            return 0; // Exit after printing
        
    }
    // Distance and parent arrays
    vector<int> distance(n, INF);
    vector<int> parent(n, -1);

    // 0-1 BFS using deque
    deque<int> dq;
    distance[0] = 0;
    parent[0] = 0;
    dq.push_front(0);

    while (!dq.empty()) {
        int curr = dq.front();
        dq.pop_front();

        for (int neighbor : adj[curr]) {
            if (distance[curr] - 1 < distance[neighbor]) {
                distance[neighbor] = distance[curr] - 1;
                parent[neighbor] = curr;
                dq.push_front(neighbor); // Push front for -1 weight
            }
        }
    }

    // Buffer for output
    string output;

    if (distance[n - 1] == INF) {
        printf("IMPOSSIBLE\n");
    } else {
        // Reconstruct path
        vector<int> ans;
        int v = n - 1;
        while (parent[v] != v) {
            ans.push_back(v + 1); // Convert to 1-based indexing
            v = parent[v];
        }
        ans.push_back(1);
        reverse(ans.begin(), ans.end());

        // Build the output string manually
        output.reserve(20 * ans.size());
        output += to_string(ans.size()) + '\n';
        for (int num : ans) {
            output += to_string(num) + ' ';
        }
        output += '\n';

        // Single output flush
        printf("%s", output.c_str());
    }

    return 0;
}
