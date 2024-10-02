import java.util.Scanner;

/**
 * Repetitions
 */
public class Repetitions {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        int c=1;
        int n=s.length();
        int max=Integer.MIN_VALUE;
        for(int i=1;i<n;i++){
            if(s.charAt(i) != s.charAt(i-1)){
                max=Math.max(max, c);
                c=1;
            }else{
                c++;
            }
        }
        max=Math.max(max,c);
        System.out.println(max);
    }
}