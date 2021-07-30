package com.zyx.factoryPattern;

import com.zyx.factoryPattern.factory.CarFactory;
import com.zyx.factoryPattern.interfaces.Car;

/**
 * @author zhangyuxiao
 * @date 2021-07-30 15:10
 * @description
 */
public class FactoryPatternDemo {
    public static void main(String[] args) {
        final CarFactory carFactory = new CarFactory();
        carFactory.getCar(Car.BMW).drive();
        carFactory.getCar(Car.MERCEDES_BENZ).drive();
        carFactory.getCar(Car.FERRARI).drive();
    }
}
