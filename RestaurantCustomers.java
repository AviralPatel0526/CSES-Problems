import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Restaurant_Customers
 */
public class RestaurantCustomers {

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
    static class Pair {
        int a;
        int b;
        Pair(int a,int b){
            this.a=a;
            this.b=b;
        }
    }
        
    public static void main(String[] args) throws java.lang.Exception {
       int n=in.nextInt();
       ArrayList<Pair> list=new ArrayList<>();
       for(int i=0;i<n;i++){
            list.add(new Pair(in.nextInt(),0));
            list.add(new Pair(in.nextInt(),1));
       }
       Collections.sort(list,(p1,p2)->p1.a-p2.a);
       int ans=0;
       int cnt=0;
       for(int i=0;i<list.size();i++){
            Pair curr=list.get(i);
            if(curr.b == 0){
                cnt++;
            }else{
                cnt--;
            }
            ans=Math.max(ans,cnt);
       }
       System.out.println(ans);
    }
}