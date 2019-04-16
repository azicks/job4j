package ru.job4j.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     *
     * @param task задача
     */
    public void put(Task task) {
        this.tasks = Stream.concat(this.tasks.stream(), Stream.of(task))
                .sorted(Comparator.comparingInt(Task::getPriority))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public Task take() {
        return this.tasks.poll();
    }
}