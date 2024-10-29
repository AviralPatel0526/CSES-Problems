#include <iostream>
#include <vector>
#include <cstring> // For memset

using namespace std;

const int MOD = 1000000007;

long long f(int total, int sum, int a[], long long dp[]) {
    if (dp[sum] != -1) {
        return dp[sum];
    }
    if (sum == total) {
        return 1;
    }
    if (sum > total) {
        return 0;
    }

    long long ways = 0;
    for (int j = 0; j < 6; j++) {
        if (a[j] + sum <= total) {
            ways = (ways % MOD + f(total, sum + a[j], a, dp) % MOD) % MOD;
        }
    }

    return dp[sum] = ways % MOD;
}

int main() {
    int n;
    cin >> n; // Input target sum
    int a[] = {1, 2, 3, 4, 5, 6}; // Array of elements
    
    // Create dp array, large enough to handle inputs
    long long dp[10000001];
    memset(dp, -1, sizeof(dp)); // Fill dp array with -1
    
    cout << f(n, 0, a, dp) << endl;
    
    return 0;
}
