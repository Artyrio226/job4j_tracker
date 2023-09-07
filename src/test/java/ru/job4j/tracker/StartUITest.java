package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StartUITest {
    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        Store tracker = new SqlTracker();
        List<UserAction> actions = new ArrayList<>(List.of(
                new CreateAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo("Item name");
    }

    @Test
    public void whenShowAllItem() {
        Output out = new StubOutput();
        Store tracker = new SqlTracker();
        Item item = tracker.add(new Item("Test1"));
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        List<UserAction> actions = new ArrayList<>(List.of(
                new ShowAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Show all items ===" + ln
                        + item + ln
                        + "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        );
    }

    @Test
    public void whenEditItem() {
        Output out = new StubOutput();
        Store tracker = new SqlTracker();
        Item item = tracker.add(new Item("Edited item"));
        String editedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), editedName, "1"}
        );
        List<UserAction> actions = new ArrayList<>(List.of(
                new EditAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(editedName);
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        Store tracker = new SqlTracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = new ArrayList<>(List.of(
                new DeleteAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenFindByIdItem() {
        Output out = new StubOutput();
        Store tracker = new SqlTracker();
        Item item = tracker.add(new Item("Test1"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = new ArrayList<>(List.of(
                new FindByIdAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Find item by id ===" + ln
                        + item + ln
                        + "Menu:" + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        );
    }

    @Test
    public void whenFindByNameItem() {
        Output out = new StubOutput();
        Store tracker = new SqlTracker();
        Item item = tracker.add(new Item("Test1"));
        Input in = new StubInput(
                new String[] {"0", "Test1", "1"}
        );
        List<UserAction> actions = new ArrayList<>(List.of(
                new FindByNameAction(out),
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Find items by name ===" + ln
                        + item + ln
                        + "Menu:" + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        );
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Store tracker = new SqlTracker();
        Input in = new StubInput(
                new String[] {"4", "0"}
        );
        List<UserAction> actions = new ArrayList<>(List.of(
                new ExitAction(out)
        ));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Exit Program" + ln
                        + "Wrong input, you can select: 0 .. 0" + ln
                        + "Menu:" + ln
                        + "0. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        );
    }
}