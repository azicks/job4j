package ru.job4j.tracker;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {

    private TrackerSQL tracker;

    @Before
    public void setTracker() {
        TrackerSQL tr = new TrackerSQL();
        if (tr.init()) {
            this.tracker = tr;
        }
    }

    @After
    public void closeTracker() {
        if (this.tracker != null) {
            try {
                tracker.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenFindItemByNameThenTrackerHasSTwoItems() {
        String suffix = String.valueOf(System.currentTimeMillis());
        Item item1 = new Item("test1" + suffix, "testDescription", 123L);
        tracker.add(item1);
        Item item2 = new Item("goal" + suffix, "testDescription", 124L);
        tracker.add(item2);
        Item item3 = new Item("test2" + suffix, "testDescription", 125L);
        tracker.add(item3);
        Item item4 = new Item("goal" + suffix, "testDescription", 126L);
        tracker.add(item4);
        List<Item> expected = new ArrayList<>();
        expected.add(item2);
        expected.add(item4);
        assertThat(tracker.findByName("goal" + suffix), is(expected));
    }

    @Test
    public void whenReplaceItem2OnItem3ThenItem1Item3() {
        String itemName = String.format("test_replace-%s", String.valueOf(System.currentTimeMillis()));
        Item item1 = new Item(itemName, "testDescription", 123L);
        tracker.add(item1);
        Item item2 = new Item(itemName, "testDescription", 124L);
        tracker.add(item2);
        Item item3 = new Item(itemName, "testDescription", 125L);
        List<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item3);
        assertThat(tracker.replace(item2.getId(), item3), is(true));
        assertThat(tracker.replace("-1", item3), is(false));
        assertThat(tracker.findByName(itemName), is(expected));
    }

    @Test
    public void whenDeleteItem2ThenItem1Item3() {
        String itemNmame = String.format("test_del-%s", String.valueOf(System.currentTimeMillis()));
        Item item1 = new Item(itemNmame, "testDescription", 123L);
        tracker.add(item1);
        Item item2 = new Item(itemNmame, "testDescription", 124L);
        tracker.add(item2);
        Item item3 = new Item(itemNmame, "testDescription", 125L);
        tracker.add(item3);
        List<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item3);
        assertThat(tracker.delete(item2.getId()), is(true));
        assertThat(tracker.delete("-1"), is(false));
        assertThat(tracker.findByName(itemNmame), is(expected));
    }

    @Test
    public void whenFindById() {
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        item = tracker.findByName("test1").get(0);
        assertThat(tracker.findById(item.getId()), is(item));
        assertThat(tracker.findById("-1"), is((Object) null));
    }
}
