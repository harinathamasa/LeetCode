package leetcode.amazon;

import java.util.Arrays;
import java.util.HashSet;

/*
* 957. Prison Cells After N Days
 *
* */
public class PrisonCellNDays {
    public int[] prisonAfterNDays(int[] cells, int N) {
        HashSet<String> cycle= new HashSet();
        boolean flag = false;
        int size = 0;
        for(int i=0;i<N;i++){
            int[] next = nextDay(cells);
            String nxt = Arrays.toString(next);
            if(!cycle.contains(nxt)){
                cycle.add(nxt);
                cells = next;
                size++;
            }else{
                flag = true;
                break;
            }
        }
        if(flag){
            System.out.println("size "+size+" N"+ N % size);
            return prisonAfterNDays(cells,N % size);
        }
        return cells;
    }

    private int[] nextDay(int[] cells){
        int[] result = new int[cells.length];
        for(int i=1;i<cells.length-1;i++){
            result[i] = cells[i-1] == cells[i+1] ? 1:0;
        }
        return result;
    }
}
