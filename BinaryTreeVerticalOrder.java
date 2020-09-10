package leetcode.amazon;


import java.util.*;

/**
 * 314 Binary Tree Vertical Order Traversal
 *Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 * */
public class BinaryTreeVerticalOrder {
    class Node{
        TreeNode node;
        int hd;
        public Node(TreeNode node,int hd){
            this.node = node;
            this.hd = hd;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Node node_root = new Node(root,0);
        Queue<Node> queue = new LinkedList<>();
        Map<Integer,List<Integer>> map = new HashMap();
        queue.offer(node_root);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while(!queue.isEmpty()){
            Node node = queue.remove();
            map.putIfAbsent(node.hd,new ArrayList());
            map.get(node.hd).add(node.node.val);
            min = Math.min(min,node.hd);
            max = Math.max(max,node.hd);
            if(node.node.left != null){
                Node left = new Node(node.node.left,node.hd - 1);
                queue.offer(left);
            }
            if(node.node.right != null){
                Node right = new Node(node.node.right,node.hd + 1);
                queue.offer(right);
            }
        }

        for(int i=min;i<=max;i++){
            result.add(map.get(i));
        }
        return result;
    }
}
