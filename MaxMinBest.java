package leetcode.amazon;

import java.util.PriorityQueue;

public class MaxMinBest {
    public static void main(String[] args) {
        int arr[][] = {{5,4,5},{1,2,6},{7,4,6}};
        MaxMinBest maxMinBest = new MaxMinBest();
        int result =maxMinBest.maxMin(arr);
        System.out.println("Result "+result);
    }

    int rows;
    int columns;
    public int maxMin(int[][] A){
        if(A.length == 0) return -1;
        rows = A.length;
        columns = A[0].length;
        // stores max values and expand in max direction
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a,b) -> A[b[0]][b[1]] - A[a[0]][a[1]]);
        queue.offer(new int[]{0,0});
        boolean[][] visited = new boolean[rows][columns];
        int max = Integer.MAX_VALUE;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!queue.isEmpty()){
            int[] p = queue.poll();
            visited[p[0]][p[1]] = true;
            max = Math.min(max,A[p[0]][p[1]]);
            if(p[0] == rows -1 && p[1] == columns-1) break;
            for(int[] dir:dirs){
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                if(x < 0 || x >= rows || y<0 || y>= columns || visited[x][y]) continue;
                queue.offer(new int[]{x,y});
            }
        }
        return max;
    }
}
