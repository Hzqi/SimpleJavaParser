package com.jackywong.parser;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @Author: 黄子奇
 * @Date: 2019/9/21 17:00
 */
@FunctionalInterface
public interface Parser<A> extends Function<ParseState,ParseResult<A>> {

    default Parser<A> label(String name) {
        return state -> {
            ParseResult<A> res = this.apply(state);
            if(res.isSuccess()) {
                return res;
            } else {
                ParseError err = res.getError();
                err.setMsg(name);
                return res;
            }
        };
    }

    default Parser<A> scope(String name) {
        return state -> {
            ParseResult<A> res = this.apply(state);
            if(res.isSuccess()) {
                return res;
            } else {
                ParseError err = res.getError();
                err.setMsg("in scope:" + name + " " + err.getMsg());
                return res;
            }
        };
    }

    default Parser<String> slice() {
        return state -> {
            ParseResult<A> res = this.apply(state);
            if(res.isSuccess()) {
                Integer len = res.getLength();
                String newStr = state.getLoc().getInput().substring(state.getLoc().getOffset(), state.getLoc().getOffset() + len);
                return new ParseResult.Success<>(newStr,len);
            } else {
                return new ParseResult.Failure<>(res.getError());
            }
        };
    }

    default <B> Parser<B> map(Function<A,B> f) {
        return state -> {
            ParseResult<A> res = this.apply(state);
            if(res.isSuccess()) {
                Integer len = res.getLength();
                B value = f.apply(res.getSuccess());
                return new ParseResult.Success<>(value, len);
            } else {
                return new ParseResult.Failure<>(res.getError());
            }
        };
    }

    default <B,C> Parser<C> map2(LazyParser<B> p2, BiFunction<A,B,C> f) {
        return state -> {
            ParseResult<A> res1 = this.apply(state);
            if(res1.isSuccess()) {
                Integer afterRes = state.getLoc().getOffset() + res1.getLength();
                ParseState state2 = new ParseState(new Location(state.getLoc().getInput(), afterRes));

                ParseResult<B> res2 = p2.get().apply(state2);
                if(res2.isSuccess()) {
                    Integer length = res2.getLength() + res1.getLength();
                    C value = f.apply(res1.getSuccess(), res2.getSuccess());
                    return new ParseResult.Success<>(value,length);
                } else {
                    return new ParseResult.Failure<>(res2.getError());
                }
            } else {
                return new ParseResult.Failure<>(res1.getError());
            }
        };
    }

    default <B> Parser<Tuple<A,B>> product(LazyParser<B> p2) {
        return this.map2(p2, Tuple::new);
    }

    default <B> Parser<B> as(B b) {
        return this.slice().map(i -> b);
    }

    default <B,C> Parser<C> and(LazyParser<B> p2, BiFunction<A,B,C> f) {
        return this.map2(p2, f);
    }

    default <C,B extends C> Parser<C> or(LazyParser<B> p2) {
        return state -> {
            ParseResult<A> res = this.apply(state);
            if(res.isSuccess()) {
                return new ParseResult.Success<>((C)res.getSuccess(),res.getLength());
            } else {
                ParseResult<B> res2 = p2.get().apply(state);
                if(res2.isSuccess()) {
                    return new ParseResult.Success<>(res2.getSuccess(),res2.getLength());
                } else {
                    return new ParseResult.Failure<>(res2.getError());
                }
            }
        };
    }
}
