package ru.job4j.tracker;

import java.sql.SQLException;

public class DeleteAction implements UserAction {
    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete item ===");
        int id = input.askInt("Enter id: ");
        try {
            tracker.delete(id);
            out.println("Заявка удалена успешно.");
        } catch (SQLException e) {
            out.println("Ошибка удаления заявки.");
        }
        return true;
    }
}
