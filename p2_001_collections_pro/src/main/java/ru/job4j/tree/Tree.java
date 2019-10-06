package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int size;
    private Queue<Node<E>> dt = new LinkedList<>();

    public Tree(E rootValue) {
        this.root = new Node<>(rootValue);
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> el = findBy(parent);
        if (el.isPresent()) {
            el.get().add(new Node<>(child));
            result = true;
            size++;
        }
        return result;
    }

    private void addAllChildren(Node<E> el) {
        for (Node<E> child : el.leaves()) {
            dt.offer(child);
            addAllChildren(child);
        }
    }

    public boolean isBinary() {
        return checkBinaryNode(root);
    }

    private boolean checkBinaryNode(Node<E> el) {
        boolean result = true;
        int counter = 0;
        for (Node<E> child : el.leaves()) {
            if (counter++ == 2) {
                result = false;
                break;
            }
            result = checkBinaryNode(child);
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        dt.offer(root);
        addAllChildren(root);
        return new Iterator<>() {
            private int position;

            @Override
            public boolean hasNext() {
                return position <= size;
            }

            @Override
            public E next() {
                position++;
                return dt.poll().getValue();
            }
        };
    }
}
