package ru.job4j.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertTrue(tree.findBy(6).isPresent());
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertFalse(tree.findBy(7).isPresent());
    }

    @Test
    public void iterateTest() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        StringBuilder result = new StringBuilder();
        for (Integer el : tree) {
            result.append(el);
        }
        assertEquals(result.toString(), "123456");
    }

    @Test
    public void binaryTest() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Tree<Integer> tree2 = new Tree<>(1);
        tree2.add(1, 2);
        tree2.add(1, 3);
        tree2.add(2, 4);
        tree2.add(4, 5);
        tree2.add(5, 6);
        assertFalse(tree.isBinary());
        assertTrue(tree2.isBinary());
    }
}