package leetcode.amazon;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceAllBuildings {
    final static int[][] dir= {{1,0},{0,1},{-1,0},{0,-1}};
    public static void main(String[] args) {
        int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        int result = findShortestPath(grid);
        System.out.println("Result "+result);
    }

    public static int findShortestPath(int[][] grid){
        if(grid.length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int[][] buildings = new int[row][col];
        int[][] distance = new int[row][col];
        int numOfBuildings = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j] == 1){
                    bfs(grid,i,j,buildings,distance);
                    numOfBuildings++;
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(buildings[i][j] == numOfBuildings){
                    if(distance[i][j] < minDistance){
                        minDistance = distance[i][j];
                    }
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 :minDistance;
    }

    private static void bfs(int[][] grid, int i, int j, int[][] buildings, int[][] distance) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        int dis = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        while(!queue.isEmpty()){
            int size = queue.size();
            dis++;
            for(int k=0;k<size;k++){
                int[] p = queue.poll();
                for(int f[]:dir){
                    int x = p[0] + f[0];
                    int y = p[1] + f[1];
                    if(x < 0 || x > grid.length-1 || y < 0 || y > grid[0].length -1 || grid[x][y] == 1 || grid[x][y] == 2 || visited[x][y]){
                        continue;
                    }
                    queue.add(new int[]{x,y});
                    buildings[x][y]++;
                    distance[x][y] += dis;
                    visited[x][y] = true;
                }
            }
        }
    }
}
