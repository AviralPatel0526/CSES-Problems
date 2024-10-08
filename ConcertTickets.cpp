#include <iostream>
#include <vector>
#include <map>
using namespace std;

int main() {
    int n, m;
    cin >> n >> m;
    
    vector<int> a(n);
    vector<int> b(m);
    map<int, int> mp;
    
    // Input for array 'a' and populating the map with frequencies
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        mp[a[i]]++;
    }
    
    vector<int> ans(m);
    
    // Input for array 'b' and finding the upper bound for each element in 'b'
    for (int i = 0; i < m; i++) {
        cin >> b[i];
        
        // Finding the floor key using lower_bound (reverse iterator to find the greatest key <= b[i])
        auto it = mp.lower_bound(b[i]);
        
        // If the lower bound key is greater than b[i], move to the previous element (to get floor key)
        if (it == mp.end() || it->first > b[i]) {
            if (it != mp.begin()) {
                --it;
            } else {
                it = mp.end();
            }
        }
        
        if (it != mp.end() && it->first <= b[i]) {
            ans[i] = it->first;  // store the floor key
            mp[it->first]--;     // decrement the frequency
            
            // If frequency becomes 0, remove the element from the map
            if (mp[it->first] == 0) {
                mp.erase(it);
            }
        } else {
            ans[i] = -1;  // If no valid floor key found, set -1
        }
    }
    
    // Print the result
    for (int i = 0; i < m; i++) {
        cout << ans[i] << " ";
    }
    cout << endl;

    return 0;
}
