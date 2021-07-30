package com.zyx.abstractFactoryPattern.factories;

import com.zyx.abstractFactoryPattern.interfaces.Car;
import com.zyx.abstractFactoryPattern.interfaces.Color;

/**
 * @author zhangyuxiao
 * @date 2021-07-30 15:57
 * @description
 */
public abstract class AbstractFactory {
    public abstract Car getCar(String carType);

    public abstract Color getColor(String colorType);
}
