package me.jinsui.yunzhanghu;

import java.util.*;


/**
 * 带干扰字符的括号匹配
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(isValid("{*(*)*[*()*(**)**]**}"));
        System.out.println(isValid("{**[**)**(**]**}"));
    }

    private static Set<Character> leftCharSet
            = new HashSet<Character>() {{
        add('{');
        add('(');
        add('[');
    }};


    private static Set<Character> rightCharSet
            = new HashSet<Character>() {{
        add('}');
        add(')');
        add(']');
    }};

    private static boolean isValid(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        Deque stack = new ArrayDeque();
        //会抛出异常
//        stack.remove()/remove
        List<Character> charList = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            if (leftCharSet.contains(ch)) {
                charList.add(ch);
            }
            if (rightCharSet.contains(ch)) {
                // NOTE: 不一定有值；
                // 没有可用的左括号
                if (charList.size() == 0) {
                    return false;
                }
                // 移除最后一个左括号
                char lastChar = charList.remove(charList.size() - 1);
                // 注意初始化
                char expectedChar = 'x';
                switch (ch) {
                    case '}':
                        expectedChar = '{';
                        break;
                    case ']':
                        expectedChar = '[';
                        break;
                    case ')':
                        expectedChar = '(';
                        break;
                }
                if (lastChar != expectedChar) {
                    return false;
                }
            }// end of if
        }

        if (charList.size() == 0) {
            return true;
        }
        //左边有多余的括号
        return false;
    }
}