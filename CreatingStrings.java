import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Creating_Strings
 */
public class CreatingStrings {

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
    
    static void f(char ch[],int n,String temp,ArrayList<String> list,boolean vis[]){
        if(temp.length() == n){
            list.add(temp);
            return;
        }
        for(int k=0;k<n;k++){
            if(!vis[k]){
                if (k > 0 && ch[k] == ch[k - 1] && !vis[k - 1]) {
                    continue;
                }
                vis[k]=true;
                f(ch,n,temp+ch[k],list,vis);
                vis[k]=false;
            }
        }
    }
    public static void main(String[] args) throws java.lang.Exception {
        String s=in.next();
        int n=s.length();
        char ch[]=s.toCharArray();
        Arrays.sort(ch);
        ArrayList<String> list=new ArrayList<>();
        boolean vis[]=new boolean[n];
        f(ch,n,"",list,vis);
        System.out.println(list.size());
        for(String i:list){
            System.out.println(i);
        }
    }
}