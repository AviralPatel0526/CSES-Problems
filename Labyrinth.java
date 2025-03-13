import java.util.*;
import java.lang.*;
import java.io.*;
public class Labyrinth {

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
        int i;
        int j;
        Pair(int i,int j){
            this.i=i;
            this.j=j;
        }
    }
    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int m = in.nextInt();
        char ch[][] = new char[n][m];
        Queue<Pair> q=new LinkedList<>();
        boolean vis[][]=new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < m; j++) {
                ch[i][j] = s.charAt(j);
                if(ch[i][j] == 'A'){
                    q.add(new Pair(i,j));
                    vis[i][j]=true;
                }
            }
        }
        char parent[][]=new char[n][m];
        int dx[]={1,0,-1,0};
        int dy[]={0,1,0,-1};
        boolean flag=false;
        int bx=-1;
        int by=-1;
        outer:while(!q.isEmpty()){
            Pair curr=q.poll();
            for(int i=0;i<4;i++){
                int nx=curr.i+dx[i];
                int ny=curr.j+dy[i];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m){
                    if((ch[nx][ny] != '#') && (!vis[nx][ny])){
                        vis[nx][ny]=true;
                        if(i==0){
                            parent[nx][ny]='U';
                        }else if(i == 1){
                            parent[nx][ny]='L';
                        }else if(i == 2){
                            parent[nx][ny]='D';
                        }else{
                            parent[nx][ny]='R';
                        }
                        if(ch[nx][ny] == 'B'){
                            bx=nx;
                            by=ny;
                            flag=true;
                            break outer;
                        }
                        q.add(new Pair(nx,ny));
                    }
                }
            }
        }
        if(flag){
            System.out.println("YES");
            ArrayList<Character> list=new ArrayList<>();
            while(ch[bx][by] != 'A'){
                if(parent[bx][by] == 'U'){
                    list.add('D');
                    bx--;
                }else if(parent[bx][by] == 'D'){
                    list.add('U');
                    bx++;
                }else if(parent[bx][by] == 'L'){
                    list.add('R');
                    by--;
                }else{
                    list.add('L');
                    by++;
                }
            }
            System.out.println(list.size());
            String ans="";
            for(int i=list.size()-1;i>=0;i--){
                ans+=list.get(i);
            }
            System.out.println(ans);
        }else{
            System.out.println("NO");
        }
    }
}