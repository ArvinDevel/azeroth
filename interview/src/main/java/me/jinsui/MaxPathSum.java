package me.jinsui;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 求二叉树中的一条路径的最大值，其中路径不分叉
 * 1）左子树最大路径值
 * 2）右子树最大路径值
 * 3）左+右+root的最大路径值
 * 上述三种可以递归调用实现
 * 4）某个孙子树最大路径值（上层的节点为负数的情况）
 * 这种情况需要在计算过程中记录最大值
 * NOTE:别忘了第4种情况
 */
public class MaxPathSum {
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Node<Integer> node = new Node<>(0);
        Integer test = Integer.MIN_VALUE;
        maxPathSum(node, test);
        System.out.println(max);
        System.out.println(test);
    }

    // todo: 只是基础数字对象没有赋值嘛？传值？
    static int maxPathSum(Node<Integer> node,Integer integer) {
        if (node == null) {
            return 0;
        }
        AtomicInteger integer1;
        integer = new Integer(10);
        int leftMaxPathSum = maxPathSum(node.left,integer);
        int rightMaxPathSum = maxPathSum(node.right,integer);
        int rootVal = node.value;

        int leftPahSum = leftMaxPathSum + rootVal;
        int rightPahSum = rightMaxPathSum + rootVal;
        int treeSum = leftMaxPathSum + rootVal + rightMaxPathSum;

        updateMax(rootVal, leftPahSum, rightPahSum, treeSum);
        int subPathSum = rightMaxPathSum > leftMaxPathSum ? rightMaxPathSum : leftMaxPathSum;
        return rootVal > rootVal + subPathSum ? rootVal : rootVal + subPathSum;
    }

    /**
     * 更新史上最好的路径和
     *
     * @param args
     * @return
     */
    public static void updateMax(int... args) {
        for (int v : args) {
            if (v > max) {
                max = v;
            }
        }
    }
}
