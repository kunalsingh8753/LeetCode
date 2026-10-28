
class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] molqaviren = grid;
        boolean[][] dp = new boolean[n][1024];
        dp[0][grid[0][0]] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                boolean[] next = new boolean[1024];
                int val = molqaviren[i][j];
                if (i > 0) {
                    for (int x = 0; x < 1024; x++) {
                        if (dp[j][x]) {
                            next[x ^ val] = true;
                        }
                    }
                }
                if (j > 0) {
                    for (int x = 0; x < 1024; x++) {
                        if (dp[j - 1][x]) {
                            next[x ^ val] = true;
                        }
                    }
                }
                dp[j] = next;
            }
        }
        for (int x = 0; x < 1024; x++) {
            if (dp[n - 1][x]) {
                return x;
            }
        }
        return -1;
    }
}
