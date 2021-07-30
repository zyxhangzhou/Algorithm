package com.zyx.factoryPattern.interfaces;

/**
 * @author zhangyuxiao
 * @date 2021-07-30 14:55
 * @description Common Car
 */
public interface Car {
    String MERCEDES_BENZ = "MercedesBenz";
    String BMW = "BMW";
    String FERRARI = "Ferrari";
    /**
     * drive!
     */
    void drive();
}
