package ru.job4j.list;

public class SimpleStack<E> extends SimpleLinkedList {

    public void push(E data) {
        add(data);
    }

    public E poll() {
        return (E) deleteFirst();
    }
}
