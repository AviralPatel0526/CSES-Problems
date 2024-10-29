import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Removing_Digits
 */
public class RemovingDigits {

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
    // static int[] dp;

    // static int f(int sum) {
    //     if (sum == 0) return 0;
    //     if (dp[sum] != -1) return dp[sum];
        
    //     int n = (int) Math.log10(sum) + 1;
    //     int min = Integer.MAX_VALUE;
    //     for (int i = 0; i < n; i++) {
    //         int r = (sum / (int) (Math.pow(10, i))) % 10;
    //         if (r > 0) {
    //             min = Math.min(min, 1 + f(sum - r));
    //         }
    //     }
        
    //     dp[sum] = min;
    //     return dp[sum];
    // }

    public static void main(String[] args) throws java.lang.Exception {
        int sum = in.nextInt();
        int dp[] = new int[sum + 1];  
        Arrays.fill(dp, Integer.MAX_VALUE); // Initialize dp array with large value
        dp[0] = 0; // It takes 0 steps to reduce 0 to 0
        
        for (int j = 1; j <= sum; j++) {
            int n = (int) Math.log10(j) + 1;
            for (int i = 0; i < n; i++) {
                int r = (j / (int) (Math.pow(10, i))) % 10;
                if (r > 0 && j >= r) { // Ensure j - r is non-negative
                    dp[j] = Math.min(dp[j], 1 + dp[j - r]);
                }
            }
        }
        System.out.println(dp[sum]);
    }
}