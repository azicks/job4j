package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void testPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
        result = queue.take();
        assertThat(result.getDesc(), is("middle"));
        result = queue.take();
        assertThat(result.getDesc(), is("low"));
        result = queue.take();
        assertNull(result);
    }
}