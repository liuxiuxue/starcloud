package com.star.auth.entity;

/**
 * @author: liuxiuxue
 * @date: 2022/3/12 14:02
 */
public enum Sex {

    MALE("male",1),
    FEMALE("female",0);

    private String name;

    private Integer value;

    private Sex(String name, Integer value){
        this.name = name;
        this.value =value;
    }
}
