import java.util.*;
import java.lang.*;
import java.io.*;
public class CountingRooms {

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
    
//     static class Pair {
//         int i, j;
//         Pair(int i, int j) {
//             this.i = i;
//             this.j = j;
//         }
//     }
    
//     public static void main(String[] args) throws java.lang.Exception {
//         Scanner in = new Scanner(System.in);
//         int n = in.nextInt();
//         int m = in.nextInt();
//         char ch[][] = new char[n][m];
    
//         for (int i = 0; i < n; i++) {
//             String s = in.next();
//             for (int j = 0; j < m; j++) {
//                 ch[i][j] = s.charAt(j);
//             }
//         }
    
//         boolean vis[][] = new boolean[n][m];
//         int cnt = 0;
//         int dx[] = {0, 1, 0, -1};
//         int dy[] = {1, 0, -1, 0};
    
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 if (ch[i][j] == '.' && !vis[i][j]) {
//                     cnt++;
//                     Queue<Pair> q = new LinkedList<>();
//                     q.add(new Pair(i, j));
//                     vis[i][j] = true; // Mark as visited when adding to the queue
                    
//                     while (!q.isEmpty()) {
//                         Pair curr = q.poll();
//                         for (int k = 0; k < 4; k++) {
//                             int nx = curr.i + dx[k];
//                             int ny = curr.j + dy[k];
    
//                             if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
//                                 if (ch[nx][ny] == '.' && !vis[nx][ny]) {
//                                     q.add(new Pair(nx, ny));
//                                     vis[nx][ny] = true; // ✅ Mark as visited here
//                                 }
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//         System.out.println(cnt);
// }
// }
static int[] parent, size;

    // DSU Find with Path Compression
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // DSU Union by Size
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] ch = new char[n][m];

        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < m; j++) {
                ch[i][j] = s.charAt(j);
            }
        }

        int totalCells = n * m;
        parent = new int[totalCells];
        size = new int[totalCells];

        // Initialize DSU
        for (int i = 0; i < totalCells; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        int[] dx = {0, 1}; // Right and Down (to avoid redundant checks)
        int[] dy = {1, 0};

        // Union adjacent open cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (ch[i][j] == '.') {
                    int currIdx = i * m + j;
                    for (int k = 0; k < 2; k++) {  // Right and Down neighbors
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m && ch[nx][ny] == '.') {
                            int neighborIdx = nx * m + ny;
                            union(currIdx, neighborIdx);
                        }
                    }
                }
            }
        }

        // Count unique components
        Set<Integer> uniqueComponents = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (ch[i][j] == '.') {
                    uniqueComponents.add(find(i * m + j));
                }
            }
        }

        System.out.println(uniqueComponents.size());
    }
}