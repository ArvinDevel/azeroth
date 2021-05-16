package me.jinsui;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class NaiveSolution {
    public static void main(String[] args) {
        test_simple();
        test_zero();
    }

    private static void test_simple() {
        int[] array = new int[]{1, 1, 1, 2, 2, 3};
        ArrayBasedDataSource arrayBasedDataSource = new ArrayBasedDataSource(array);
        ResultContainer initCtn = new ResultContainer();
        List<ResultContainer> resultContainers = new ArrayList<>();
        int targetNum = 6;
        chooseCombineRecursive(arrayBasedDataSource, targetNum, 0, initCtn, resultContainers);
        // 10
        System.out.println(resultContainers.stream().count());
    }

    // [C(0,2) + C(1,2) + C(2,2)] * result_without_zero
    private static void test_zero() {
        int[] array = new int[]{1, 1, 1, 0, 0, 2, 2, 3};
        ArrayBasedDataSource arrayBasedDataSource = new ArrayBasedDataSource(array);
        ResultContainer initCtn = new ResultContainer();
        List<ResultContainer> resultContainers = new ArrayList<>();
        int targetNum = 6;
        chooseCombineRecursive(arrayBasedDataSource, targetNum, 0, initCtn, resultContainers);
        // 40
        System.out.println(resultContainers.stream().count());
    }

    /**
     * f(n) = f(n-1)+f(n-2) +... +f(1) --> 2^n 指数级复杂度
     * Note: 选择一个数字 和 不选择该数字 构成该步骤解的全集
     * <p>
     * 整体来看，一些组合即选择一些数字C(m,n),每个数字有位于该集合和不位于两种情况，故状态空间为2^n
     * <p>
     * TODO：industry level solution：change recursive to traverse；dynamic programming；
     * 可能的解放：计算数字集合（利用map<int,list>记录每个数字的多个存储位置，计算集合中的解，根据某个数字可以由其他数字组成（3=1，1，1）进行替换得出结果；
     *
     * @param dataSource
     * @param targetNum
     * @param currentLine
     * @param processingPath
     * @param results
     */
    private static void chooseCombineRecursive(DataSource dataSource, int targetNum, int currentLine, ResultContainer processingPath, List<ResultContainer> results) {
        if (currentLine >= dataSource.getLength()) {
            return;
        }
        for (int i = currentLine; i < dataSource.getLength(); i++) {
            int currentVal = dataSource.get(i);
            ResultContainer newPath = new ResultContainer(processingPath);
            newPath.add(i);
            if (currentVal == targetNum) {
                results.add(new ResultContainer(newPath));
            }
            chooseCombineRecursive(dataSource, targetNum - currentVal, i + 1, newPath, results);
        }
    }

    @ToString
    static class ResultContainer {
        List<Integer> lineNums;

        public ResultContainer() {
            lineNums = new ArrayList<>();
        }

        public ResultContainer(ResultContainer other) {
            lineNums = new ArrayList<>(other.lineNums);
        }

        public void add(int line) {
            lineNums.add(line);
        }
    }

    /**
     * todo:File based DataSource
     */
    static class ArrayBasedDataSource implements DataSource {
        int[] array;

        public ArrayBasedDataSource(int[] array) {
            this.array = array;
        }

        @Override
        public int getLength() {
            return array.length;
        }

        @Override
        public int get(int idx) {
            if (idx > array.length - 1) {
                throw new IllegalArgumentException("idx outOfBound of array");
            }
            return array[idx];
        }
    }
}
