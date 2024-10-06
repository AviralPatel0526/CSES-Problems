import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Chessboard_and_Queens
 */
public class ChessboardandQueens {

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
    static void print(char ch[][]){
        int n=ch.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(ch[i][j]);
            }
            System.out.println();
        }
    }
    static int ans=0;
    static boolean isSafe(int r,int c,char ch[][]){
        int n=ch.length;
        for(int i=r-1;i>=0;i--){
            if(ch[i][c] == 'Q'){
                return false;
            }
        }
        for(int i=r-1,j=c-1;i>=0 && j >=0;i--,j--){
            if(ch[i][j] == 'Q'){
                return false;
            }
        }
        for(int i=r-1,j=c+1;i>=0 && j<n;i--,j++){
            if(ch[i][j] == 'Q'){
                return false;
            }
        }
        return true;
    }
    static void f(int r,char ch[][]){
        if(r == 8){
            ans++;
            return;
        }
        for(int c=0;c<8;c++){
            if(ch[r][c] == '.'){
                if(isSafe(r,c,ch)){
                    ch[r][c]='Q';
                    f(r+1,ch);
                    ch[r][c]='.';
                }
                
            }
        }
    }
    public static void main(String[] args) throws java.lang.Exception {
        char ch[][]=new char[8][8];
        for(int i=0;i<8;i++){
            ch[i]=in.next().toCharArray();
        }
        f(0,ch);
        System.out.println(ans);
    }
}