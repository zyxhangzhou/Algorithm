package com.zyx.factoryPattern.impl;

import com.zyx.factoryPattern.interfaces.Car;

/**
 * @author zhangyuxiao
 * @date 2021-07-30 15:01
 * @description
 */
public class BMW implements Car {
    @Override
    public void drive() {
        System.out.println("I'm driving " + BMW.class.getSimpleName() + " m4! THE dream car!");
    }
}
