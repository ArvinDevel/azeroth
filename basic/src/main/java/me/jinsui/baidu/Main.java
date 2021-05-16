package me.jinsui.baidu;


import lombok.*;

/**
 * list,n,k reverse
 * 1,2,3,4,5,6
 * <p>
 * n=6,k=4
 * 432156
 */
public class Main {
    @AllArgsConstructor
    @Setter
    @Getter
    @NoArgsConstructor
    static class Node {
        int val;
        Node next;

        @Override
        public String toString() {
            return val + "";
        }
    }

    public static void main(String[] args) {
        Node a = new Node(1, null);
        Node b = new Node(2, a);
        Node c = new Node(3, b);
        traverseList(c);
//        System.out.println("after reverse");
//        traverseList(reverseUsingIte(c));
        System.out.println("after reverse by k");
        traverseList(reverseByK(c, 2));
    }

    public static void traverseList(Node node) {
        Node current = node;
        if (current == null) {
            System.out.println("empty list");
        }
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    public static Node reverseByK(Node node, int k) {
        int cnt = 0;
        Node pre = null;
        Node currentPre = null;
        Node current = node;
        Node currentStepStart = current;
        while (current != null) {
            cnt++;
            if (cnt == k) {
                cnt = 0;
                currentPre = current;
                Node tmp = current.next;
                //手动截断，便于独立反转
                current.next = null;
                Node reverseResult = reverseUsingIte(currentStepStart);
                reverseResult.next = pre;
                pre = reverseResult;

                currentStepStart = tmp;
                current = tmp;
            } else {
                currentPre = current;
                current = current.next;
            }
        }
        return currentPre;
    }

    /**
     * 1,2,3-->3,2,1
     *
     * @param root
     * @return
     */
    private static Node reverse(Node root) {
        if (root == null || root.next == null) {
            return root;
        }
        Node newPre = reverse(root.next);
        newPre.next = root;
        return root;
    }

    private static Node reverseUsingIte(Node root) {

        if (root == null || root.next == null) {
            return root;
        }
        Node pre = null;
        Node current = root;
        while (current != null) {
            Node tmp = current.next;
            current.next = pre;
            pre = current;
            current = tmp;
        }
        return pre;
    }


}
