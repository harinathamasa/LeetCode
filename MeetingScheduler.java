package leetcode.amazon;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1229 Meeting Scheduler
 *
 * **/
public class MeetingScheduler {

    public static void main(String[] args) {
        MeetingScheduler meetingScheduler = new MeetingScheduler();
        int[][] slot1 = {{10,50},{60,120},{140,210}};
        int[][] slot2 = {{0,15},{60,70}};
        List<Integer> result = meetingScheduler.earlistAvailableSlot(slot1,slot2,8);
        System.out.println("Result "+Arrays.toString(result.toArray()));
    }

    public List<Integer> earlistAvailableSlot(int[][] slot1,int[][] slot2,int duration){
        Arrays.sort(slot1,(a,b) -> a[0] - b[0]);
        Arrays.sort(slot2,(a,b) -> a[0] - b[0]);
        int a = 0;
        int b = 0;
        while(true){
            int start = Math.max(slot1[a][0],slot2[b][0]);
            int end = Math.min(slot1[a][1],slot2[b][1]);
            if(end - start >= duration){
                return new ArrayList<>(Arrays.asList(start,start+duration));
            }else{
                if(end == slot1[a][1]) a++;
                else if(end == slot2[b][1])b++;
                else if(a >= slot1.length || b >= slot2.length )
                    return new ArrayList<>();
            }
        }
    }
}
