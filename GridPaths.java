// ekdum sexy problem thi


import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * GridPaths
 */
public class GridPaths {

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
    static int paths=0;
    static int steps=0;
    static boolean vis[][]=new boolean[7][7];
    static int res[]=new int[48];
    static void f(int r,int c){
        if(r == 6 && c == 0){
            if(steps == 48){
                paths++;
            }
            return;
        }
        if (vis[r][c] || (
            ((c >= 1 && c <= 5 && !vis[r][c + 1] && !vis[r][c - 1]) &&
                ((r == 0 && vis[r + 1][c]) || (r == 6 && vis[r - 1][c]))) 
            ||
            ((r >= 1 && r <= 5 && !vis[r + 1][c] && !vis[r - 1][c]) &&
                ((c == 0 && vis[r][c + 1]) || (c == 6 && vis[r][c - 1])))
            ||
            (r >= 1 && r <= 5 && c >= 1 && c <= 5 && vis[r + 1][c] 
                && vis[r - 1][c] && !vis[r][c + 1] && !vis[r][c - 1])
            ||
            (r >= 1 && r <= 5 && c >= 1 && c <= 5 && vis[r][c + 1] 
                && vis[r][c - 1] && !vis[r + 1][c] && !vis[r - 1][c])))
        {
            return;
        }

        vis[r][c]=true;
        if(res[steps] != -1){
            if(res[steps] == 0){
                if(r > 0 && !vis[r-1][c]){
                    steps++;
                    f(r-1,c);
                    steps--;
                }
            }
            if(res[steps] == 1){
                if(c < 6 && !vis[r][c+1]){
                    steps++;
                    f(r,c+1);
                    steps--;
                }
            }
            if(res[steps] == 2){
                if(r < 6 && !vis[r+1][c]){
                    steps++;
                    f(r+1,c);
                    steps--;
                }
            }
            if(res[steps] == 3){
                if(c > 0 && !vis[r][c-1]){
                    steps++;
                    f(r,c-1);
                    steps--;
                }
            }
            vis[r][c]=false;
            return;
        }
           
        if(r > 0 && !vis[r-1][c]){
            steps++;
            f(r-1,c);
            steps--;
        }
        if(c < 6 && !vis[r][c+1]){
            steps++;
            f(r,c+1);
            steps--;
        }
        if(r < 6 && !vis[r+1][c]){
            steps++;
            f(r+1,c);
            steps--;
        }
        
        if(c > 0 && !vis[r][c-1]){
            steps++;
            f(r,c-1);
            steps--;
        }
        vis[r][c]=false;
    } 
    public static void main(String[] args) throws java.lang.Exception {
        String s=in.next();
        int n=s.length();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch == '?'){
                res[i]=-1;
            }else if(ch == 'U'){
                res[i]=0;
            }else if(ch == 'R'){
                res[i]=1;
            }else if(ch == 'D'){
                res[i]=2;
            }else{
                res[i]=3;
            }
        }
        f(0,0);
        System.out.println(paths);
    }
}