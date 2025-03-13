#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int n, q;
    cin >> n >> q;

    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i]--;  // Convert to 0-based indexing
    }

    // Fix: Interchange rows and columns (log first, n second)
    vector<vector<int>> binarylift(31, vector<int>(n));

    // Base case: First ancestor
    for (int i = 0; i < n; i++) {
        binarylift[0][i] = a[i];
    }

    // Compute binary lifting table
    for (int j = 1; j < 31; j++) {
        for (int i = 0; i < n; i++) {
            binarylift[j][i] = binarylift[j - 1][binarylift[j - 1][i]];
        }
    }

    vector<long long> ans;
    for (int i = 0; i < q; i++) {
        int v, k;
        cin >> v >> k;
        v--;  // Convert to 0-based indexing

        for (int j = 0; j < 31; j++) {
            if ((k & (1 << j)) != 0) {
                v = binarylift[j][v];
            }
        }

        ans.push_back(v + 1);  // Convert back to 1-based indexing
    }

    for (long long res : ans) {
        cout << res << "\n";
    }

    return 0;
}
