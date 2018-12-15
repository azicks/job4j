package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        String[] action = {"1", "name", "description", "0"};
        new StartUI(new StubInput(action), tracker);
        assertThat(tracker.findAll()[0].getName(), is("name"));
        assertThat(tracker.findAll()[0].getDesc(), is("description"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc", 123L));
        String[] action = {"2", item.getId(), "test replace", "заменили заявку", "0"};
        new StartUI(new StubInput(action), tracker);
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
        assertThat(tracker.findById(item.getId()).getDesc(), is("заменили заявку"));
    }

    @Test
    public void whenDeleteThenTrackerHasNoValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc", 123L));
        String[] action = {"3", item.getId(), "0"};
        new StartUI(new StubInput(action), tracker);
        assertThat(tracker.findAll(), is(new Item[0]));
    }
}
