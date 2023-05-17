package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rsl = 0;
        int tmp = Math.min(left.length(), right.length());
        for (int i = 0; i < tmp && rsl == 0; i++) {
            rsl = Character.compare(left.charAt(i), right.charAt(i));
        }
        return rsl == 0 ? Integer.compare(left.length(), right.length()) : rsl;
    }
}