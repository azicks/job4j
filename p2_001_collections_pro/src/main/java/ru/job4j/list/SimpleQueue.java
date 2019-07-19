package ru.job4j.list;

public class SimpleQueue<E> {
    private SimpleStack<E> stack = new SimpleStack();
    private SimpleStack<E> stack2 = new SimpleStack();
    private int size;

    public void push(E data) {
        int stack2size = stack2.getSize();
        for (int i = 0; i != stack2size; i++)
        {
            stack.push(stack2.poll());
        }
        stack.push(data);
        size++;
    }

    public E poll() {
        E result = null;
        if (size != 0) {
            int stack1size = stack.getSize();
            for (int i = 0; i != stack1size; i++) {
                stack2.push(stack.poll());
            }
            size--;
            result = stack2.poll();
        }
        return result;
    }

    public int getSize() {
        return size;
    }
}
