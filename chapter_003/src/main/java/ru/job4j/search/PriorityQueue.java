package ru.job4j.search;

import java.util.ArrayList;
import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>(new ArrayList<Task>(5));

 /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        while (this.tasks.size() < task.getPriority()) {
            this.tasks.add(null);
        }
        tasks.add(task.getPriority(),task);
    }

    public Task take() {
        Task result;
        do {
            result = this.tasks.poll();
        }
        while (result == null && this.tasks.size() > 0);
        return result;
    }
}