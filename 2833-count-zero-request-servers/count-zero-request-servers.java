import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int[] sortedQueries = queries.clone();
        Arrays.sort(sortedQueries);

        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> answerMap = new HashMap<>();

        int left = 0;
        int right = 0;

        for (int q : sortedQueries) {
            int start = q - x;

            // Add logs within [q-x, q]
            while (right < logs.length && logs[right][1] <= q) {
                int server = logs[right][0];
                map.put(server, map.getOrDefault(server, 0) + 1);
                right++;
            }

            // Remove logs before q-x
            while (left < right && logs[left][1] < start) {
                int server = logs[left][0];
                map.put(server, map.get(server) - 1);

                if (map.get(server) == 0) {
                    map.remove(server);
                }
                left++;
            }

            // Servers not in map have zero requests
            answerMap.put(q, n - map.size());
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            ans[i] = answerMap.get(queries[i]);
        }

        return ans;
    }
}