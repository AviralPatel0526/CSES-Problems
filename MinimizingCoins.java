import java.util.Arrays;
import java.util.Scanner;

class MinimizingCoins {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();  // Input size of array
        int x = in.nextInt();  // Input target sum
        int a[] = new int[n];  // Array of elements
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();  // Input array elements
        }

        int dp[] = new int[x + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);  // Initialize dp array with a large number
        dp[0] = 0;  // Base case: 0 elements to sum to 0

        for (int i = 1; i <= x; i++) {
            for (int j = 0; j < n; j++) {
                if (a[j] <= i && dp[i - a[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - a[j]] + 1);  // Update dp[i] only if valid
                }
            }
        }

        // If no valid combination was found, dp[x] will still be Integer.MAX_VALUE
        if (dp[x] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[x]);
        }
    }
}