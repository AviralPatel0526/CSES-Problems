import java.util.*;
import java.lang.*;
import java.io.*;
public class LongestFlightRoute {

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
    static class Pair{
        int v;
        int wt;
        Pair(int v,int wt){
            this.v=v;
            this.wt=wt;
        }
    }
    public static void main(String[] args) throws java.lang.Exception {
        int n=in.nextInt();
        int m=in.nextInt();
        ArrayList<ArrayList<Pair>> adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            int s=in.nextInt()-1;
            int d=in.nextInt()-1;
            adj.get(s).add(new Pair(d,-1));
        }
        Queue<Pair> pq=new LinkedList<>();
        pq.add(new Pair(0, 0));
        int distance[]=new int[n];
        int parent[]=new int[n];
        Arrays.fill(parent, -1);
        distance[0]=0;
        parent[0]=0;
        Arrays.fill(distance,Integer.MAX_VALUE);
        while(!pq.isEmpty()){
            Pair curr=pq.poll();
            for(int i=0;i<adj.get(curr.v).size();i++){
                if(adj.get(curr.v).get(i).wt+curr.wt < distance[adj.get(curr.v).get(i).v]){
                    distance[adj.get(curr.v).get(i).v]=adj.get(curr.v).get(i).wt+curr.wt;
                    pq.add(new Pair(adj.get(curr.v).get(i).v, adj.get(curr.v).get(i).wt+curr.wt));
                    parent[adj.get(curr.v).get(i).v]=curr.v;
                }
            }
        }
        if(distance[n-1] == Integer.MAX_VALUE){
            System.out.println("IMPOSSIBLE");
        }else{
            // System.out.println(-1*distance[n-1]+1);
            ArrayList<Long> ans=new ArrayList<>();
            int v=n-1;
            while(parent[v] != v){
                ans.add((long)v + 1);
                v=parent[v];
            }
            ans.add(1l);
            Collections.reverse(ans);
            System.out.println(ans.size());
            lout(ans);

        }
    }
}