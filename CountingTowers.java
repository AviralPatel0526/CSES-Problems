import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Counting_Towers
 */
public class CountingTowers {

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


    static int mod = 1000000007;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long dp[][]=new long[1000000][5];
        dp[0]=new long[]{2,1,1,1,1};
        for(int i=1;i<1000000;i++){
            dp[i][0]=(dp[i-1][0]%mod+dp[i-1][1]%mod)%mod;
            dp[i][1]=(dp[i-1][0]%mod+dp[i-1][1]%mod)%mod;
            dp[i][0]=(dp[i][0]%mod+dp[i-1][0]%mod+dp[i-1][2]%mod+dp[i-1][3]%mod+dp[i-1][4%mod])%mod;
            dp[i][4]=(dp[i-1][0]%mod+dp[i-1][2]%mod+dp[i-1][3]%mod+dp[i-1][4]%mod)%mod;
            dp[i][2]=(dp[i-1][0]%mod+dp[i-1][2]%mod+dp[i-1][3]%mod+dp[i-1][4]%mod)%mod;
            dp[i][3]=(dp[i-1][0]%mod+dp[i-1][2]%mod+dp[i-1][3]%mod+dp[i-1][4]%mod)%mod;
        }
        int t = in.nextInt();  // Number of test cases
        while (t-- > 0) {
            int n = in.nextInt();  // Height of the tower
            System.out.println(dp[n-1][0]);
        }
    }
}