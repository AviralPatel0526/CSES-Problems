#include <bits/stdc++.h>
using namespace std;

class DSU {
public:
    vector<int> parent, size;
    unordered_set<int> set;
    int maxSize;

    DSU(int n) {
        parent.resize(n);
        size.assign(n, 1);
        maxSize = 1;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            set.insert(i);
        }
    }

    int findParent(int v) {
        if (parent[v] == v)
            return v;
        return parent[v] = findParent(parent[v]); // Path compression
    }

    void unionBySize(int u, int v) {
        int rootU = findParent(u);
        int rootV = findParent(v);
        if (rootU == rootV) return;

        if (size[rootU] < size[rootV]) {
            parent[rootU] = rootV;
            size[rootV] += size[rootU];
            maxSize = max(maxSize, size[rootV]);
            set.erase(rootU);
        } else {
            parent[rootV] = rootU;
            size[rootU] += size[rootV];
            maxSize = max(maxSize, size[rootU]);
            set.erase(rootV);
        }
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;
    DSU dsu(n);

    for (int i = 0; i < m; i++) {
        int s, d;
        cin >> s >> d;
        s--, d--; // Convert to 0-based index
        dsu.unionBySize(s, d);
        cout << dsu.set.size() << " " << dsu.maxSize << "\n";
    }

    return 0;
}
