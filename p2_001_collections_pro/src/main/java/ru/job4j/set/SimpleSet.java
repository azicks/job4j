package ru.job4j.set;

import ru.job4j.list.ArrList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private ArrList<E> list = new ArrList<>();
    private int size;

    public void add(E e) {
        if (!list.contains(e)) {
            list.add(e);
            size++;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i != size; i++) {
            E e = list.get(i);
            if (e == null) {
                str.append("null");
            } else {
                str.append(e.toString());
            }
            if (i != size - 1) {
                str.append(" ");
            }
        }
        return str.toString();
    }
}
