#include <iostream>
#include <queue>
#include <vector>
#include <string>
using namespace std;

struct Pair {
    int i, j;
    Pair(int x, int y) : i(x), j(y) {}
};

int main() {
    int n, m;
    cin >> n >> m;

    vector<string> grid(n);
    vector<vector<bool>> visited(n, vector<bool>(m, false)); // Visited grid

    for (int i = 0; i < n; i++) {
        cin >> grid[i];
    }

    int dx[] = {1, 0, -1, 0};
    int dy[] = {0, 1, 0, -1};
    int cnt = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] == '.' && !visited[i][j]) {
                cnt++;
                queue<Pair> q;
                q.push(Pair(i, j));
                visited[i][j] = true;  // Mark as visited immediately

                // BFS Traversal
                while (!q.empty()) {
                    Pair curr = q.front();
                    q.pop();

                    for (int k = 0; k < 4; k++) {
                        int nx = curr.i + dx[k];
                        int ny = curr.j + dy[k];

                        if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                            if (grid[nx][ny] == '.' && !visited[nx][ny]) {
                                visited[nx][ny] = true;  // Mark as visited
                                q.push(Pair(nx, ny));
                            }
                        }
                    }
                }
            }
        }
    }

    cout << cnt << endl;
    return 0;
}
