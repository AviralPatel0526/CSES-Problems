import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Trailing_Zeros
 */
public class TrailingZeros {

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
    static long Fastpow(long a,long n){
        long ans=1;
        // long mod=1000000007;
        while(n > 0){
            if((n&1) != 0){
                // ans=((ans%mod)*(a%mod))%mod;
                ans=ans*a;
            }
            // a=((a%mod)*(a%mod))%mod;
            a=a*a;
            n=n>>1;
        }
        return ans;
    }
    public static void main(String[] args) throws java.lang.Exception {
    
       long n=in.nextLong();
       long ans=0;
       long z=1;
       while(Fastpow(5, z) <= n){
        ans+=n/Fastpow(5, z);
        z++;
       }
       System.out.println(ans);
    }
}