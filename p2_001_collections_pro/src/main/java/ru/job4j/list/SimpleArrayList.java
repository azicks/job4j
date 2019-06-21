package ru.job4j.list;

public class SimpleArrayList<E> {
    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
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
        return result.data;
    }

    /**
    *Метод получения элемента по индексу.
    */
     public E deleteFirst() {
        Node<E> result = this.first;
        this.first = result.next;
        size--;
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
