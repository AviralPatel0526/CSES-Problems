import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Increasing_Array
 */
public class IncreasingArray {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        long a[]=new long[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextLong();
        }
        long ans=0;
        for(int i=1;i<n;i++){
            if(a[i] < a[i-1]){
                ans+=a[i-1]-a[i];
                a[i]=a[i-1];
            }
        }
        System.out.println(ans);
    }
}