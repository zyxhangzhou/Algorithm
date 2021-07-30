package com.zyx.factoryPattern.factory;

import com.zyx.factoryPattern.impl.BMW;
import com.zyx.factoryPattern.interfaces.Car;
import com.zyx.factoryPattern.impl.Ferrari;
import com.zyx.factoryPattern.impl.MercedesBenz;

/**
 * @author zhangyuxiao
 * @date 2021-07-30 15:10
 * @description
 */
public class CarFactory {
    public Car getCar(String carType) {
        if (carType == null) {
            return null;
        }
        if (carType.equalsIgnoreCase(Car.MERCEDES_BENZ)) {
            return new MercedesBenz();
        } else if (carType.equalsIgnoreCase(Car.BMW)) {
            return new BMW();
        } else if (carType.equalsIgnoreCase(Car.FERRARI)) {
            return new Ferrari();
        }
        return null;
    }
}
