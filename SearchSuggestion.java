package leetcode.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1268. Search Suggestions System
*
**/
public class SearchSuggestion {

    class Trie{
        char ch;
        Trie[] children;
        List<String> suggestion;
        boolean isComplete;
        public Trie(char ch){
            children = new Trie[26];
            isComplete = false;
            suggestion = new ArrayList<>();
        }
    }
    Trie root = new Trie('-');
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        addWordsToRoot(products);
        Trie search = root;
        List<List<String>> result = new ArrayList();

        for (char c : searchWord.toCharArray()) {
            if (root != null)
                root = root.children[c - 'a'];
            result.add(root == null ? Arrays.asList() : root.suggestion);
        }

        return result;
    }
    public void addWordsToRoot(String[] products){
        for(String str:products){
            Trie curr = root;
            for(char c: str.toCharArray()){
                if(curr.children[c-'a'] == null){
                    curr.children[c-'a'] = new Trie(c);
                }
                if(curr.children[c-'a'].suggestion.size() < 3)
                    curr.children[c-'a'].suggestion.add(str);
                curr =  curr.children[c-'a'];

            }
            curr.isComplete = true;
        }
    }

}
