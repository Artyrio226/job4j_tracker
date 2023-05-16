package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rsl = 0;
        int i = 0;
        int tmp = Integer.compare(left.length(), right.length()) < 0 ? left.length() : right.length();
        while (rsl == 0 && i < tmp) {
            rsl = Character.compare(left.charAt(i), right.charAt(i));
            i++;
        }
        return rsl == 0 ? Integer.compare(left.length(), right.length()) : rsl;
    }
}