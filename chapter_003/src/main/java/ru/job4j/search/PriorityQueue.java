package ru.job4j.search;

import java.util.LinkedList;

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
        boolean added = false;
        for (int idx = 0; idx != this.tasks.size(); idx++) {
            if (this.tasks.get(idx).getPriority() >= task.getPriority()) {
                this.tasks.add(idx, task);
                added = true;
                break;
            }
        }
        if (!added) {
            this.tasks.add(task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}