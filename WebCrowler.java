package leetcode.amazon;

import java.util.*;

/**
 *
 *1235 Leetcode
 * **/
public class WebCrowler {
    public List<String> getURls(String url){
        return new ArrayList<>();
    }

    public List<String> getlinks(String startUrl){
        Set<String> set = new HashSet<>();
        List<String> host_urls = getURls(startUrl);
        String host = startUrl.split("/")[2];
        Queue<String> queue = new LinkedList<>();
        queue.add(startUrl);
        for(String url:host_urls){
            if(url.contains(host) && !set.contains(url)){
                queue.add(url);
            }
        }

        while (!queue.isEmpty()){
            String cl_url = queue.poll();
            if(set.contains(cl_url)) continue;
            List<String> cl_urls = getURls(cl_url);
            for(String s:cl_urls){
                if(s.contains(host) && !set.contains(s)){
                    queue.add(s);
                }
            }
        }
        return new ArrayList<>(set);
    }
}
