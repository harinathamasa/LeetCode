package leetcode.amazon;

import java.util.HashSet;
import java.util.Set;

/*


[LeetCode] 694. Number of Distinct Islands
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally
 (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be
translated (and not rotated or reflected) to equal the other.

Example 1:

11000
11000
00011
00011
Given the above grid map, return 1.

Helper method strings
X - Start
O - Out of bounds/0
U - Up
D - Down
L - Left
R - right
**/
public class UniqueIsland {
    public static void main(String[] args) {
        int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        int res = numDistinctIslands(grid);
        System.out.println("Result "+res);
    }

    public static int numDistinctIslands(int[][] grid) {
        if(grid.length == 0) return 0;
        Set<String> set = new HashSet<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    String str = computeString(i,j,grid,"X");
                    set.add(str);
                }

            }
        }
        return set.size();
    }

    private static String computeString(int i, int j, int[][] grid, String x) {
        if(i<0 || i>= grid.length || j < 0 || j>= grid[0].length || grid[i][j] == 0)
            return "O";
        grid[i][j] = 0;
        String left = computeString(i,j-1,grid,"L");
        String right = computeString(i,j+1,grid,"R");
        String up = computeString(i-1,j,grid,"U");
        String down = computeString(i+1,j-1,grid,"D");
        return x + left + right + up + down ;

    }

}
