import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Gray_Code
 */
public class GrayCode {

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
        int n = in.nextInt();  // Assuming 'in' is an instance of your input handler
        ArrayList<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
    
        for (int i = 2; i <= n; i++) {
            int z = list.size();
    
            // Append the reversed list with '1' prefixed
            for (int j = z - 1; j >= 0; j--) {
                list.add("1" + list.get(j));
            }
    
            // Prefix the original list with '0'
            for (int j = 0; j < z; j++) {
                list.set(j, "0" + list.get(j));
            }
        }
    
        for (String i : list) {
            System.out.println(i);
        }
    }
    
    
}