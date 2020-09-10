package leetcode.amazon;

import java.util.LinkedList;
import java.util.Queue;

public class Maze {
    public static void main(String[] args) {
        int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        Maze maze1 = new Maze();
        System.out.println("Result "+maze1.hasPath(maze,new int[]{0,4},new int[]{3,1}));
    }

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        int[][] directions = {{0,1},{1,0},{-1,0},{0,-1}};
        while (!queue.isEmpty()){
            int[] ordinates = queue.poll();
            if(ordinates[0] == destination[0] && ordinates[1] == ordinates[1]) return true;
            if(visited[ordinates[0]][ordinates[1]]) continue;
            visited[ordinates[0]][ordinates[1]] = true;
            for(int[] dir:directions){
                int x = ordinates[0];
                int y = ordinates[1];
                while (isValid(x+dir[0],y+dir[1],maze)){
                    x += dir[0];
                    y += dir[1];
                }
                if(!visited[x][y]){
                    queue.offer(new int[]{x,y});
                }
            }
        }
        return false;
    }

    private boolean isValid(int x, int y, int[][] maze) {
        return (x >= 0 && x<maze.length && y>=0 && y<maze[0].length && maze[x][y] != 1) ;
    }

}
