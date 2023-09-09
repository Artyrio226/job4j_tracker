package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = new FileInputStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenSaveItemThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        assertThat(tracker.add(item)).isEqualTo(item);
    }

    @Test
    public void whenReplaceItemIsSuccessful() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        int id = tracker.add(item).getId();
        Item updateItem = new Item("element");
        assertThat(tracker.replace(id, updateItem)).isTrue();
        assertThat(tracker.findById(id).getName()).isEqualTo(updateItem.getName());
    }

    @Test
    public void whenReplaceItemIsNotSuccessful() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        int id = tracker.add(item).getId();
        Item updateItem = new Item("element");
        assertThat(tracker.replace(100, updateItem)).isFalse();
        assertThat(tracker.findById(id).getName()).isEqualTo(item.getName());
    }

    @Test
    public void whenDeleteItem() throws SQLException {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        int id = tracker.add(item).getId();
        tracker.delete(id);
        assertThat(tracker.findById(id)).isNull();
    }

    @Test
    public void whenTestFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        List<Item> items = tracker.findAll();
        assertThat(items).hasSize(2)
                .contains(first)
                .contains(second);
    }

    @Test
    public void whenTestFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = new Item("First");
        Item second = new Item("Second");
        Item third = new Item("First");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        List<Item> items = tracker.findByName("First");
        assertThat(items).hasSize(2)
                .contains(first, third);
    }
}
