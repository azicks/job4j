package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int size;
    private Queue<Node<E>> dt;

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

    public boolean isBinary() {
        boolean result = true;
        for (Node<E> el : this) {
            if (el.numChildren() > 2) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<Node<E>> iterator() {
        dt = new LinkedList<>();
        dt.offer(root);
        return new Iterator<>() {
            private int position;

            @Override
            public boolean hasNext() {
                return position <= size;
            }

            @Override
            public Node<E> next() {
                position++;
                final Node<E> result = dt.poll();

                for (Node<E> child : result.leaves()) {
                    dt.offer(child);
                }
                return result;
            }
        };
    }
}
