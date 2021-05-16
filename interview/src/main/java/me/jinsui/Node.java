package me.jinsui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor()
public class Node<T> {
    final T value;
    Node left;
    Node right;

    static void preTraverse(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        preTraverse(node.left);
        preTraverse(node.right);
    }

    static void midTraverse(Node node) {
        if (node == null) {
            return;
        }
        midTraverse(node.left);
        System.out.println(node.value);

        midTraverse(node.right);
    }

    static void postTraverse(Node node) {
        if (node == null) {
            return;
        }
        postTraverse(node.left);
        postTraverse(node.right);
        System.out.println(node.value);
    }
}
