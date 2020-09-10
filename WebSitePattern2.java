package leetcode.amazon;

import java.util.*;

/*
*LeetCode 1152. Analyze User Website Visit Pattern
* We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].

A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)

Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.

Example 1:

Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
Output: ["home","about","career"]
Explanation:
The tuples in this example are:
["joe", 1, "home"]
["joe", 2, "about"]
["joe", 3, "career"]
["james", 4, "home"]
["james", 5, "cart"]
["james", 6, "maps"]
["james", 7, "home"]
["mary", 8, "home"]
["mary", 9, "about"]
["mary", 10, "career"]
The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
Note:

3 <= N = username.length = timestamp.length = website.length <= 50
1 <= username[i].length <= 10
0 <= timestamp[i] <= 10^9
1 <= website[i].length <= 10
Both username[i] and website[i] contain only lowercase characters.
It is guaranteed that there is at least one user who visited at least 3 websites.
No user visits two websites at the same time.
*
*
*
*
* */


public class WebSitePattern2 {
    public static void main(String[] args) {
        String[] username = {"joe","joe","joe","james","james","james","james","mary","mary","mary"};
        int[] timestamp = {1,2,3,4,5,6,7,8,9,10};
        String[] website = {"home","about","career","home","cart","maps","home","home","about","career"};
        List<String> output = mostVisitedPattern(username,timestamp,website);
        System.out.println(Arrays.toString(output.toArray()));
    }

    public static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {

        Map<String, TreeMap<Integer,String>> usr_web = new HashMap();
        Map<String,List<String>> user_list = new HashMap<>();
        Map<String,Integer> three_sites = new HashMap<>();
        List<String> result = new ArrayList<>();

        // This will store the user and list of sites in timestamp order
        for(int i=0;i<username.length;i++){
            if(!usr_web.containsKey(username[i])){
                usr_web.put(username[i],new TreeMap<>());
            }
            usr_web.get(username[i]).put(timestamp[i],website[i]);
        }

        // Maintain a mapping a user to website in a list

        for(String user:usr_web.keySet()){
            if(!user_list.containsKey(user)){
                user_list.put(user,new ArrayList<>());
            }
            System.out.println("Key Set for user "+user+" keys "+usr_web.get(user).keySet());
            for(Integer time:usr_web.get(user).keySet()){
                user_list.get(user).add(usr_web.get(user).get(time));
            }
        }

        //Loop through each user and list of sites and find the 3 combinations and add it to the result

        int max = 0;
        String lex = "";
        for(String user:user_list.keySet()){
            int size = user_list.get(user).size();
            List<String> temp = new ArrayList<>();
            // Find the chronological order
            for(int i=0;i<size;i++){
                for(int j=i+1;j<size;j++){
                    for(int k=j+1;k<size;k++){
                        String s = user_list.get(user).get(i)+" "+user_list.get(user).get(j)+" "+user_list.get(user).get(k);
                        temp.add(s);
                    }
                }
            }
            // Loop through each string and it to the 3 site map by count
            for(String str:temp){
                three_sites.put(str,three_sites.getOrDefault(str,0)+1);
                if(three_sites.get(str) > max || three_sites.get(str) == max && str.compareTo(lex)< 0){
                    result = new ArrayList<>();
                    result.add(str.split(" ")[0]);
                    result.add(str.split(" ")[1]);
                    result.add(str.split(" ")[2]);
                    lex = str;
                    max = three_sites.get(str);
                }
            }
        }


    return result;
    }
}
