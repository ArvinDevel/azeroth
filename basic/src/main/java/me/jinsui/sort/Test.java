package me.jinsui.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        permute(new int[]{4, 2, 3});
    }

    public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (null == num || num.length == 0) {
            return result;
        }
        ArrayList<Integer> first = new ArrayList<>();
        permute(num, 0, first, result);
        System.out.println(result);
        return result;
    }

    public static void permute(int[] num, int index, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> result) {
        if (index == num.length - 1) {
            current.add(num[index]);
            result.add(current);
            //System.out.println(current);
            return;
        }
        // 按序输出结果
        Arrays.sort(num, index, num.length);
        for (int i = index; i < num.length; i++) {
            swap(num, index, i);
            ArrayList<Integer> next = new ArrayList(current);
            next.add(num[index]);
            permute(num, index + 1, next, result);
            swap(num, index, i);
        }
    }

    private static void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }

}