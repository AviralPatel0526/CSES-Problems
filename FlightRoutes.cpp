#include <bits/stdc++.h>
using namespace std;

struct Pair {
    int v;
    long long wt;
    Pair(int v, long long wt) : v(v), wt(wt) {}
};

void printArray(long long arr[], int k) {
    for (int i = 0; i < k; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

int main() {
    int n, m, k;
    cin >> n >> m >> k;

    vector<vector<Pair>> adj(n);
    for (int i = 0; i < m; i++) {
        int src, dest;
        long long wt;
        cin >> src >> dest >> wt;
        src--; dest--; // Adjusting to 0-based index
        adj[src].emplace_back(dest, wt);
    }

    vector<vector<long long>> a(n, vector<long long>(k, LLONG_MAX));

    priority_queue<Pair, vector<Pair>, 
        function<bool(const Pair&, const Pair&)>> pq([](const Pair& p1, const Pair& p2) {
        return p1.wt > p2.wt;
    });

    pq.push(Pair(0, 0));
    
    while (!pq.empty()) {
        Pair curr = pq.top();
        pq.pop();

        for (auto& neighbor : adj[curr.v]) {
            int dest = neighbor.v;
            long long wt = neighbor.wt;
            if (curr.wt + wt < a[dest][k - 1]) {
                a[dest][k - 1] = curr.wt + wt;
                sort(a[dest].begin(), a[dest].end());
                pq.push(Pair(dest, curr.wt + wt));
            }
        }
    }

    printArray(a[n - 1].data(), k);

    return 0;
}
