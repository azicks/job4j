package ru.job4j.iterator;

import java.util.Iterator;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            private Iterator<Integer> current;

            {
                current = it.next();
            }

            @Override
            public boolean hasNext() {
                boolean result = false;
                result |= current.hasNext();
                if (!result && it.hasNext()) {
                    current = it.next();
                    result = this.hasNext();
                }
                return result;
            }

            @Override
            public Integer next() {
                int result = current.next();
                if (!current.hasNext() && it.hasNext()) {
                    current = it.next();
                }
                return result;
            }
        };
    }
}