package com.jackywong.parser;

/**
 * @Author: 黄子奇
 * @Date: 2019/9/21 16:24
 */
public interface ParseResult<A> {
    boolean isSuccess();
    Either<ParseError,A> extract();
    A getSuccess();
    Integer getLength();
    ParseError getError();

    class Success<A> implements ParseResult<A> {
        private A value;
        private Integer length;

        public Success(A value, Integer length) {
            this.value = value;
            this.length = length;
        }

        public boolean isSuccess() {
            return true;
        }

        public Either<ParseError, A> extract() {
            return new Either.Right<ParseError, A>(value);
        }

        public A getSuccess() {
            return value;
        }

        public Integer getLength() {
            return length;
        }

        public ParseError getError() {
            return null;
        }
    }

    class Failure<A> implements ParseResult<A> {
        private ParseError error;

        public Failure(ParseError error) {
            this.error = error;
        }

        public boolean isSuccess() {
            return false;
        }

        public Either<ParseError, A> extract() {
            return new Either.Left<ParseError, A>(error);
        }

        public A getSuccess() {
            return null;
        }

        public Integer getLength() {
            return null;
        }

        public ParseError getError() {
            return error;
        }
    }
}
