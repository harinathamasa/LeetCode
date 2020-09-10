package leetcode.amazon;

/*
*
* 295. Find Median from Data Stream

 * */

import java.util.PriorityQueue;

public class RunningMediam {
    //
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // All small elements here in desc order
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b-a);

    /** initialize your data structure here. */


    public void addNum(int num) {
        if(minHeap.size() == maxHeap.size()){
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }
        else if(maxHeap.size() > minHeap.size()){
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian() {
        if(maxHeap.size() == 0)
            return 0;
        else if(maxHeap.size() == minHeap.size()){
            return (maxHeap.peek()+minHeap.peek())/2.0;
        }else{
            return maxHeap.peek();
        }

    }


}
