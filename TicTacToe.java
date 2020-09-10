package leetcode.amazon;

public class TicTacToe {
    int[] rowCount;
    int[] columnCount;
    int leftDiag = 0;
    int rightDiag = 0;
    int n;

    public TicTacToe(int n){
        rowCount = new int[n];
        columnCount = new int[n];
        this.n = n;
    }

    public static void main(String[] args) {
        TicTacToe toe = new TicTacToe(3);
        toe.move(0, 0, 1);
        toe.move(0, 2, 2);
        toe.move(2, 2, 1);
        toe.move(1, 1, 2);
        toe.move(2, 0, 1);
        toe.move(1, 0, 2);
        int result = toe.move(2, 1, 1);
        System.out.println("result " + result);
    }

    private int move(int row, int column, int player) {
        int mv  = player == 1 ? 1 : -1;
        rowCount[row] += mv;
        columnCount[column] += mv;

        if(row == column){
            leftDiag += mv;
        }

        else if(row + column == n - 1){
            rightDiag += mv;
        }
        if(Math.abs(rowCount[row]) == n || Math.abs(columnCount[column]) == n || Math.abs(leftDiag) == n || Math.abs(rightDiag) == n)
            return player;
        else
            return 0;
    }
}
