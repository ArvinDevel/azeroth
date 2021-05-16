import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor()
public class TreeNode<T> {
    final T value;
    TreeNode left;
    TreeNode right;

    static void preTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        preTraverse(node.left);
        preTraverse(node.right);
    }

    static void midTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        midTraverse(node.left);
        System.out.println(node.value);

        midTraverse(node.right);
    }

    static void postTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        postTraverse(node.left);
        postTraverse(node.right);
        System.out.println(node.value);
    }
}
