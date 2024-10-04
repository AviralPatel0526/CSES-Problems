import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Palindrome_Reorder
 */
public class PalindromeReorder {

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
        String s = in.next();
        int n = s.length();
        int a[] = new int[26];
        
        for (int i = 0; i < n; i++) {
            a[s.charAt(i) - 'A']++;
        }
        
        int cnt = 0;
        char b = ' ';
        char ch[] = new char[n];
        
        // Count odd occurrences
        for (int i = 0; i < 26; i++) {
            if (a[i] % 2 != 0) {
                cnt++;
                b = (char) ('A' + i);
            }
        }
        
        // If more than one character has an odd count, no solution
        if (cnt > 1) {
            System.out.println("NO SOLUTION");
        } else {
            // If odd length string, place the middle character
            if (n % 2 != 0) {
                ch[n / 2] = b;
                a[b - 'A']--;  // Decrement count of the middle character
            }
            
            int i = 0;
            int j = n - 1;
            
            // Place remaining characters symmetrically
            for (int k = 0; k < 26; k++) {
                char c = (char) ('A' + k);
                while (a[k] > 0) {
                    ch[i++] = c;
                    ch[j--] = c;
                    a[k] -= 2;  // Reduce count by 2
                }
            }
            
            System.out.println(new String(ch));
        }
    }
}