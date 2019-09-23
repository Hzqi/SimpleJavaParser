package com.jackywong.parser;

import java.util.function.Supplier;

/**
 * @Author: 黄子奇
 * @Date: 2019/9/21 17:03
 */
@FunctionalInterface
public interface LazyParser<A> extends Supplier<Parser<A>> {
}
