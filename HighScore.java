import java.util.*;

public class HighScore {
    static final long INF = (long) 1e18;

    static void dfs(List<List<Integer>> g, int[] s, int u) {
        s[u] = 1;
        for (int v : g.get(u))
            if (s[v] == 0)
                dfs(g, s, v);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), m = sc.nextInt();
        List<List<Integer>> a = new ArrayList<>();
        List<List<Integer>> b = new ArrayList<>();
        List<int[]> e = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            a.add(new ArrayList<>());
            b.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            a.get(u).add(v);
            b.get(v).add(u);
            e.add(new int[]{u, v, -w});
        }

        int[] vis = new int[n + 1], vis1 = new int[n + 1];
        dfs(a, vis, 1);
        dfs(b, vis1, n);

        long[] d = new long[n + 1];
        Arrays.fill(d, INF);
        d[1] = 0;
        boolean flag = false;

        for (int i = 1; i <= n; i++) {
            flag = false;
            for (int[] x : e) {
                int u = x[0], v = x[1], w = x[2];
                if (vis[u] == 1 && vis1[v] == 1 && d[u] + w < d[v]) {
                    flag = true;
                    d[v] = d[u] + w;
                }
            }
        }
        System.out.println(flag ? -1 : -d[n]);

        sc.close();
    }
}
