package ru.job4j.tracker;

import java.sql.SQLException;
import java.util.List;

public class DeleteAllItems implements UserAction {
    private final Output out;

    public DeleteAllItems(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete all items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete all items ===");
        List<Item> allItems = tracker.findAll();
        List<Integer> collect = allItems.stream()
                .map(Item::getId).toList();
        for (Integer integer : collect) {
            try {
                tracker.delete(integer);
            } catch (SQLException e) {
                out.println("Ошибка удаления заявки.");
            }
        }
        out.println("=== Все заявки удалены ===");
        return true;
    }
}