package me.jinsui.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给出一组数字，返回该组数字的所有排列
 * 例如：
 * [1,2,3]的所有排列如下
 * [1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2], [3,2,1].
 * （以数字在数组中的位置靠前为优先级，按字典序排列输出。）
 */
public class Permulation {
    public static void main(String[] args) {
        permute(new int[]{4, 2, 3});
    }

    public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        permute(num, 0, result);
        return result;
    }

    public static void permute(int[] num, int index, ArrayList<ArrayList<Integer>> result) {
        if (index == num.length) {
            ArrayList<Integer> current = new ArrayList<>();
            for (int val : num) {
                current.add(val);
            }
            result.add(current);
            System.out.println(current);
            return;
        }

        for (int i = index; i < num.length; i++) {
            swap(num, index, i);
            permute(num, index + 1, result);
            swap(num, index, i);
        }

    }

    private static void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }

}
