#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, m, q;
    cin >> n >> m >> q;
    
    vector<vector<long long>> distance(n, vector<long long>(n, 1000000000000LL));

    for (int i = 0; i < n; i++) {
        distance[i][i] = 0;
    }

    for (int i = 0; i < m; i++) {
        int src, dest;
        long long wt;
        cin >> src >> dest >> wt;
        src--; dest--; // Convert 1-based to 0-based indexing
        distance[src][dest] = min(distance[src][dest], wt);
        distance[dest][src] = min(distance[dest][src], wt); // Ensure minimum weight in case of multiple edges
    }

    for (int i = 0; i < n; i++) { // Floyd-Warshall Algorithm
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                distance[j][k] = min(distance[j][k], distance[j][i] + distance[i][k]);
            }
        }
    }

    for (int i = 0; i < q; i++) {
        int src, dest;
        cin >> src >> dest;
        src--; dest--; // Convert 1-based to 0-based indexing
        if (distance[src][dest] == 1000000000000LL) {
            cout << -1 << '\n';
        } else {
            cout << distance[src][dest] << '\n';
        }
    }

    return 0;
}
