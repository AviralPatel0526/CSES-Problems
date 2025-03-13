import java.util.*;
import java.lang.*;
import java.io.*;
public class Monsters {

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
        int i, j, t;

        Triplet(int i, int j, int t) {
            this.i = i;
            this.j = j;
            this.t = t;
        }
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        char ch[][] = new char[n][m];

        boolean vis[][] = new boolean[n][m];
        boolean visa[][] = new boolean[n][m];
        int val[][] = new int[n][m];
        Queue<Triplet> q = new LinkedList<>();
        Queue<Triplet> qa = new LinkedList<>();

        // Initialize val[][] with a large value
        for (int i = 0; i < n; i++) {
            Arrays.fill(val[i], Integer.MAX_VALUE);
        }

        int startX = -1, startY = -1;
        boolean fg=false;
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < m; j++) {
                ch[i][j] = s.charAt(j);
                if (ch[i][j] == 'M') {
                    q.add(new Triplet(i, j, 0));
                    vis[i][j] = true;
                    val[i][j] = 0;  // Monster position set to zero
                }
                if (ch[i][j] == 'A') {
                    if(i == n-1 || i == 0 || j == 0 || j == m-1){
                        fg=true;
                    }
                    qa.add(new Triplet(i, j, 0));
                    visa[i][j] = true;
                    startX = i;
                    startY = j;
                }
            }
        }
        if(fg){
            System.out.println("YES");
            System.out.println(0);
           
        }else{
            int dx[] = {1, 0, -1, 0};
        int dy[] = {0, 1, 0, -1};

        // Step 1: BFS for Monsters to fill `val[][]`
        while (!q.isEmpty()) {
            Triplet curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.i + dx[i];
                int ny = curr.j + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !vis[nx][ny] && ch[nx][ny] == '.') {
                    vis[nx][ny] = true;
                    val[nx][ny] = curr.t + 1;
                    q.add(new Triplet(nx, ny, curr.t + 1));
                }
            }
        }

        // Parent matrix to store moves
        char parent[][] = new char[n][m];

        int bx = -1, by = -1;
        
        // Step 2: BFS for Player 'A'
        outer:
        while (!qa.isEmpty()) {
            Triplet curr = qa.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.i + dx[i];
                int ny = curr.j + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visa[nx][ny] && ch[nx][ny] == '.') {
                    visa[nx][ny] = true;

                    // Store the move direction
                    if (i == 0) parent[nx][ny] = 'D'; // Moving Down
                    else if (i == 1) parent[nx][ny] = 'R'; // Moving Right
                    else if (i == 2) parent[nx][ny] = 'U'; // Moving Up
                    else parent[nx][ny] = 'L'; // Moving Left

                    // Check if 'A' can escape
                    if ((nx == 0 || ny == 0 || nx == n - 1 || ny == m - 1) && val[nx][ny] > curr.t + 1) {
                        bx = nx;
                        by = ny;
                        break outer;
                    }

                    qa.add(new Triplet(nx, ny, curr.t + 1));
                }
            }
        }

        // Step 3: Backtracking to reconstruct the path
        if (bx != -1 && by != -1) {
            System.out.println("YES");

            StringBuilder path = new StringBuilder();
            while (bx != startX || by != startY) {
                char move = parent[bx][by];
                path.append(move);
                
                if (move == 'U') bx++;
                else if (move == 'D') bx--;
                else if (move == 'L') by++;
                else if (move == 'R') by--;
            }

            System.out.println(path.length());
            System.out.println(path.reverse().toString()); // Reverse to get the correct order
        } else {
            System.out.println("NO");
        }

        }

        
    }
}