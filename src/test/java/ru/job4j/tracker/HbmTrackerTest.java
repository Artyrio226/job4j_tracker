package ru.job4j.tracker;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class HbmTrackerTest {
    private final HbmTracker tracker = new HbmTracker();

    @AfterEach
    public void clear() {
        var items = tracker.findAll();
        for (var item : items) {
            tracker.delete(item.getId());
        }
    }

    @Test
    public void whenReplaceItemThenTrackerHasNewItem() throws Exception {
        try {
            Item item1 = new Item();
            Item item2 = new Item();
            item1.setName("test1");
            item2.setName("test2");
            tracker.add(item1);
            boolean result = tracker.replace(item1.getId(), item2);
            var itemResult = tracker.findById(item1.getId());

            assertThat(result).isTrue();
            assertThat(itemResult.getName()).isEqualTo(item2.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDeleteItemThenTrackerHasNoItem() throws Exception {
        try {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            var result = tracker.findById(item.getId());

            assertThat(result.getName()).isEqualTo(item.getName());

            boolean deleted = tracker.delete(item.getId());
            result = tracker.findById(item.getId());

            assertThat(deleted).isTrue();
            assertThat(result).isNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddTwoItemThenFindTwoItem() throws Exception {
        try {
            Item item1 = new Item();
            Item item2 = new Item();
            item1.setName("test1");
            item2.setName("test2");
            tracker.add(item1);
            tracker.add(item2);
            var itemList = tracker.findAll();

            assertThat(itemList).contains(item1, item2);
            assertThat(itemList).isEqualTo(List.of(item1, item2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddNewItemThenFindItemByName() throws Exception {
        try {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            var result = tracker.findByName(item.getName());

            assertThat(result).isEqualTo(List.of(item));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            var result = tracker.findById(item.getId());

            assertThat(result.getName()).isEqualTo(item.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}