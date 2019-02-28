package ru.job4j.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Lambda {

    public interface Operation {
        double calc(int left, int right);
    }

    public void multiple(int start, int end, int value, Operation op) {
        for (int i = start; i != end; i++) {
            System.out.println(op.calc(value, i));
        }
    }

    public void multiple2(int start, int end, int value,
                          BiFunction<Integer, Integer, Double> op, Consumer<Double> media) {
        for (int i = start; i != end; i++) {
            media.accept(op.apply(value, i));
        }
    }

    public static void main(String[] args) {
        Lambda l = new Lambda();
        l.multiple(0, 10, 2, new Operation() {
            @Override
            public double calc(int left, int right) {
                return left * right;
            }
        });
        l.multiple(0, 10, 2, (left, right) -> left * right);
        l.multiple(0, 10, 2, Math::multiplyExact);
        l.multiple2(0, 10, 2, (left, right) -> (double) left * right, System.out::println);
    }
}
