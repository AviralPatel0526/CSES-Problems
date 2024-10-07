import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Ferris_Wheel
 */
public class FerrisWheel {

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
    
    public static void countingSortByDigit(long[] arr, int exp) {
        long[] output = new long[arr.length];
        long[] count = new long[10];
    
        for (long num : arr) {
            count[(int)((num / exp) % 10)]++;
        }
    
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
    
        for (int i = arr.length - 1; i >= 0; i--) {
            output[(int)count[(int)((arr[i] / exp) % 10)] - 1] = arr[i];
            count[(int)((arr[i] / exp) % 10)]--;
        }
    
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
    
    public static void radixSort(long[] arr) {
        long max = arr[0];
        for (long num : arr) {
            if (num > max) max = num;
        }
    
        for (long exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, (int)exp);
        }
    }
    
    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        long x = in.nextLong();
        long a[] = al(n);
        radixSort(a);
        int cnt = 0;
        int i = 0;
        int j = n - 1;
        while (i <= j) {
            if (a[i] + a[j] <= x) {
                i++;
                j--;
            } else {
                j--;
            }
            cnt++;
        }
        System.out.println(cnt);
    }
    
}