package com.jackywong.parser;

/**
 * @Author: 黄子奇
 * @Date: 2019/9/21 15:58
 */
public class Tuple<A,B> {
    private A _1;
    private B _2;

    public Tuple(A _1, B _2) {
        this._1 = _1;
        this._2 = _2;
    }

    public A get_1() {
        return _1;
    }

    public void set_1(A _1) {
        this._1 = _1;
    }

    public B get_2() {
        return _2;
    }

    public void set_2(B _2) {
        this._2 = _2;
    }
}
