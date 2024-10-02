import java.util.Scanner;

/**
 * Missing_Number
 */
public class MissingNumber {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextLong();
        long sum1=(n*(n+1))/2;
        long sum2=0;
        for(int i=1;i<n;i++){
            sum2+=sc.nextLong();
        }
        System.out.println(sum1-sum2);
    }
}