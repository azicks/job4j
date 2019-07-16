package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleLinkedList<E> implements Iterable<E> {
    private int size;
    private int modCount;
    private Node<E> first;


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
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}
