package leetcode.amazon;


import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @296 Leetcode
 *
 * **/
public class BestMeetingPoint {
    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        BestMeetingPoint bestMeetingPoint = new BestMeetingPoint();
        int result = bestMeetingPoint.minTotalDistance(grid);
        System.out.println("Result "+result);
    }

    public int minTotalDistance(int[][] grid){
        if(grid.length == 0) return 0;
        ArrayList<Integer> rows = new ArrayList<>(); // Add all x points
        ArrayList<Integer> cols = new ArrayList<>(); // Add all y points
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        Collections.sort(cols);
        int midpoint = rows.size()/2;  // find mid point
        int distance = 0;
        for(int i=0;i<rows.size();i++){
            distance += Math.abs(i-rows.get(midpoint)) + Math.abs(i-cols.get(midpoint));
        }
        return distance;
    }
}
