import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Sum_of_Two_Values
 */
public class SumofTwoValues {

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
    static class Pair{
        int x;
        int y;
        Pair(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    public static void main(String[] args) throws java.lang.Exception {
        int n=in.nextInt();
        int sum=in.nextInt();
        ArrayList<Pair> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(new Pair(in.nextInt(),i+1));
        }
        Collections.sort(list,(p1,p2)->p1.x-p2.x);
        int i=0;
        int j=n-1;
        boolean flag=true;
        int idx1=0,idx2=0;
        while(i < j){

            if(list.get(i).x+list.get(j).x > sum){
                j--;
            }else if(list.get(i).x+list.get(j).x < sum){
                i++;
            }else{
                flag=false;
                idx1=list.get(i).y ;
                idx2= list.get(j).y;
                break;
            }
            
        }
        if(flag){
            System.out.println("IMPOSSIBLE");
        }else{
            System.out.println(idx1+" "+idx2);
        }
    }
}