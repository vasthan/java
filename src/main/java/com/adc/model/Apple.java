package com.adc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Apple implements Serializable {
    /**
     * 颜色
     */
    private String color;

    /**
     * 重量
     */
    private int weight;

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public boolean isGreenApple() {
        return "green".equals(this.getColor());
    }

    public boolean isHeavyApple() {
        return this.getWeight() > 300;
    }
}
