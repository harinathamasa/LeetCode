package leetcode.amazon;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 93. Restore IP Addresses
 *
 * */

public class RestoreIPAddress {

    public static void main(String[] args) {
       List<String> res = restoreIpAddresses("0000");
       System.out.println("Result "+ Arrays.toString(res.toArray()));
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if(s.length() < 4 || s.length() > 12) return result;
        helper(s,result,"",0);
        return result;
    }

    private static void helper(String s, List<String> ans, String restored, int count) {
        if (count == 4) {
            if (s.isEmpty()) ans.add(restored);
            return;
        }

        for (int i = 1; i <= Math.min(3, s.length()); i++) {
            String sec = s.substring(0, i);
            if ((sec.length() > 1 && sec.charAt(0) == '0') || (sec.length() == 3 && Integer.valueOf(sec) > 255)) continue;
            helper(s.substring(i), ans, restored + sec + (count < 3 ? "." : ""), count+1);
        }
    }
}
