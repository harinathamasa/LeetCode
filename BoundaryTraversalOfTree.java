package leetcode.amazon;


import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * **/
public class BoundaryTraversalOfTree {

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x)
        {
            val = x;
        }
    }

    TreeNode root;
    public static void main(String[] args) {

    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        result.add(root.val);
        leftBoundary(root.left,result);
        rightBoundary(root.right,result);
        return result;
    }

    private void rightBoundary(TreeNode right, List<Integer> result) {
        if(right != null){
            if (right.right != null){
                DFS(right.left,result);
                rightBoundary(right.right,result);
            }else{
                rightBoundary(right.left,result);
            }
            result.add(right.val);
        }
    }

    private void leftBoundary(TreeNode left, List<Integer> result) {
        if(left != null){
            result.add(left.val);
            if(left.left != null){
                leftBoundary(left.left,result);
                DFS(left.right,result);
            }else{
                leftBoundary(left.right,result);
            }
        }
    }

    private void DFS(TreeNode root, List<Integer> result) {
        if(root != null){
            if(root.left == null && root.right == null){
                result.add(root.val);
            }else{
                DFS(root.left,result);
                DFS(root.right,result);
            }

        }
    }


}
