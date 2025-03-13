#include <bits/stdc++.h>
using namespace std;

struct Pair {
    int i, j;
    Pair(int i, int j) : i(i), j(j) {}
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int n, m;
    cin >> n >> m;
    
    vector<vector<char>> ch(n, vector<char>(m));
    queue<Pair> q;
    vector<vector<bool>> vis(n, vector<bool>(m, false));
    
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < m; j++) {
            ch[i][j] = s[j];
            if (ch[i][j] == 'A') {
                q.push(Pair(i, j));
                vis[i][j] = true;
            }
        }
    }

    vector<vector<char>> parent(n, vector<char>(m));
    int dx[] = {1, 0, -1, 0};
    int dy[] = {0, 1, 0, -1};
    
    bool flag = false;
    int bx = -1, by = -1;
    
    while (!q.empty()) {
        Pair curr = q.front();
        q.pop();
        for (int i = 0; i < 4; i++) {
            int nx = curr.i + dx[i];
            int ny = curr.j + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (ch[nx][ny] != '#' && !vis[nx][ny]) {
                    vis[nx][ny] = true;
                    if (i == 0) parent[nx][ny] = 'U';
                    else if (i == 1) parent[nx][ny] = 'L';
                    else if (i == 2) parent[nx][ny] = 'D';
                    else parent[nx][ny] = 'R';
                    
                    if (ch[nx][ny] == 'B') {
                        bx = nx;
                        by = ny;
                        flag = true;
                        break;
                    }
                    q.push(Pair(nx, ny));
                }
            }
        }
        if (flag) break;
    }

    if (flag) {
        cout << "YES\n";
        vector<char> list;
        while (ch[bx][by] != 'A') {
            if (parent[bx][by] == 'U') {
                list.push_back('D');
                bx--;
            } else if (parent[bx][by] == 'D') {
                list.push_back('U');
                bx++;
            } else if (parent[bx][by] == 'L') {
                list.push_back('R');
                by--;
            } else {
                list.push_back('L');
                by++;
            }
        }
        cout << list.size() << '\n';
        reverse(list.begin(), list.end());
        for (char c : list) cout << c;
        cout << '\n';
    } else {
        cout << "NO\n";
    }

    return 0;
}
