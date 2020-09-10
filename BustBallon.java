package leetcode.amazon;


/**
 * 312. Burst Balloons - Hard - IMP
 *https://www.youtube.com/watch?v=l0gEZjF9KEk
 * */
public class BustBallon {
    public static void main(String[] args) {
        int[] arr = {3,1,5,8};
        int result = maxCoins(arr);
        System.out.println("Result "+result);
    }
    public static int maxCoins(int[] nums) {
        if(nums.length == 0) return 0;
        int len = nums.length + 2;
        int[] arr = new int[len];
        int n = 1;
        for(int numbr:nums){
            arr[n++] = numbr;
        }
        arr[0] = arr[n] = 1;
        int[][] dp = new int[len][len];
        for(int gap = 2;gap<len;gap++){
            for(int left = 0; left < len - gap;left++){
                int right = left + gap;
                int curr = 0;
                for(int i = left + 1;i<right;i++){
                    curr = Math.max(curr,dp[left][i] + dp[i][right]+arr[left] * arr[right] * arr[i] );
                }
                dp[left][right] = curr;
            }
        }
        return dp[0][len-1];
    }
}

