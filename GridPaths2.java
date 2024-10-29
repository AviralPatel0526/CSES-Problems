import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Grid_Paths
 */
public class GridPaths2 {

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
    
    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        char ch[][]=new char[n][n];
        for(int i=0;i<n;i++){
            ch[i]=in.next().toCharArray();
        }
        int dp[][]=new int[n][n];
        for(int i=0;i<n;i++){
            if(ch[0][i] == '*') break;
            dp[0][i]=1;
        }
        for(int i=0;i<n;i++){
            if(ch[i][0] == '*') break;
            dp[i][0]=1;
        }
        int mod=1000000007;
        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                if(ch[i][j] != '*'){

                    dp[i][j]=(dp[i-1][j]%mod+dp[i][j-1]%mod)%mod;
                }
            }
        }
        System.out.println(dp[n-1][n-1]);
    }
}