import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Dice_Combinations
 */
public class DiceCombinations {

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
    // recursive + memo
    // static long f(int total,int sum,int a[],long dp[]){
    //     if(dp[sum] != -1){
    //         return dp[sum];
    //     }
    //     if (sum == total) {
    //         return 1;
    //     }
    //     if (sum > total) {
    //         return 0;
    //     }
        
    //     long ways = 0;
    //     for (int j = 0; j < 6; j++) {
    //         if (a[j] + sum <= total) {
    //             ways =((ways)%1000000007 +  f(total, sum + a[j], a,dp)%1000000007)%1000000007;
    //         }
    //     }
        
    //     return dp[sum]=ways%1000000007;
    // }
    public static void main(String[] args) throws java.lang.Exception {
        int n=in.nextInt();
        long dp[]=new long[n+1];
         
        dp[0]=1;
        int m=1000000007;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=6;j++){
                if(i - j >= 0){
                    dp[i]=(dp[i]%m + dp[i-j]%m)%m;

                }
            }
        }
        System.out.println(dp[n]);
    }
}