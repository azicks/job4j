package ru.job4j.list;

public class SimpleQueue<E> {
    private SimpleStack<E> stack = new SimpleStack();
    private SimpleStack<E> stack2 = new SimpleStack();
    private int size;

    public void push(E data) {
        stack.push(data);
        size++;
    }

    public E poll() {
        if (stack2.getSize() == 0) {
            for (int i = 0; i != size; i++) {
                stack2.push(stack.poll());
            }
        }
        if (size != 0) {
            size--;
        }
        return stack2.poll();
    }

    public int getSize() {
        return size;
    }
}
