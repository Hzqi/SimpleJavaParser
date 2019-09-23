package com.jackywong.parser;

/**
 * @Author: 黄子奇
 * @Date: 2019/9/21 16:25
 */
public interface Either<L,R> {
    boolean isRight();
    L getLeft();
    R getRight();

    class Left<L,R> implements Either<L,R> {
        private L value;

        public Left(L value) {
            this.value = value;
        }

        public boolean isRight() {
            return false;
        }

        public L getLeft() {
            return value;
        }

        public R getRight() {
            return null;
        }
    }

    class Right<L,R> implements Either<L,R> {
        private R value;

        public Right(R value) {
            this.value = value;
        }

        public boolean isRight() {
            return true;
        }

        public L getLeft() {
            return null;
        }

        public R getRight() {
            return value;
        }
    }
}
