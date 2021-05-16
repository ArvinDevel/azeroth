package me.jinsui.java8;

import java.util.function.Supplier;

/**
 * 函数式接口的注解检查
 */
@FunctionalInterface
public interface DefaultMethodOfInterface {
    void traditionalMethod(String arg0);

    /**
     * 默认方法实现，便于接口升级，新增方法而不用更新原有子类
     */
    default void methodWithDefaultImpl() {
        System.out.println("methodWithDefaultImpl in interface");
    }

//    default String toString() {
//        return "overrid Object's method";
//    }

    /**
     * 提供类似工厂方法；替代集合等工具类
     *
     * @return
     */
    static String getInstance(Supplier supplier) {
        return "getInstance from interface";
    }

}
