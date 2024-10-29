#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

const int mod = 1000000007;

int main() {
    int n, m;
    cin >> n >> m;
    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    vector<vector<int>> dp(n + 1, vector<int>(m + 2, 0));

    // Base case: when i == n, the result is 1
    for (int j = 0; j <= m + 1; j++) {
        dp[n][j] = 1;
    }

    // Fill the dp table from bottom to top
    for (int i = n - 1; i >= 0; i--) {
        for (int prev = -1; prev <= m; prev++) {
            if (a[i] != 0) {
                // If a[i] is non-zero, check if it can be selected based on prev
                if (prev == -1 || abs(a[i] - prev) <= 1) {
                    dp[i][prev + 1] = dp[i + 1][a[i] + 1];
                } else {
                    dp[i][prev + 1] = 0;
                }
            } else {
                // If a[i] is zero, try all possible values from 1 to m
                dp[i][prev + 1] = 0;
                for (int j = 1; j <= m; j++) {
                    if (prev == -1 || abs(j - prev) <= 1) {
                        dp[i][prev + 1] = (dp[i][prev + 1] + dp[i + 1][j + 1]) % mod;
                    }
                }
            }
        }
    }

    // Output the result
    cout << dp[0][0] << endl;

    return 0;
}
