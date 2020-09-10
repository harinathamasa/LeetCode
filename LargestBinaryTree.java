package leetcode.amazon;

/**
 *
 *
 *
 * **/
public class LargestBinaryTree {

    public int largestBST(TreeNode root){
        if(root == null) return 0;
        if(isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE)) return largestBST(root.left)+largestBST(root.right)+1;
        return Math.max(largestBST(root.left),largestBST(root.right));
    }

    private boolean isBST(TreeNode root, int minValue, int maxValue) {
        if(root == null){
            return true;
        }
        if(root.val <= minValue || root.val >= maxValue)
            return false;
        return isBST(root.left,minValue,root.val) && isBST(root.right,root.val,maxValue);
    }

    public static void main(String[] args) {
        TreeNode root  = new TreeNode(6);
        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(8);
        TreeNode left2 = new TreeNode(3);
        TreeNode left2right = new TreeNode(5);
        TreeNode right2right = new TreeNode(9);
        root.left = left1;
        root.right = right1;
        left1.left = left2;
        left1.right = left2right;
        right1.right = right2right;
        LargestBinaryTree largestBinaryTree = new LargestBinaryTree();
        int result = largestBinaryTree.largestBST(root);
        System.out.println("Result "+result);
    }

}
