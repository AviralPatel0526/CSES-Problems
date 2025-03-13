import java.util.*;


import java.lang.*;
import java.io.*;
public class CycleFinding {

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
    
    // Find Power Method
    static long findPower(long num, long base) {
        if (num < 1 || base <= 1) return -1;
        double logResult = Math.log(num) / Math.log(base);
        if (Math.abs(logResult - Math.round(logResult)) < 1e-10) {
            return (long) Math.round(logResult);
        } else {
            return -1;
        }
    }
    
    // Fast Exponentiation Method using Modular Arithmetic
    static long fastExponentiation(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
    
    // Sieve Method
    static boolean[] sieve;
    static void fillSieve(int ssize) {
        sieve = new boolean[ssize + 1];
        Arrays.fill(sieve, true);
        sieve[0] = sieve[1] = false;
        for (int i = 2; i * i <= ssize; i++) {
            if (sieve[i]) {
                for (int j = i * i; j <= ssize; j += i) {
                    sieve[j] = false;
                }
            }
        }
    }
    
    // Smallest Prime Factor (SPF) Method
    static int[] spf;
    static void fillSpf(int ssize) {
        spf = new int[ssize + 1];
        for (int i = 0; i <= ssize; i++) {
            spf[i] = i;
        }
        for (int i = 2; i * i <= ssize; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= ssize; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
    }
    
    // Reverse Array Function
    static void reverse(long[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            long temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }
    
    // Print Array Elements (Space-separated)
    static void aout(long[] arr) {
        StringBuilder sb = new StringBuilder();
        for (long num : arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
    
    // Print ArrayList Elements (Space-separated)
    static void lout(ArrayList<Long> list) {
        StringBuilder sb = new StringBuilder();
        for (long num : list) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
    
    static FastReader in = new FastReader();
    
    // Input of int array
    static int[] ai(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextInt();
        return arr;
    }
    
    // Input of long array
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
    static class Triplet {
        int src;
        int dest;
        long wt;
    
        Triplet(int src, int dest, long wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }
    
    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt(); // Number of nodes
        int m = in.nextInt(); // Number of edges
    
        long[] ans = new long[n];
        Arrays.fill(ans, Long.MAX_VALUE); // Distance array
    
        ArrayList<Triplet> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int src = in.nextInt() - 1;
            int dest = in.nextInt() - 1;
            long wt = in.nextLong();
            list.add(new Triplet(src, dest, wt));
        }
    
        int[] parent = new int[n];
        Arrays.fill(parent, -1); 
    
        boolean cycleFound = false;
    
        // Run Bellman-Ford for each disconnected component
        for (int start = 0; start < n; start++) {
            if (ans[start] != Long.MAX_VALUE) continue; // Already visited
    
            ans[start] = 0; // Start from this node
            int v = -1;
    
            for (int i = 0; i < n; i++) {
                v = -1;
                for (Triplet edge : list) {
                    if (ans[edge.src] != Long.MAX_VALUE && ans[edge.src] + edge.wt < ans[edge.dest]) {
                        ans[edge.dest] = ans[edge.src] + edge.wt;
                        parent[edge.dest] = edge.src;
                        v = edge.dest;
                    }
                }
            }
    
            if (v != -1) { 
                cycleFound = true;
                System.out.println("YES");
    
                for (int i = 0; i < n; i++) {
                    v = parent[v];
                }
    
                ArrayList<Integer> cycle = new ArrayList<>();
                int curr = v;
                do {
                    cycle.add(curr + 1);
                    curr = parent[curr];
                } while (curr != v);
                cycle.add(v + 1);
    
                Collections.reverse(cycle);
                for (int node : cycle) {
                    System.out.print(node + " ");
                }
                System.out.println();
                break; 
            }
        }
    
        if (!cycleFound) {
            System.out.println("NO");
        }
    }
    
}