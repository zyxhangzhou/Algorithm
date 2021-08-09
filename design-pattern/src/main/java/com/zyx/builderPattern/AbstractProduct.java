package com.zyx.builderPattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhangyuxiao
 * @date 2021-08-06 18:02
 * @description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractProduct {
    protected String name;
    protected String partA;
    protected String partB;
    
}
