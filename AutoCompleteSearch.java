package leetcode.amazon;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * 642. Design Search Autocomplete System
 *
 *
 * */
public class AutoCompleteSearch {

    public static void main(String[] args) {
        String[] sentenses = {"i love you", "island","ironman", "i love leetcode"};
        int[] times = {5,3,2,2};
        AutoCompleteSearch autoCompleteSearch  = new AutoCompleteSearch(sentenses,times);
        List<String> res = autoCompleteSearch.input('i');
        System.out.println(Arrays.toString(res.toArray()));
        List<String> res1 = autoCompleteSearch.input(' ');
        System.out.println(Arrays.toString(res1.toArray()));
        List<String> res2 = autoCompleteSearch.input('a');
        System.out.println(Arrays.toString(res2.toArray()));
        List<String> res3 = autoCompleteSearch.input('#');
        System.out.println(res3);


        List<String> re = autoCompleteSearch.input('i');
        System.out.println(Arrays.toString(re.toArray()));
        List<String> re1 = autoCompleteSearch.input(' ');
        System.out.println(Arrays.toString(re1.toArray()));
        List<String> re2 = autoCompleteSearch.input('a');
        System.out.println(Arrays.toString(re2.toArray()));

    }

    class TrieNode{
        TrieNode[] children;
        String s;
        int times;
        List<TrieNode> hot;
        public TrieNode() {
            children = new TrieNode[128];
            s = new String();
            times = 0;
            hot = new ArrayList<>();
        }

        public void update(TrieNode curr) {
            if(!this.hot.contains(curr)){
                this.hot.add(curr);
            }
            Collections.sort(this.hot,(x,y) -> {
                if(x.times == y.times)
                    return x.s.compareTo(y.s);
                return y.times - x.times;
            });

            if(this.hot.size() > 3){
                this.hot.remove(this.hot.size() - 1);
            }
        }
    }

    TrieNode root;
    StringBuilder sb;
    TrieNode input;

    public void add(String s,int times){
        TrieNode curr = root;
        input = root;
        List<TrieNode> visited = new ArrayList<>();
        for(char c:s.toCharArray()){
            if(curr.children[c] == null){
                curr.children[c] = new TrieNode();
            }
            curr = curr.children[c];
            visited.add(curr);
        }
        curr.s = s;
        curr.times += times;
        for(TrieNode node:visited)
            node.update(curr);
    }


    public AutoCompleteSearch(String[] sentences, int[] times) {
        root = new TrieNode();
        sb = new StringBuilder();
        for(int i=0;i<sentences.length;i++){
            add(sentences[i],times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if(c == '#'){
            add(sb.toString(),1);
            input = root;
            sb = new StringBuilder();
            return res;
        }
        sb.append(c);
        if(input != null){
            input = input.children[c];
        }

        if(input == null) return res;
        for(TrieNode node:input.hot){
            res.add(node.s);
        }

        return res;
    }

}