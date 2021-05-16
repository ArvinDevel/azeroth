package me.jinsui.sort;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 基于partition算法，选择旋转点，然后将小于其值的放其左侧，大于其值的放其右侧
 * 双指针，用于快速交换小于值和大于值
 */
public class QuickSort {
    /**
     * 左右逼近的双指针方式
     *
     * @param data
     * @param l
     * @param r
     * @return
     */
    private static int partitionWithTwoSide(int data[], int l, int r) {
        // 随机选取枢轴元素
        swap(data, l, ThreadLocalRandom.current().nextInt() % (r - l + 1) + l);
        int v = data[l];
        // [l+1, i] <= v, [j, r) >= v
        int i = l + 1, j = r;
        while (true) {
            // 依次找到两边不满足的元素，交换
            while (i <= r && data[i] < v) i++;
            while (j >= l + 1 && data[j] > v) j--;
            if (i > j) break;
            swap(data, i, j);
            i++;
            j--;
        }
        swap(data, l, j);
        return j;
    }

    private static void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }

}
