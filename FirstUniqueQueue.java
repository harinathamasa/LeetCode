package leetcode.amazon;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class FirstUniqueQueue {
    Set<Integer> unique;
    Set<Integer> dup;

    public static void main(String[] args) {
        int[] nums = {1,2,3,2,4,3};
        FirstUniqueQueue firstUniqueQueue = new FirstUniqueQueue(nums);
        System.out.println("Unique one "+firstUniqueQueue.getUnique());
        firstUniqueQueue.addNum(1);
        firstUniqueQueue.addNum(5);
        firstUniqueQueue.addNum(6);
        System.out.println("Unique one "+firstUniqueQueue.getUnique());

    }

    public FirstUniqueQueue(int[] nums){
        unique = new LinkedHashSet<>();
        dup = new HashSet<>();
        for(int num:nums){
            if(!unique.contains(num) && !dup.contains(num)){
                unique.add(num);
            }else{
                if(unique.contains(num)){
                    unique.remove(num);
                }
                dup.add(num);
            }
        }
    }

    public int getUnique(){
        if(unique.size() == 0){
            return -1;
        }
        for(int num:unique){
            return num;
        }
        return -1;
    }

    public void addNum(int num){
        if(!unique.contains(num) && !dup.contains(num)){
            unique.add(num);
        }else{
            if(unique.contains(num)){
                unique.remove(num);
            }
            dup.add(num);
        }
    }

}
