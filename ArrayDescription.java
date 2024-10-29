import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Array_Description
 */
public class ArrayDescription {

    // GCD Method
    static long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
    
    // LCM Method
    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
    
    static FastReader in = new FastReader();
    // input of int array
    static int[] ai(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextInt();
        return arr;
    }
    
    // input of long array
    static long[] al(int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextLong();
        return arr;
    }
    
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
    
        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
    
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
    
        int nextInt() {
            return Integer.parseInt(next());
        }
    
        long nextLong() {
            return Long.parseLong(next());
        }
    
        double nextDouble() {
            return Double.parseDouble(next());
        }
    
        String nextLine() {
            String str = "";
            try {
                str = br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // Length of the array
        int m = in.nextInt(); // Maximum value in the array
        int[] a = new int[n]; // Input array

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int dp[][] = new int[n+1][m+1];

        // Initialize the first row
        if (a[0] == 0) {
            for (int i = 1; i <= m; i++) {
                dp[1][i] = 1;
            }
        } else {
            dp[1][a[0]] = 1;
        }
        int mod=1000000007;
        // Fill the DP array
        for (int i = 2; i <= n; i++) {
            if (a[i-1] != 0) {
                int prevVal = a[i-1];
                // Handle bounds when accessing dp[i-1][prevVal-1] and dp[i-1][prevVal+1]
                dp[i][prevVal] = dp[i-1][prevVal];
                if (prevVal > 1) {
                    dp[i][prevVal] =(dp[i][prevVal]%mod + dp[i-1][prevVal-1]%mod)%mod;
                }
                if (prevVal < m) {
                    dp[i][prevVal] =(dp[i][prevVal]%mod + dp[i-1][prevVal+1]%mod);
                }
                continue;
            }
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i-1][j];
                if (j > 1) {
                    dp[i][j] = (dp[i][j]%mod + dp[i-1][j-1]%mod)%mod;
                }
                if (j < m) {
                    dp[i][j] = (dp[i][j]%mod + dp[i-1][j+1]%mod)%mod;
                }
            }
        }
        int ans=0;
        // Print the DP table
        for (int i = 1; i <= m; i++) {
            ans=(ans%mod + dp[n][i]%mod)%mod;
        }
        System.out.println(ans);
    }
}