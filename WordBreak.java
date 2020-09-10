package leetcode.amazon;

import java.util.*;

/**
 *
 *139. Word Break
 *
 * */
public class WordBreak {
    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();

        boolean val = wordBreak.wordBreak("leetcode",new ArrayList<>(Arrays.asList("leet","code")));
        System.out.println("result "+val);
    }
    public boolean wordBreak(String s, List<String> wordDict) {

        if(s.length() == 0) return wordDict.size() == 0;
        if(wordDict.size() == 0) return false;

        Set<String> set = new HashSet<>();
        for(String str:wordDict){
            set.add(str);
        }
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                if(!dp[j]) continue;
                if(set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
