package leetcode.amazon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class MedianSlidingWindow {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        MedianSlidingWindow medianSlidingWindow = new MedianSlidingWindow();
        double[] result = medianSlidingWindow.medianSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result));
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
        // Sort descending
        TreeSet<Integer> left = new TreeSet<Integer>(comparator.reversed());
        // Sort ascending
        TreeSet<Integer> right = new TreeSet<Integer>(comparator);

        int n = nums.length;
        double[] res = new double[n - k + 1];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            left.add(i);
            right.add(left.pollFirst());
            balance(left, right);
            if (left.size() + right.size() == k) {
                res[idx] = getMedian(k, nums, left, right);
                if (!left.remove(idx)) {
                    right.remove(idx);
                }
                idx++;
            }
        }
        return res;
    }

    private double getMedian(int k, int[] nums, TreeSet<Integer> left, TreeSet<Integer> right) {
        if (k % 2 == 0) {
            return ((double) nums[left.first()] + nums[right.first()]) / 2;
        } else {
            return (double) nums[left.first()];
        }
    }

    // Make sure left size >= right size
    private void balance(TreeSet<Integer> left, TreeSet<Integer> right) {
        while (right.size() > left.size()) {
            left.add(right.pollFirst());
        }
    }
}

