package com.zyx.abstractFactoryPattern.factories;

/**
 * @author zhangyuxiao
 * @date 2021-07-30 16:07
 * @description
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("CAR")){
            return new CarFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
