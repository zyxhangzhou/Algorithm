package com.zyx.abstractFactoryPattern.colorImpl;

import com.zyx.abstractFactoryPattern.interfaces.Color;

/**
 * @author zhangyuxiao
 * @date 2021-07-30 15:55
 * @description
 */
public class Blue implements Color {
    @Override
    public void paint() {
        System.out.println("Painting this car into " + Blue.class.getSimpleName() + "!");
    }
}
