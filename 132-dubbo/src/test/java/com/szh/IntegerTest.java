package com.szh;


public class IntegerTest {

    public static void main(String[] args) {

        // Integer Short Long Boolean Character 自动装箱，实际调用的是缓存。
        Integer i1 = 33;   // == Integer.valueOf(33);
//        i1.intValue();  // 自动拆箱。
        Integer i2 = 33;
        System.out.println(i1 == i2);// 输出 true

        // 两种浮点数类型的包装类 Float,Double 并没有实现缓存机制
        Float i11 = 333f;
        Float i22 = 333f;
        System.out.println(i11 == i22);// 输出 false

        Double i3 = 1.2;
        Double i4 = 1.2;
        System.out.println(i3 == i4);// 输出 false
    }
}
