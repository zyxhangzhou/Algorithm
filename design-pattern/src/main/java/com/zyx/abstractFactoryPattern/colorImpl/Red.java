package com.zyx.abstractFactoryPattern.colorImpl;

import com.zyx.abstractFactoryPattern.interfaces.Color;

/**
 * @author zhangyuxiao
 * @date 2021-07-30 15:54
 * @description
 */
public class Red implements Color {
    @Override
    public void paint() {
        System.out.println("Painting this car into " + Red.class.getSimpleName() + "!");
    }
}
