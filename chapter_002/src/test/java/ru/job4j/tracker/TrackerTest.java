package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenFindItemByNameThenTrackerHasSTwoItems() {
        Tracker tracker = new Tracker();
        Item[] expected = new Item[2];
        Item item1 = new Item("test1", "testDescription", 123L);
        tracker.add(item1);
        Item item2 = new Item("goal", "testDescription", 124L);
        tracker.add(item2);
        Item item3 = new Item("test2", "testDescription", 125L);
        tracker.add(item3);
        Item item4 = new Item("goal", "testDescription", 126L);
        tracker.add(item4);
        expected[0] = item2;
        expected[1] = item4;
        assertThat(tracker.findByName("goal"), is(expected));
    }

    @Test
    public void whenReplaceItem2OnItem3ThenItem1Item3() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 123L);
        tracker.add(item1);
        Item item2 = new Item("test2", "testDescription", 124L);
        tracker.add(item2);
        Item item3 = new Item("test3", "testDescription", 125L);
        Item[] expected = new Item[]{item1, item3};
        assertThat(tracker.replace(item2.getId(), item3), is(true));
        assertThat(tracker.replace("-1", item3), is(false));
        assertThat(tracker.findAll(), is(expected));
    }

    @Test
    public void whenDeleteItem2ThenItem1Item3() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 123L);
        tracker.add(item1);
        Item item2 = new Item("test2", "testDescription", 124L);
        tracker.add(item2);
        Item item3 = new Item("test3", "testDescription", 125L);
        tracker.add(item3);
        Item[] expected = new Item[]{item1, item3};
        assertThat(tracker.delete(item2.getId()), is(true));
        assertThat(tracker.delete("-1"), is(false));
        assertThat(tracker.findAll(), is(expected));
    }

    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        item.setId("1");
        tracker.add(item);
        assertThat(tracker.findById("1"), is(item));
        assertThat(tracker.findById("-1"), is((Object) null));
    }
}
