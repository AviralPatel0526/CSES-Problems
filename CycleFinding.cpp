#include <bits/stdc++.h>
using namespace std;

const int MAX_N = 2500;  // Adjust based on constraints
const long long INF = LLONG_MAX;

struct Edge {
    int src, dest;
    long long wt;
};

int n, m;
Edge edges[MAX_N * 10];  // Assuming max 10 edges per node
long long dist[MAX_N];
int parent[MAX_N];

int main() {
    scanf("%d %d", &n, &m); // Fast input

    for (int i = 0; i < m; i++) {
        int src, dest;
        long long wt;
        scanf("%d %d %lld", &src, &dest, &wt);
        edges[i] = {src - 1, dest - 1, wt}; // 0-based indexing
    }

    fill(dist, dist + n, 0);  // ✅ Initialize all distances to 0 for cycle detection
    fill(parent, parent + n, -1);

    int v = -1;

    for (int i = 0; i < n; i++) {
        v = -1;
        for (int j = 0; j < m; j++) {
            Edge e = edges[j];
            if (dist[e.src] != INF && dist[e.src] + e.wt < dist[e.dest]) {
                dist[e.dest] = dist[e.src] + e.wt;
                parent[e.dest] = e.src;
                v = e.dest;
            }
        }
    }

    if (v == -1) {
        printf("NO\n"); // No negative cycle
    } else {
        printf("YES\n");

        // ✅ Move `v` inside the cycle (necessary for correct cycle detection)
        for (int i = 0; i < n; i++) {
            v = parent[v];
        }

        // Recover the negative cycle
        vector<int> cycle;
        int curr = v;
        do {
            cycle.push_back(curr + 1); // 1-based indexing for output
            curr = parent[curr];
        } while (curr != v);
        cycle.push_back(v + 1);

        reverse(cycle.begin(), cycle.end());
        for (int node : cycle) {
            printf("%d ", node);
        }
        printf("\n");
    }

    return 0;
}
