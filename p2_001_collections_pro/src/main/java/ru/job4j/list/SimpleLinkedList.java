package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleLinkedList<E> implements Iterable<E> {
    protected int size;
    protected int modCount;
    protected Node<E> first;


    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
        modCount++;
    }

    /**
     * Метод удаления элемента по индексу.
     */
    public E delete(int index) {
        Node<E> result = this.first;
        Node<E> prev;
        if (index < 1) {
            this.first = result.next;
        } else {
            prev = this.first;
            int i;
            for (i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            result = prev.next;
            prev.next = result.next;
        }
        size--;
        modCount++;
        return result.data;
    }

    /**
    *Метод получения элемента по индексу.
    */
     public E deleteFirst() {
        Node<E> result = this.first;
        this.first = result.next;
        size--;
        modCount++;
        return result.data;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result == null ? null : result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentModCount = modCount;
            private Node<E> current = first;

            @Override
            public boolean hasNext() {

                return (!(current == null || current.next == null));
            }

            @Override
            public E next() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E result = current.data;
                current = current.next;
                return result;
            }
        };
    }

    /**
     * Для тестов
     */

    public void addCycle() {
        first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);


        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        five.next = six;

        size = 6;

    }

    boolean hasCycle() {
        boolean result = true;
        Node<E> node = first;
        for (int i = 0; i != size; i++) {
            if (node.next == null) {
                result = false;
            }
            node = node.next;
        }
        return result;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    protected static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

}
