package me.jinsui;

public class SpecialBinarySearch {
    public static void main(String[] args) {
        int nums[] = new int[]{7, 8, 9, 1, 2, 3};
        int result = binarySearch(nums, 0, 5, 2);
        System.out.println(result);
    }

    private static int binarySearch(int nums[], int left, int right, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (left > right) {
            return -1;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int leftVal = nums[left];
            int rightVal = nums[right];
            int midVal = nums[mid];

            if (midVal == target) {
                return mid;
            }

            // 单调递增
            if (leftVal < rightVal) {
                if (midVal > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 左半段递增；右半段递增
                // 不在右半段
                if (target > rightVal) {
                    if (midVal > target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                    //9, 1, 2, 3
                } else {
                    // 和上述分支一摸一样时，肯定wrong!
                    if (midVal > target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }
        return -1;
    }
}
