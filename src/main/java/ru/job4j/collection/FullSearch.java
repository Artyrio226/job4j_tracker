package ru.job4j.collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FullSearch {
    public List<String> extractNumber(List<Task> tasks) {
        Set<String> set = new HashSet<>();
        for (Task t : tasks) {
            set.add(t.getNumber());
        }
        return List.copyOf(set);
    }
}
