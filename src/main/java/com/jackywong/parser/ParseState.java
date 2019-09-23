package com.jackywong.parser;

/**
 * @Author: 黄子奇
 * @Date: 2019/9/21 15:54
 */
public class ParseState {
    private Location loc;

    public ParseState(Location loc) {
        this.loc = loc;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }
}
