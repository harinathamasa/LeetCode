package leetcode.amazon;

public class TicTacToe2 {
    private int[][] grid;
    private int n;

    public TicTacToe2(int n){
        grid = new int[n][n];
        this.n = n;
    }
    public static void main(String[] args) {
        TicTacToe2 toe = new TicTacToe2(3);
        toe.move(0, 0, 1);
        toe.move(0, 2, 2);
        toe.move(2, 2, 1);
        toe.move(1, 1, 2);
        toe.move(2, 0, 1);
        toe.move(1, 0, 2);
        int result  = toe.move(2, 1, 1);
        System.out.println("result "+result);
    }

    public int move(int row,int column,int player){
        grid[row][column] = player;
        int win = player;
        for(int i=0;i<n;i++){
            if(grid[row][i] != player){
                win = 0;
                break;
            }
        }
        if(win == player) return player;
        win = player;
        for(int i=0;i<n;i++){
            if(grid[i][column] != player){
                win = 0;
                break;
            }
        }
        if(win == player) return player;
        win = player;
        if(row == column){
            for(int i=0;i<n;i++){
                if(grid[i][i] != player){
                    win = 0;
                    break;
                }
            }
            if(win == player) return player;
        }

        if(row+column == n-1){
            for(int i=0;i<n;i++){
                if(grid[i][n-i-1] != player){
                   win = 0;
                   break;
                }
            }
            if(win == player) return player;
        }
        return 0;
    }

}
