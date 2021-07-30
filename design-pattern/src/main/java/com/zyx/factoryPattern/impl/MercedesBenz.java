package com.zyx.factoryPattern.impl;

import com.zyx.factoryPattern.interfaces.Car;

/**
 * @author zhangyuxiao
 * @date 2021-07-30 14:57
 * @description
 */
public class MercedesBenz implements Car {
    @Override
    public void drive() {
        System.out.println("I'm driving " + MercedesBenz.class.getSimpleName() + "! AMG C63-S! \"S\" does matter!");
    }
}
