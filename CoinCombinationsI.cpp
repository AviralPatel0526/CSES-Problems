#include <iostream>
#include <vector>

using namespace std;

int main() {
    int n, x;
    cin >> n >> x; 
    vector<int> a(n);
    
    for (int i = 0; i < n; i++) {
        cin >> a[i]; 
    }

    const long long mod = 1000000007;
    vector<long long> dp(x + 1, 0);
    dp[0] = 1; 

    for (int i = 1; i <= x; i++) {
        for (int j = 0; j < n; j++) {
            if (a[j] <= i) {
                dp[i] = (dp[i] + dp[i - a[j]]) % mod; 
            }
        }
    }

    cout << dp[x] << endl; 
    return 0;
}
