package com.jackywong.parser;

import java.util.function.Function;

/**
 * @Author: 黄子奇
 * @Date: 2019/9/21 17:00
 */
@FunctionalInterface
public interface Parser<A> extends Function<ParseState,A> {

}
