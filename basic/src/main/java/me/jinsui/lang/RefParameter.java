package me.jinsui.lang;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Java参数是值传递还是引用传递？
 * 参数传递的永远是一个副本（基本变量、引用变量）
 * 参数传递的是对象（对象引用），在方法内改变其指向后，方法外使用的仍是原引用
 * https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value
 * <p>
 * 想要改变数据有两种做法：返回值；可变对象+修改内容
 * 注意点：在方法内修改作为参数传递进来的集合类（数组、list等）的元素，外部可见，相当于方法2
 */
public class RefParameter {
    public static void main(String[] args) {
        int ori = 1;
        passBasicDataType(ori);
        System.out.println(ori);
        Object obj = new Object();
        System.out.println("ori " + obj);
        passRefDataType(obj);
        System.out.println("after ori " + obj);

        System.out.println(getChangedDataUsingResp(ori));
        ChangeableInt changeable = new ChangeableInt(ori);
        getChangedDataUsingChangeableObj(changeable);
        System.out.println(changeable.getIntVal());
    }

    /**
     * 不改变原值，因为传递的是副本
     *
     * @param ori
     */
    private static void passBasicDataType(int ori) {
        ori = ori + 2;
    }

    /**
     * 不改变原值，因为传递的是引用的指针副本
     *
     * @param ori
     */
    private static void passRefDataType(Object ori) {
        ori = new Object();
    }

    /**
     * 方法1：使用返回值
     *
     * @param ori
     */
    private static int getChangedDataUsingResp(int ori) {
        ori = ori + 2;
        return ori;
    }

    /**
     * 方法2：使用可变对象，方法内修改对象内容
     *
     * @param changeableObj
     */
    private static void getChangedDataUsingChangeableObj(ChangeableInt changeableObj) {
        int oriContent = changeableObj.getIntVal();
        changeableObj.setIntVal(oriContent + 2);
    }

    @AllArgsConstructor
    @Setter
    @Getter
    static class ChangeableInt {
        private int intVal;
    }
}
