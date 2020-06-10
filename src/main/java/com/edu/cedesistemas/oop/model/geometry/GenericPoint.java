package com.edu.cedesistemas.oop.model.geometry;

import java.util.Objects;
import java.util.Random;

public class GenericPoint<T extends Number> {
        private T x;
        private T y;

        public GenericPoint (T x, T y) {
            this.x = x;
            this.y = y;
        }

        public T getX() {
            return x;
        }

        public T getY() {
            return y;
        }

        public static <R extends Number> double distance(GenericPoint<R> p1, GenericPoint<R> p2) {
            return Math.sqrt(Math.pow(p1.x.doubleValue() - p2.x.doubleValue(), 2) + Math.pow(p1.y.doubleValue() - p2.y.doubleValue(), 2));
        }

        public static <Y extends Number> GenericPoint<Y> of(Y x, Y y) {
            GenericPoint point = new GenericPoint(x, y);
            return point;
        }

        public static <S extends Number> GenericPoint<S> random(int bound) {
             return null;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }



}
