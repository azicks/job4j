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
}
