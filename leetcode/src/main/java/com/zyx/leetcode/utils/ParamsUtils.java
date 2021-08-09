package com.zyx.leetcode.utils;

import org.apache.commons.beanutils.ConvertUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhangyuxiao
 * @date 2021-07-28 11:05
 * @description
 */
public class ParamsUtils {
    public static List<String> params = new ArrayList<>();

    static {
        try {
            final String path = Objects.requireNonNull(ParamsUtils.class.getClassLoader().getResource("params.txt")).getPath();
            final BufferedReader in = new BufferedReader(new FileReader(path));
            String str;
            while ((str = in.readLine()) != null) {
                params.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String basicConvert(String param) {
        return param.replaceAll("([\\[])", "{").replaceAll("(])", "}");
    }

    public static Integer[] array2Integers(String param) {
        return (Integer[]) ConvertUtils.convert(param.substring(1, param.length() - 1).split(","), Integer.class);
    }

    //    public static Integer[] array2Strings(String param) {
//
//    }
    public static void main(String[] args) {
        System.out.println(basicConvert("[[],[0,2,3,4],[3],[4],[]]"));
    }
}
