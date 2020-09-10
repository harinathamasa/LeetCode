package leetcode.amazon;


/*
*
*91. Decode Ways

*
*  BBAD
* XT
* AYD
* 2 2 1 5
* 22 15
* 2 21 5
* 22 1 5
 *
* */



public class DecodeWays {

    public static void main(String[] args) {
        String str = "2215";
        DecodeWays decodeWays = new DecodeWays();
        int result = decodeWays.numDecodings(str);
        System.out.println("Result "+result);
    }

    public int numDecodings(String s){
        int len = s.length();
        int[] dp = new int[len+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == 0?0:1;
        for(int i=2;i<=len;i++){
            int first = Integer.valueOf(s.substring(i-1,i));
            int second = Integer.valueOf(s.substring(i-2,i));
            if(first > 0 && first <=9){
                dp[i] += dp[i-1];
            }

            if(second >= 10 && second <=26){
                dp[i] += dp[i-2];
            }

        }
        return dp[len];
    }
}
