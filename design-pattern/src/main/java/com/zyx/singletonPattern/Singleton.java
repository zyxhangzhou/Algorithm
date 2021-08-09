package com.zyx.singletonPattern;

/**
 * @author zhangyuxiao
 * @date 2021-08-06 17:04
 * @description 单例模式的实现
 */
public class Singleton {
    private static volatile Singleton singleton;

    private Singleton() {
    }

    /**
     * 双检锁/双重校验锁（DCL，即 double-checked locking）
     * 但这种方法也渐渐被淘汰
     *
     * @return Singleton
     */
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                // 如果下面没有判空，试想一下，竞争AB都进入于此，会怎样？
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }


    private static class SingleTonHolder {
        private static Singleton INSTANCE = new Singleton();
    }

    /**
     * 静态内部类方法
     *
     * @return Singleton
     */
    public static Singleton getInstance2() {
        return SingleTonHolder.INSTANCE;
    }


}
