package com.zyx.abstractFactoryPattern.carImpl;

import com.zyx.abstractFactoryPattern.interfaces.Car;

/**
 * @author zhangyuxiao
 * @date 2021-07-30 15:03
 * @description
 */
public class Ferrari implements Car {
    @Override
    public void drive() {
        System.out.println("I'm driving "+ Ferrari.class.getSimpleName() +" 430! Is that a dream? Am I in a dream right now?");
    }
}
