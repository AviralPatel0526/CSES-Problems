import java.util.*;
import java.lang.*;
import java.lang.reflect.Array;
import java.io.*;
public class RoundTripII {

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
    public static boolean dfs(int v, boolean vis[], boolean pathvis[], ArrayList<Integer> list, ArrayList<ArrayList<Integer>> adj) {
        list.add(v);
        vis[v] = true;
        pathvis[v] = true;
        
        for (int i = 0; i < adj.get(v).size(); i++) {
            int neighbor = adj.get(v).get(i);
            if (!vis[neighbor]) {
                if (dfs(neighbor, vis, pathvis, list, adj)) {
                    return true;
                }
            } else if (pathvis[neighbor]) {
                list.add(neighbor);  
                return true;
            }
        }

        pathvis[v] = false;
        list.remove(list.size() - 1);
        return false;
    }
    public static void main(String[] args) throws java.lang.Exception {
        int n=in.nextInt();
        int m=in.nextInt();
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int i=0;i<m;i++){
            int src=in.nextInt()-1;
            int dest=in.nextInt()-1;
            adj.get(src).add(dest);
        }
        boolean pathvis[]=new boolean[n];
        boolean vis[]=new boolean[n];
        ArrayList<Integer> list=new ArrayList<>();
        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (!vis[i] && dfs(i, vis, pathvis, list, adj)) {
                found = true;
                break;
            }
        }
        if(found){
            int last=list.get(list.size()-1);
            boolean f=false;
            ArrayList<Long> ans=new ArrayList<>();
            for(int i=0;i<list.size();i++){
                if(list.get(i) == last) f=true;
                if(f){
                    ans.add((long)list.get(i)+1);
                }
            }
            System.out.println(ans.size());
            lout(ans);
        }else{
            System.out.println("IMPOSSIBLE");
        }
        
    }
}