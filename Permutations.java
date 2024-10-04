import java.util.*;

public class Permutations {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        StringBuilder result = new StringBuilder();

        if (n == 1) {
            result.append(1);
        } else if (n <= 3) {
            result.append("NO SOLUTION");
        } else if (n == 4) {
            result.append("2 4 1 3");
        } else {
            int a = n;
            int b = n - 1;

            while (a > 0) {
                result.append(a).append(" ");
                a -= 2;
            }
            while (b > 0) {
                result.append(b).append(" ");
                b -= 2;
            }
        }

        System.out.println(result.toString().trim());
    }
}
