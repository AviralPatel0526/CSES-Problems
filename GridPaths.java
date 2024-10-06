import java.util.Scanner;

public class GridPaths {

    static final int n = 7;                // Grid size
    static final int total_steps = 48;     // Total number of steps required
    static boolean[][] visited = new boolean[n][n]; // Visited cells tracker
    static int[] reserved = new int[49];   // Reserved array for paths
    static String path;                     // Path input by the user

    // Recursive function to explore all possible moves
    public static void move(int row, int col, int[] ans, int[] steps) {
        // If we've reached the bottom-left corner of the grid
        if (row == n - 1 && col == 0) {
            if (steps[0] == total_steps) {
                ans[0]++;
            }
            return;
        }

        // Check if we hit a wall or cannot move further
        if (((row + 1 == n || (row > 0 && visited[row - 1][col] && visited[row + 1][col])) && col - 1 >= 0 && col + 1 < n && !visited[row][col - 1] && !visited[row][col + 1])
            || ((col + 1 == n || (col > 0 && visited[row][col - 1] && visited[row][col + 1])) && row - 1 >= 0 && row + 1 < n && !visited[row - 1][col] && !visited[row + 1][col])
            || ((row == 0 || (row + 1 < n && visited[row + 1][col] && visited[row - 1][col])) && col - 1 >= 0 && col + 1 < n && !visited[row][col - 1] && !visited[row][col + 1])
            || ((col == 0 || (col + 1 < n && visited[row][col + 1] && visited[row][col - 1])) && row - 1 >= 0 && row + 1 < n && !visited[row - 1][col] && !visited[row + 1][col])) {
            return;
        }

        visited[row][col] = true; // Mark the current cell as visited

        if (path.charAt(steps[0]) != '?') {
            // Move based on path direction (U, R, D, L)
            switch (path.charAt(steps[0])) {
                case 'U':
                    if (row - 1 >= 0 && !visited[row - 1][col]) {
                        steps[0]++;
                        move(row - 1, col, ans, steps);
                        steps[0]--;
                    }
                    break;
                case 'R':
                    if (col + 1 < n && !visited[row][col + 1]) {
                        steps[0]++;
                        move(row, col + 1, ans, steps);
                        steps[0]--;
                    }
                    break;
                case 'D':
                    if (row + 1 < n && !visited[row + 1][col]) {
                        steps[0]++;
                        move(row + 1, col, ans, steps);
                        steps[0]--;
                    }
                    break;
                case 'L':
                    if (col - 1 >= 0 && !visited[row][col - 1]) {
                        steps[0]++;
                        move(row, col - 1, ans, steps);
                        steps[0]--;
                    }
                    break;
            }
        } else {
            // '?' allows to move in all four directions
            // Move down
            if (row + 1 < n && !visited[row + 1][col]) {
                steps[0]++;
                move(row + 1, col, ans, steps);
                steps[0]--;
            }

            // Move right
            if (col + 1 < n && !visited[row][col + 1]) {
                steps[0]++;
                move(row, col + 1, ans, steps);
                steps[0]--;
            }

            // Move up
            if (row - 1 >= 0 && !visited[row - 1][col]) {
                steps[0]++;
                move(row - 1, col, ans, steps);
                steps[0]--;
            }

            // Move left
            if (col - 1 >= 0 && !visited[row][col - 1]) {
                steps[0]++;
                move(row, col - 1, ans, steps);
                steps[0]--;
            }
        }
        visited[row][col] = false; // Backtrack by marking the cell as unvisited
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        path = sc.nextLine(); // Input the path

        // Initialize the reserved array based on input path
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '?') {
                reserved[i] = -1;
            } else if (path.charAt(i) == 'U') {
                reserved[i] = 0;
            } else if (path.charAt(i) == 'R') {
                reserved[i] = 1;
            } else if (path.charAt(i) == 'D') {
                reserved[i] = 2;
            } else if (path.charAt(i) == 'L') {
                reserved[i] = 3;
            }
        }

        // Answer and steps are passed as arrays to maintain mutable state in recursion
        int[] ans = {0};
        int[] steps = {0};

        // Start from top-left corner (0, 0) and try to find valid paths
        move(0, 0, ans, steps);

        // Output the result
        System.out.println(ans[0]);

        sc.close();
    }
}
