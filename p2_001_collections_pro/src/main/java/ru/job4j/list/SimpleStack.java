package ru.job4j.list;

import java.util.Iterator;

public class SimpleStack<E> implements Iterable<E> {
    private SimpleLinkedList<E> list;

    public SimpleStack() {
        this.list = new SimpleLinkedList<>();
    }

    public void push(E data) {
        list.add(data);
    }

    public E poll() {
        E result = null;
        if (this.getSize() != 0) {
            result = list.deleteFirst();
        }
        return result;
    }

    public E peek() {
        return list.get(0);
    }

    public int getSize() {
        return list.getSize();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
