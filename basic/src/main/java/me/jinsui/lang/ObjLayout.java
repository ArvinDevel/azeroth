package me.jinsui.lang;

import org.openjdk.jol.info.ClassLayout;

/**
 * 1.MarkWord（锁标记位及相关信息，如指针）
 * 2.Kclass pointer
 * 3.length（array）
 * 4.instance data
 * 5.padding
 */
public class ObjLayout {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
