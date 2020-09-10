package leetcode.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Least Frequently Used
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 *
 * Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.
 *
 *
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 *
 * **/

public class LFU {
    Map<Integer,Node> node_map;
    Map<Integer,DQueue> freq_map;
    int size;
    int capacity;
    int min;

    class Node{
        int val,freq,key;
        Node prev;
        Node next;
        public Node(int key,int val){
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    class DQueue{
        Node head;
        Node tail;
        int size;
        public DQueue(){
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next = tail;
            head.prev = null;
            tail.prev = head;
            tail.next = null;
            this.size = 0;
        }

        public void addNode(Node node){
            Node nextNode = head.next;
            node.next = nextNode;
            nextNode.prev = node;
            node.prev = head;
            head.next = node;
            size++;
        }

        public void removeNode(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast(){
            if(size > 0){
                Node node = tail.prev;
                removeNode(node);
                return node;
            }else{
                return null;
            }
        }
    }


    public LFU(int capacity) {
        this.node_map = new HashMap<>();
        this.freq_map = new HashMap();
        this.capacity = capacity;
        this.size = 0;
        this.min = 0;
    }

    public int get(int key) {
        if(node_map.containsKey(key)){
            update(node_map.get(key));
            return node_map.get(key).val;
        }else
            return -1;
    }

    public void update(Node node){
        DQueue dqueue = freq_map.get(node.freq);
        dqueue.removeNode(node);
        if(node.freq == min && dqueue.size == 0)
            min++;
        node.freq++;
        DQueue new_dqueue = freq_map.getOrDefault(node.freq,new DQueue());
        new_dqueue.addNode(node);
        freq_map.put(node.freq,new_dqueue);
    }

    public void put(int key, int value) {
        if(capacity == 0) return;
        Node node;
        if(node_map.containsKey(key)){
            node = node_map.get(key);
            node.val = value;
            update(node);
        }else{
            node = new Node(key,value);
            node_map.put(key,node);
            if(size == capacity){
                DQueue dqueue = freq_map.get(min);
                node_map.remove(dqueue.removeLast().key);
                size--;
            }
            size++;
            min = 1;
            DQueue dqueue = freq_map.getOrDefault(node.freq,new DQueue());
            dqueue.addNode(node);
            freq_map.put(node.freq,dqueue);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

