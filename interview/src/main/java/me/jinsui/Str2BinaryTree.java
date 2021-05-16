package me.jinsui;


/**
 * 给定合法的字符串（值为字母，括号是完美配对的），给出其二叉树
 * 示例：A(B(C,D(,)),E)
 * 输出树如下：
 *     A
 *   B   E
 *  C D
 *
 */
public class Str2BinaryTree {

    /**
     * 注意事项：递归的基本单元，是A（，）？A，应该选择最小的完整单元，故选择前者
     * 1.判断终止条件：str非法，长度为0/1
     * 2.处理本层：构建树，填入值，找到左子树，右子树的子串
     * 3.递归调用下层：
     * 4.清理本层：无
     *
     * @param str
     * @return
     */
    static Node<Character> recursiveBuildBinaryTree(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Character rootValue = str.charAt(0);
        Node<Character> root = new Node(rootValue);
        if (str.length() == 1) {
            // assert content is valid
            return root;
        }

        int leftBrackNum = 0;
        int commaLocation = 2;
        for (int i = 2; i < str.length() - 1; i++) {
            char current = str.charAt(i);
            if (current == ',' && leftBrackNum == 0) {
                commaLocation = i;
                break;
            }
            if (current == '(') {
                leftBrackNum++;
            }
            if (current == ')') {
                leftBrackNum--;
            }
        }
        Node<Character> left = recursiveBuildBinaryTree(str.substring(2, commaLocation));
        root.setLeft(left);
        Node<Character> right = recursiveBuildBinaryTree(str.substring(commaLocation + 1, str.length() - 1));
        root.setRight(right);
        return root;
    }

    public static void main(String[] args) {
        Node tree = Str2BinaryTree.recursiveBuildBinaryTree("A(B(C,D(,)),E)");
        System.out.println("preOrder");
        Node.preTraverse(tree);
        System.out.println("midOrder");
        Node.midTraverse(tree);
        System.out.println("postOrder");
        Node.postTraverse(tree);
    }


}
