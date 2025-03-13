#include <bits/stdc++.h>
using namespace std;

class Pair {
public:
    int v, c;
    Pair(int v, int c) {
        this->v = v;
        this->c = c;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int n, m;
    cin >> n >> m;

    vector<vector<int>> adj(n);
    for (int i = 0; i < m; i++) {
        int v, u;
        cin >> v >> u;
        v--; u--; // Convert 1-based input to 0-based indexing
        adj[v].push_back(u);
        adj[u].push_back(v);
    }

    vector<int> color(n, -1); // -1 means uncolored
    bool isBipartite = true;

    // Handle disconnected components
    for (int start = 0; start < n; start++) {
        if (color[start] == -1) { // If unvisited, perform BFS
            queue<Pair> q;
            q.push(Pair(start, 0));
            color[start] = 0;

            while (!q.empty()) {
                Pair curr = q.front();
                q.pop();
                int nc = 1 - curr.c;

                for (int neighbor : adj[curr.v]) {
                    if (color[neighbor] == -1) {
                        color[neighbor] = nc;
                        q.push(Pair(neighbor, nc));
                    } else if (color[neighbor] == curr.c) {
                        isBipartite = false;
                        break;
                    }
                }
                if (!isBipartite) break;
            }
        }
    }

    if (isBipartite) {
        for (int i = 0; i < n; i++) {
            cout << color[i] + 1 << " "; // Convert 0,1 to 1,2 for output
        }
        cout << "\n";
    } else {
        cout << "IMPOSSIBLE\n";
    }

    return 0;
}
