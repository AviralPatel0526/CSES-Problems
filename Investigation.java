import java.util.*;

public class Investigation {
    static final long MOD = 1_000_000_007L; // To prevent overflow

    static class Pair {
        int node;
        long dist;

        Pair(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            long wt = in.nextLong();
            adj.get(u).add(new Pair(v, wt));
        }

        // Dijkstra's Algorithm for shortest path
        long[] minPrice = new long[n];
        long[] ways = new long[n];
        Arrays.fill(minPrice, Long.MAX_VALUE);
        ways[0] = 1;
        minPrice[0] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.dist));
        pq.add(new Pair(0, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;
            long currDist = curr.dist;

            if (currDist > minPrice[u]) continue; // Skip outdated entries

            for (Pair neighbor : adj.get(u)) {
                int v = neighbor.node;
                long newPrice = minPrice[u] + neighbor.dist;

                if (newPrice < minPrice[v]) {
                    minPrice[v] = newPrice;
                    ways[v] = ways[u] % MOD;
                    pq.add(new Pair(v, newPrice));
                } else if (newPrice == minPrice[v]) {
                    ways[v] = (ways[v] + ways[u]) % MOD;
                }
            }
        }

        // Check if there is no valid path to n-1
        if (minPrice[n - 1] == Long.MAX_VALUE) {
            System.out.println("-1 0 -1 -1"); // No path exists
            return;
        }

        // BFS for minLen and maxLen
        int[] minLen = new int[n];
        int[] maxLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        Arrays.fill(maxLen, Integer.MIN_VALUE);
        Queue<Integer> queue = new LinkedList<>();
        minLen[0] = maxLen[0] = 0;
        queue.add(0);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Pair neighbor : adj.get(u)) {
                int v = neighbor.node;
                if (minPrice[u] + neighbor.dist == minPrice[v]) {
                    if (minLen[u] + 1 < minLen[v]) {
                        minLen[v] = minLen[u] + 1;
                        queue.add(v);
                    }
                    if (maxLen[u] + 1 > maxLen[v]) {
                        maxLen[v] = maxLen[u] + 1;
                        queue.add(v);
                    }
                }
            }
        }

        System.out.println(minPrice[n - 1] + " " + ways[n - 1] + " " + minLen[n - 1] + " " + maxLen[n - 1]);
    }
}
