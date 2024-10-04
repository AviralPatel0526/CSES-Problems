import java.util.Scanner;

public class TwoKnights {

    public static void main(String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        
        for (long i = 1; i <= n; i++) {
            if (i == 1) {
                System.out.println(0); // 1x1 board, no room for two knights
            } else if (i == 2) {
                System.out.println(6); // Known result for 2x2 board
            } else if (i == 3) {
                System.out.println(28); // Known result for 3x3 board
            } else {
                long res = ((i * i) * (i * i - 1) 
                            - 8 - 24 - (i - 4) * 16 
                            - 16 - (i-4)*24- (i - 4) * (i - 4) * 8) / 2;
                System.out.println(res);
            }
        }
    }
}
