package com.zyx.abstractFactoryPattern;

import com.zyx.abstractFactoryPattern.factories.AbstractFactory;
import com.zyx.abstractFactoryPattern.factories.FactoryProducer;
import com.zyx.abstractFactoryPattern.interfaces.Car;
import com.zyx.abstractFactoryPattern.interfaces.Color;

/**
 * @author zhangyuxiao
 * @date 2021-07-30 16:08
 * @description
 */
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        final AbstractFactory carFactory = FactoryProducer.getFactory("car");
        final AbstractFactory colorFactory = FactoryProducer.getFactory("color");
        assert carFactory != null;
        final Car bmw = carFactory.getCar("BMW");
        assert colorFactory != null;
        final Color red = colorFactory.getColor("red");
        bmw.drive();
        red.paint();
    }
}
