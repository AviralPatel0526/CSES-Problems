#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n, x;
    cin >> n >> x;
    vector<int> a(n), b(n);
    for (int i = 0; i < n; ++i) cin >> a[i];
    for (int i = 0; i < n; ++i) cin >> b[i];

    vector<vector<int>> dp(n + 1, vector<int>(x + 1, 0));

    for (int i = 1; i <= n; i++) {
        for (int j = 0; j <= x; j++) {
            int w = a[i - 1];
            int v = b[i - 1];
            int pick = (j >= w ? dp[i - 1][j - w] + v : 0);
            int skip = dp[i - 1][j];
            dp[i][j] = max(pick, skip);
        }
    }

    cout << dp[n][x] << endl;

    return 0;
}
