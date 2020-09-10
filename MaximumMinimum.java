package leetcode.amazon;

public class MaximumMinimum {
    public static void main(String[] args) {
        int arr[][] = {{5,4,5},{1,2,6},{7,4,6}};
        int result =maximumMinimumPath(arr);
        System.out.println("Result "+result);
    }

    public static int maximumMinimumPath(int[][] A) {
        if(A.length == 0) return -1;
        int max = Integer.MIN_VALUE;
        boolean[][] visited = new boolean[A.length][A[0].length];
        return dfs(0,0,Integer.MAX_VALUE,visited,A);
    }

    private static int dfs(int r,int c,int score, boolean[][] visited, int[][] A) {

        int row = A.length;
        int col = A[0].length;
        if (r == row - 1 && c == col - 1) {
            return Math.min(A[r][c], score);
        }

        // consider current value
        score = Math.min(A[r][c], score);
        visited[r][c] = true;

        // 4 directions
        int up = Integer.MIN_VALUE;
        if (r - 1 >= 0 && !visited[r - 1][c]) { // up
            up = dfs(r - 1, c, score, visited, A);
        }
        int down = Integer.MIN_VALUE;
        if (r + 1 <= row - 1 && !visited[r + 1][c]) {
            down = dfs(r + 1, c, score, visited, A);
        }
        int left = Integer.MIN_VALUE;
        if (c - 1 >= 0 && !visited[r][c - 1]) {
            left = dfs(r, c - 1, score, visited, A);
        }
        int right = Integer.MIN_VALUE;
        if (c + 1 <= col - 1 && !visited[r][c + 1]) {
            right = dfs(r, c + 1, score, visited, A);
        }

        // reset
        visited[r][c] = false;

        int m1 = Math.max(up, down);
        int m2 = Math.max(left, right);
        return Math.max(m1, m2);
    }

}