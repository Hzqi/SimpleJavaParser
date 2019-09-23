package com.jackywong.parser;

/**
 * @Author: 黄子奇
 * @Date: 2019/9/21 15:52
 */
public class Location {
    private String input;
    private Integer offset;

    public Location(String input, Integer offset) {
        this.input = input;
        this.offset = offset;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
