package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    private static final String MENU = new StringBuilder()
            .append("0. Выход").append(System.lineSeparator())
            .append("1. Добавить заявку").append(System.lineSeparator())
            .append("2. Редактировать заявку").append(System.lineSeparator())
            .append("3. Удалить заявку").append(System.lineSeparator())
            .append("4. Найти заявку по ID").append(System.lineSeparator())
            .append("5. Поиск зявок по имени").append(System.lineSeparator())
            .append("6. Показать все заявки").append(System.lineSeparator())
            .append(System.lineSeparator())
            .append("Выберите действие: ").toString();

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void backOut() {
        System.setOut(stdout);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        String[] action = {"1", "name", "description", "0"};
        new StartUI(new StubInput(action), tracker);
        assertThat(tracker.findAll().get(0).getName(), is("name"));
        assertThat(tracker.findAll().get(0).getDesc(), is("description"));
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
        assertThat(tracker.findAll(), is(new ArrayList<Item>()));
    }

    @Test
    public void whenDelete2Of123ThenTrackerHas13() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("Item1", "desc", 123L));
        Item item2 = tracker.add(new Item("Item2", "desc", 123L));
        Item item3 = tracker.add(new Item("Item3", "desc", 123L));
        String[] action = {"3", item2.getId(), "0"};
        new StartUI(new StubInput(action), tracker);
        List<Item> result = new ArrayList<>();
        result.add(item1);
        result.add(item3);
        assertThat(tracker.findAll(), is(result));
    }

    @Test
    public void whenDelete3Of123ThenTrackerHas12() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("Item1", "desc", 123L));
        Item item2 = tracker.add(new Item("Item2", "desc", 123L));
        Item item3 = tracker.add(new Item("Item3", "desc", 123L));
        String[] action = {"3", item3.getId(), "0"};
        new StartUI(new StubInput(action), tracker);
        List<Item> result = new ArrayList<>();
        result.add(item1);
        result.add(item2);
        assertThat(tracker.findAll(), is(result));
    }

    @Test
    public void whenShowAll() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("Item1", "desc", 123L));
        Item item2 = tracker.add(new Item("Item2", "desc", 123L));
        String[] action = {"6", "0"};
        new StartUI(new StubInput(action), tracker);
        String expected = new StringBuilder()
                .append(MENU)
                .append("6").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Заявка с ID ").append(item1.getId()).append(":")
                .append(System.lineSeparator())
                .append("Имя заявки: Item1").append(System.lineSeparator())
                .append("Описание заявки: desc").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Заявка с ID ").append(item2.getId()).append(":")
                .append(System.lineSeparator())
                .append("Имя заявки: Item2").append(System.lineSeparator())
                .append("Описание заявки: desc").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(MENU)
                .append("0").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("До свидания.").append(System.lineSeparator())
                .toString();
        assertThat(new String(out.toByteArray()), is(expected));
    }

    @Test
    public void whenShowAllButNothingPresent() {
        Tracker tracker = new Tracker();
        String[] action = {"6", "0"};
        new StartUI(new StubInput(action), tracker);
        String expected = new StringBuilder()
                .append(MENU)
                .append("6").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Заявки не найдены.").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(MENU)
                .append("0").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("До свидания.").append(System.lineSeparator())
                .toString();
        assertThat(new String(out.toByteArray()), is(expected));
    }

    @Test
    public void whenFindByIdAndNoFound() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Item1", "desc", 123L));
        tracker.add(new Item("Item2", "desc", 123L));
        tracker.add(new Item("Item3", "desc", 123L));
        String[] action = {"4", "111", "0"};
        new StartUI(new StubInput(action), tracker);
        String expected = new StringBuilder()
                .append(MENU)
                .append("4").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Введите ID заявки: ").append("111").append(System.lineSeparator())
                .append("Заявка с ID 111 не найдена.").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(MENU)
                .append("0").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("До свидания.").append(System.lineSeparator())
                .toString();
        assertThat(new String(out.toByteArray()), is(expected));
    }

    @Test
    public void whenFindByIdButNoItemsd() {
        Tracker tracker = new Tracker();
        String[] action = {"4", "111", "0"};
        new StartUI(new StubInput(action), tracker);
        String expected = new StringBuilder()
                .append(MENU)
                .append("4").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Введите ID заявки: ").append("111").append(System.lineSeparator())
                .append("Заявка с ID 111 не найдена.").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(MENU)
                .append("0").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("До свидания.").append(System.lineSeparator())
                .toString();
        assertThat(new String(out.toByteArray()), is(expected));
    }

    @Test
    public void whenFindByIdAndFound() {
        Tracker tracker = new Tracker();
        String id = tracker.add(new Item("Item1", "desc", 123L)).getId();
        tracker.add(new Item("Item2", "desc", 123L));
        tracker.add(new Item("Item3", "desc", 123L));
        String[] action = {"4", id, "0"};
        new StartUI(new StubInput(action), tracker);
        String expected = new StringBuilder()
                .append(MENU)
                .append("4").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Введите ID заявки: ").append(id).append(System.lineSeparator())
                .append("Заявка с ID ").append(id).append(":")
                .append(System.lineSeparator())
                .append("Имя заявки: Item1").append(System.lineSeparator())
                .append("Описание заявки: desc").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(MENU)
                .append("0").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("До свидания.").append(System.lineSeparator())
                .toString();
        assertThat(new String(out.toByteArray()), is(expected));
    }
}
