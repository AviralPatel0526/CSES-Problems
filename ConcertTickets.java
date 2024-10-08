import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Concert_Tickets
 */
public class ConcertTickets {

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
        int m = in.nextInt();
        
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>(); 
        
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            a.add(val);
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        
        int ans[] = new int[m];
        
        for (int i = 0; i < m; i++) {
            b.add(in.nextInt());
            Integer upperBoundKey = map.floorKey(b.get(i));
            
            if (upperBoundKey != null) {
                ans[i] = upperBoundKey;
               
                int freq = map.get(upperBoundKey);
                if (freq == 1) {
                    map.remove(upperBoundKey);
                } else {
                    map.put(upperBoundKey, freq - 1);
                }
            } else {
                ans[i] = -1;  
            }
        }
        
        for (int i = 0; i < m; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
    
    
    
}