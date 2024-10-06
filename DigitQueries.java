import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Digit_Queries
 */
public class DigitQueries {

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
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 1; i < 18; i++) {
            list.add((long) (9 * Math.pow(10, i - 1)) * i);
        }
        int n = in.nextInt();
        for (int j = 1; j <= n; j++) {
            long k = in.nextLong();
            for (int i = 1; i < 18; i++) {
                if (list.get(i - 1) >= k) {
                    if (i == 1) {
                        System.out.println(k);
                    } else {
                        long q = (k - 1) / i;
                        long r = (k - 1) % i;
                        long num = (long) Math.pow(10, i - 1) + q;
                        String s = Long.toString(num);
                        System.out.println(s.charAt((int) r));
                    }
                    break; 
                } else {
                    k -= list.get(i - 1);
                }
            }
        }
    }
    
}