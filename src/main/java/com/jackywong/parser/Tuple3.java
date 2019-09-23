package com.jackywong.parser;

/**
 * @Author: 黄子奇
 * @Date: 2019/9/21 16:02
 */
public class Tuple3<A,B,C> {
    private A _1;
    private B _2;
    private C _3;

    public Tuple3(A _1, B _2, C _3) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
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

    public C get_3() {
        return _3;
    }

    public void set_3(C _3) {
        this._3 = _3;
    }
}
