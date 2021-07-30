package com.zyx.abstractFactoryPattern.factories;

import com.zyx.abstractFactoryPattern.carImpl.BMW;
import com.zyx.abstractFactoryPattern.carImpl.Ferrari;
import com.zyx.abstractFactoryPattern.carImpl.MercedesBenz;
import com.zyx.abstractFactoryPattern.interfaces.Car;
import com.zyx.abstractFactoryPattern.interfaces.Color;


/**
 * @author zhangyuxiao
 * @date 2021-07-30 15:10
 * @description
 */
public class CarFactory extends AbstractFactory {

    @Override
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

    @Override
    public Color getColor(String colorType) {
        return null;
    }
}
