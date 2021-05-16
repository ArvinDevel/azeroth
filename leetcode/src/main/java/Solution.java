class Solution {
    public static void main(String[] args) {
        double r = new Solution().findMedianSortedArrays(new int[]{1}, new int[]{});
        System.out.println(r);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        int targetIdx1 = len / 2;
        int targetIdx2 = (len & 1) == 1 ? targetIdx1 : targetIdx1 - 1;
        return calculateMedianUsingMerge(nums1, nums2, targetIdx1, targetIdx2);
    }

    private double calculateMedianUsingMerge(int[] nums1, int[] nums2,
                                             int targetIdx1, int targetIdx2) {
        int currentIdx = 0;
        int currentVal = 0;
        int leftIdx = 0;
        int rightIdx = 0;
        int targetVal1 = 0;
        int targetVal2 = 0;
        int finished = 2;
        while (leftIdx < nums1.length && rightIdx < nums2.length) {
            if (nums1[leftIdx] < nums2[rightIdx]) {
                currentVal = nums1[leftIdx];
                leftIdx++;
            } else {
                currentVal = nums2[rightIdx];
                rightIdx++;
            }
            if (currentIdx == targetIdx2) {
                targetVal2 = currentVal;
                finished--;
            }
            if (currentIdx == targetIdx1) {
                targetVal1 = currentVal;
                finished--;
            }
            if (finished == 0) {
                return calculateUsingDouble(targetVal1, targetVal2);
            }
            currentIdx++;
        }
        // 只剩左侧数组
        if (leftIdx < nums1.length) {
            // 注意处理第一轮合并是否已得到部分结果
            if (finished == 0) {
                return calculateUsingDouble(nums1[targetIdx1 - currentIdx - leftIdx]
                        , nums1[targetIdx2 - currentIdx - leftIdx]);
            } else if (finished == 1) {
                return calculateUsingDouble(nums1[targetIdx1 - currentIdx - leftIdx]
                        , targetVal2);
            }
            return calculateUsingDouble(nums1[targetIdx1 - currentIdx - leftIdx]
                    , nums1[targetIdx2 - currentIdx - leftIdx]);

        }
        if (rightIdx < nums2.length) {
            //三种状态都会出现
            if (finished == 0) {
                return calculateUsingDouble(nums2[targetIdx1 - currentIdx - rightIdx]
                        , nums2[targetIdx2 - currentIdx - rightIdx]);
            } else if (finished == 1) {
                return calculateUsingDouble(nums2[targetIdx1 - currentIdx - rightIdx]
                        , targetVal2);
            }
            return calculateUsingDouble(nums2[targetIdx1 - currentIdx - rightIdx]
                    , nums2[targetIdx2 - currentIdx - rightIdx]);
        }
        return -1;
    }

    private double calculateUsingDouble(int a, int b) {
        return (a + b + 0.0) / 2;
    }
}