package leetcode.amazon;

public class MinSubString {

    public static void main(String[] args) {
        String str = "ADOBECODEBANC";
        String t = "ABC";
        String result = minWindow(str,t);
        System.out.println("Result "+result);
    }

    public static String minWindow(String s, String t) {
        if(s == null || s.length() == 0)
            return "";
        int len = Integer.MAX_VALUE;
        int[] map = new int[128];
        int count=t.length();
        int begin = 0;
        // keep the count of all characters in string t

        for(char c:t.toCharArray()){
            map[c]++;
        }

        for(int lo=0,hi=0;hi<s.length();hi++){
            char ch = s.charAt(hi);
            if(map[ch] > 0){
                count--;
            }
            map[ch]--;
            while(count == 0){
                char cl = s.charAt(lo);
                map[cl]++;
                if(map[cl] > 0){
                    if(hi-lo+1 < len){
                        len = hi-lo+1;
                        begin = lo;
                    }
                    count++;
                }
                lo++;
            }

        }
        return len==Integer.MAX_VALUE?"":s.substring(begin, begin+len);
    }
}
