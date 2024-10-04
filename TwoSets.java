import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Two_Sets
 */
public class TwoSets {

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
        long n = in.nextLong();
        long nc=n;
        long psum=(n*(n+1))/2;
        if(psum%2 != 0){
            System.out.println("NO");
        }else{
            StringBuilder sb1=new StringBuilder();
            StringBuilder sb2=new StringBuilder();
            if(n%2 != 0){
                sb1.append(n+" ");
                n--;
            }
            long i=1;
            long j=n;
            while(i < j){
                if(i%2 != 0){
                    sb2.append(i+" "+j+" ");
                }else{
                    sb1.append(i+" "+j+" ");
                }
                i++;
                j--;
            }
            System.out.println("YES");
            System.out.println(nc/2);
            System.out.println(sb1.toString());
            System.out.println((int)Math.ceil(nc / 2.0));

            System.out.println(sb2.toString());
        }
    }
}