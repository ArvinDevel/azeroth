package me.jinsui;

/**
 * 一个数组代表高程，只有高度递增的认定为山，求最大山宽
 * NOTE：类似dp，处理好指针推进过程中的细节
 */
public class MaxWidthMountain {

    public static void main(String[] args) throws java.lang.Exception {
        testNormal();
        testAbnormal();
    }

    private static int largestWidth(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int currentWidth = 0;
        int maxWidth = 0;
        boolean increased = false;
        boolean reset = true;
        int before = nums[0];
        // 不是山
        if (nums.length == 1) {
            return 0;
        }

        for (int i = 1; i < nums.length; i++) {
            int currentVal = nums[i];
            // 只能先增长
            if (reset) {
                if (currentVal > before) {
                    currentWidth++;
                    reset = false;
                    increased = true;
                }
                // 只能增长或下降
            } else {
                if (increased) {
                    // 重置山峰
                    if (currentVal == before) {
                        // 不能更新宽度！
                        //if(currentWidth> maxWidth){
                        //     maxWidth = currentWidth;
                        //}
                        currentWidth = 0;
                        reset = true;
                    } else if (currentVal > before) {
                        currentWidth++;
                    } else {
                        currentWidth++;
                        increased = false;
                    }
                } else {
                    // 只能下降
                    if (currentVal == before) {
                        if (currentWidth > maxWidth) {
                            maxWidth = currentWidth;
                        }
                        currentWidth = 0;
                        reset = true;
                    } else if (currentVal > before) {
                        //直接开启下一个山峰
                        if (currentWidth > maxWidth) {
                            maxWidth = currentWidth;
                        }
                        currentWidth = 1;
                        increased = true;
                    } else {
                        currentWidth++;
                    }
                }

            }

            // 推进指针
            before = currentVal;
        }

        // update last width
        // 只能下降时更新max！
        if (!increased) {
            if (currentWidth > maxWidth) {
                maxWidth = currentWidth;
            }
        }


        if (maxWidth > 0) {
            return maxWidth + 1;
        } else {
            return 0;
        }
    }

    private static void testNormal() {
        // 4
        int[] normal = new int[]{1, 2, 3, 2, 2, 4, 4};
        System.out.println("expected 4 actual:" + largestWidth(normal));
    }

    private static void testAbnormal() {
        // 0
        int[] num = increasedNum();
        System.out.println("expected 0 actual:" + largestWidth(num));

        // 0
        num = decreasedNum();
        System.out.println("expected 0 actual:" + largestWidth(num));

        // 0
        num = flateNum();
        System.out.println("expected 0 actual:" + largestWidth(num));

        num = multipleN();

        System.out.println(num + "expected ? actual:" + largestWidth(num));

    }


    private static int[] multipleN() {
        return new int[]{1, 2, 3, 2, 2, 5, 6, 7, 5, 8, 9, 7};
    }

    private static int[] increasedNum() {
        return new int[]{1, 2, 3};
    }

    private static int[] decreasedNum() {
        return new int[]{4, 2, 1};
    }

    private static int[] flateNum() {
        return new int[]{1, 2, 2};
    }
}
