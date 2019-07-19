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
        return list.deleteFirst();
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
