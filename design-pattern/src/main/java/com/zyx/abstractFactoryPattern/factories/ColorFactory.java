package com.zyx.abstractFactoryPattern.factories;

import com.zyx.abstractFactoryPattern.colorImpl.Blue;
import com.zyx.abstractFactoryPattern.colorImpl.Red;
import com.zyx.abstractFactoryPattern.colorImpl.Yellow;
import com.zyx.abstractFactoryPattern.interfaces.Car;
import com.zyx.abstractFactoryPattern.interfaces.Color;

/**
 * @author zhangyuxiao
 * @date 2021-07-30 16:05
 * @description
 */
public class ColorFactory extends AbstractFactory {
    @Override
    public Car getCar(String carType) {
        return null;
    }

    @Override
    public Color getColor(String colorType) {
        if (colorType == null) {
            return null;
        }
        if (colorType.equalsIgnoreCase(Color.RED)) {
            return new Red();
        } else if (colorType.equalsIgnoreCase(Color.BLUE)) {
            return new Blue();
        } else if (colorType.equalsIgnoreCase(Color.YELLOW)) {
            return new Yellow();
        }
        return null;
    }
}
