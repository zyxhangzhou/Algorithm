package com.zyx.leetcode.utils;

import org.apache.commons.beanutils.ConvertUtils;

import java.util.Arrays;

/**
 * @author zhangyuxiao
 * @date 2021-07-27 20:31
 * @description
 */
public class ArrayUtils {
    public static Integer[] buildArray(String s) {
        return (Integer[]) ConvertUtils.convert(s.substring(1, s.length() - 1).split(","), Integer.class);
    }

    //TODO 完成二维构建
    public static Integer[] build2DArray(String s) {
       return null;
    }

    public static void main(String[] args) {
        Integer[] x = buildArray("[2,3,4,5,6,7,7]");
        System.out.println(Arrays.toString(x));
    }
}
